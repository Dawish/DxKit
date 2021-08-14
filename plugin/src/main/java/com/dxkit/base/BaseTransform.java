package com.dxkit.base;

import com.android.build.api.transform.*;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.ide.common.internal.WaitableExecutor;
import com.google.common.io.Files;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public abstract class BaseTransform extends Transform {
    protected WaitableExecutor mWaitableExecutor = WaitableExecutor.useGlobalSharedThreadPool();

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        // 需要处理的数据类型,这里表示class文件
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        // 作用范围
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        // 是否支持增量编译
        // return true
        // TODO 先不处理增量问题
        return false;
    }

    /**
     * 关键: 在transform方法中，我们需要将每个jar包和class文件复制到dest路径，这个dest路径就是下一个Transform的输入数据。
     * 而在复制时，就可以将jar包和class文件的字节码做一些修改，再进行复制。
     */
    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        printCopyRight();

        // TransformOutputProvider管理输出路径,如果消费型输入为空,则outputProvider也为空
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();

        // 当前是否是增量编译,由isIncremental方法决定的
        // 当上面的isIncremental()写的返回true,这里得到的值不一定是true,还得看当时环境.比如clean之后第一次运行肯定就不是增量编译嘛.
        boolean isIncremental = transformInvocation.isIncremental();
        if (!isIncremental) {
            //不是增量编译则删除之前的所有文件
            outputProvider.deleteAll();
        }

        // transformInvocation.inputs的类型是Collection<TransformInput>,可以从中获取jar包和class文件夹路径。需要输出给下一个任务
        // 这里的input是TransformInput
        for (TransformInput input : transformInvocation.getInputs()) {

            //处理jar
            for (JarInput jarInput : input.getJarInputs()) {
                //多线程
                mWaitableExecutor.execute(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        processJarInput(jarInput, outputProvider, isIncremental);
                        return null;
                    }
                });
            }

            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                // 多线程
                mWaitableExecutor.execute(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        processDirectoryInput(directoryInput, outputProvider, isIncremental);
                        return null;
                    }
                });
            }
        }

        // 等待所有任务结束
        mWaitableExecutor.waitForTasksWithQuickFail(true);
    }

    /**
     * 处理jar
     * 将修改过的字节码copy到dest,就可以实现编译期间干预字节码的目的
     */
    void processJarInput(JarInput jarInput, TransformOutputProvider outputProvider, boolean isIncremental) {
        Status status = jarInput.getStatus();
        File dest = outputProvider.getContentLocation(jarInput.getFile().getAbsolutePath(), jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
        if (isIncremental) {
            switch (status) {
                case NOTCHANGED:
                    break;
                case ADDED:
                case CHANGED:
                    transformJar(jarInput.getFile(), dest);
                    break;
                case REMOVED:
                    if (dest.exists()) {
                        try {
                            FileUtils.forceDelete(dest);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        } else {
            transformJar(jarInput.getFile(), dest);
        }

    }

    private void transformJar(File jarInputFile, File dest) {
        try {
            FileUtils.copyFile(jarInputFile, dest);
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * 处理源码文件
     * 将修改过的字节码copy到dest,就可以实现编译期间干预字节码的目的
     */
    private void processDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException {
        File dest = outputProvider.getContentLocation(directoryInput.getName(), directoryInput.getContentTypes(), directoryInput.getScopes(), Format
                .DIRECTORY);
        try {
            FileUtils.forceMkdir(dest);
        } catch (IOException e) {
            // Ignore
        }

        println("isIncremental = $isIncremental");

        if (isIncremental) {
            String srcDirPath = directoryInput.getFile().getAbsolutePath();
            String destDirPath = dest.getAbsolutePath();
            Map<File, Status> fileStatusMap = directoryInput.getChangedFiles();
            for (Map.Entry<File, Status> changedFile : fileStatusMap.entrySet()) {
                Status status = changedFile.getValue();
                File inputFile = changedFile.getKey();
                String destFilePath = inputFile.getAbsolutePath().replace(srcDirPath, destDirPath);
                File destFile = new File(destFilePath);
                switch (status) {
                    case NOTCHANGED:
                        break;
                    case ADDED:
                    case CHANGED:
                        FileUtils.touch(destFile);
                        transformSingleFile(inputFile, destFile);
                        break;
                    case REMOVED:
                        if (destFile.exists()) {
                            FileUtils.forceDelete(destFile);
                        }
                        break;
                }
            }
        } else {
            transformDirectory(directoryInput, dest);
        }
    }

    protected void transformSingleFile(File inputFile, File destFile) {
        println("transformSingleFile");
        // FileUtils.copyFile(inputFile, destFile)
        traceFile(inputFile, destFile);
    }

    protected void traceFile(File inputFile, File outputFile) {
        try {
            if (isNeedTraceClass(inputFile)) {
                println(" >>>>> isNeedTraceClass >>>>> ${inputFile.name}");
                FileInputStream inputStream = new FileInputStream(inputFile);
                FileOutputStream outputStream = new FileOutputStream(outputFile);

                ClassReader classReader = new ClassReader(inputStream);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                classReader.accept(getClassVisitor(classWriter), ClassReader.EXPAND_FRAMES);
                outputStream.write(classWriter.toByteArray());

                inputStream.close();
                outputStream.close();
            } else {
                FileUtils.copyFile(inputFile, outputFile);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 获取一个用于插桩的ClassVisitor
     */
    protected abstract ClassVisitor getClassVisitor(ClassWriter classWriter);

    /**
     * 这个文件是否需要插桩
     */
    protected abstract boolean isNeedTraceClass(File file);

    private void transformDirectory(DirectoryInput directoryInput, File dest) throws IOException {

        String[] extensions = new String[]{"class"};
        // 递归地去获取该文件夹下面所有的文件
        Collection<File> fileList = FileUtils.listFiles(directoryInput.getFile(), extensions, true);
        String inputFilePath = directoryInput.getFile().getAbsolutePath();
        String outputFilePath = dest.getAbsolutePath();

        // inputFilePath = DxKit\app\build\intermediates\javac\debug\classes
        // outputFilePath = DxKit\app\build\intermediates\transforms\CheckClickTransform\debug\40
        println("inputFilePath = $inputFilePath");
        println("outputFilePath = $outputFilePath");


        for (File inputFile : fileList) {
            // replace before file.absolutePath = DxKit\app\build\intermediates\javac\debug\classes\com\dxkit\demo\MainActivity.class
            // replace after file.absolutePath = DxKit\app\build\intermediates\transforms\CheckClickTransform\debug\40\com\dxkit\demo\MainActivity.class
            println("replace before file.absolutePath = ${inputFile.absolutePath}");
            String outputFullPath = inputFile.getAbsolutePath().replace(inputFilePath, outputFilePath);
            println("replace after file.absolutePath = ${outputFullPath}");
            File outputFile = new File(outputFullPath);
            // 创建文件
            try {
                FileUtils.touch(outputFile);
            } catch (IOException e) {
                Files.createParentDirs(outputFile);
            }
            // 单个单个地复制文件
            // FileUtils.copyFile(file, outputFile)
            transformSingleFile(inputFile, outputFile);
        }

        // 如果不处理,则直接复制文件夹给下一个Transform的输入目录就行
        // FileUtils.copyDirectory(directoryInput.file, dest)
    }

    /**
     * 加个打印日志,表示执行到当前Transform了,有标志性,很容易看到
     */
    static void printCopyRight() {
        println();
        println("******************************************************************************");
        println("******                                                                  ******");
        println("******                Welcome use transform plugin                      ******");
        println("******                                                                  ******");
        println("******************************************************************************");
        println();
    }

    private static void println() {
        System.out.println("");
    }

    private static void println(String msg) {
        System.out.println(msg);
    }
}