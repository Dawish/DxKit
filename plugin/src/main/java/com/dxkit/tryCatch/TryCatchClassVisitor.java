package com.dxkit.tryCatch;

import com.dxkit.utils.ASMUtil;
import com.dxkit.utils.LogUtil;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * @author danxingxi
 */
public class TryCatchClassVisitor extends ClassVisitor {

    private List<String> methodList;

    TryCatchClassVisitor(ClassVisitor classVisitor, List<String> methodList) {
        super(Opcodes.ASM6, classVisitor);
        this.methodList = methodList;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        LogUtil.log("TryCatchClassVisitor:" + "name : " + name);

        if (isNeedTryCatch(name)) {
            return new TryCatchMethodVisitor(Opcodes.ASM6, methodVisitor, access, name, descriptor);
        } else {
            return methodVisitor;
        }
    }

    /**
     * 方法是否需要加try catch
     */
    private boolean isNeedTryCatch(String methodName) {
        if (methodList != null && methodList.contains(methodName)) {
            return true;
        }

        return false;
    }

    /**
     * 方法执行顺序
     * onMethodEnter
     * visitCode
     * onMethodExit
     * visitMaxs
     * visitEnd
     *
     * @author danxingxi
     */
    public class TryCatchMethodVisitor extends AdviceAdapter {

        // 方法返回值类型描述符
        private String methodDesc;

        private String exceptionHandleClass;

        private String exceptionHandleMethod;

        private Label startLabel = new Label(),   // 开头
                endLabel = new Label(),           // 结尾
                handlerLabel = new Label(),       // 处理
                returnLabel = new Label();        // 返回

        TryCatchMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);

            LogUtil.log("TryCatchMethodVisitor:" + "descriptor : " + descriptor);
            methodDesc = descriptor;

            Map<String, String> exceptionHandler = TryCatchExtension.exceptionHandler;
            if (exceptionHandler != null && !exceptionHandler.isEmpty()) {
                exceptionHandler.entrySet().forEach(entry -> {
                    exceptionHandleClass = entry.getKey();
                    exceptionHandleMethod = entry.getValue();
                });
            }

        }

        // 开始执行方法，插入的代码会onMethodEnter插入的代码之后，在本来执行代码之前。
        @Override
        public void visitCode() {
            super.visitCode();
        }

        // 方法进入
        @Override
        protected void onMethodEnter() {
            super.onMethodEnter();
            // 1标志：try块开始位置
            mv.visitTryCatchBlock(startLabel,
                    endLabel,
                    handlerLabel,
                    "java/lang/Exception");
            mv.visitLabel(startLabel);
        }

        @Override
        protected void onMethodExit(int opcode) {
            super.onMethodExit(opcode);
        }


        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            // 2标志：try块结束
            mv.visitLabel(endLabel);

            // 3标志：catch块开始位置
            mv.visitLabel(handlerLabel);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Exception"});
            // 0代表this， 1 第一个参数，异常信息保存到局部变量
            mv.visitVarInsn(ASTORE, 1);
            // 从local variables取出局部变量到operand stack
            mv.visitVarInsn(ALOAD, 1);
            // 自定义异常处理
            if (exceptionHandleClass != null && exceptionHandleMethod != null) {
                mv.visitMethodInsn(INVOKESTATIC, exceptionHandleClass,
                        exceptionHandleMethod, "(Ljava/lang/Exception;)V", false);

            } else {
                // 没提供处理类就直接抛出异常
                mv.visitInsn(ATHROW);
            }

            // 顺序向下执行，可以不要GOTO
            //mv.visitJumpInsn(Opcodes.GOTO, returnLabel);
            // 返回label
            // mv.visitLabel(returnLabel);

            // catch结束，方法返回默认值收工
            Pair<Integer, Integer> defaultVo = ASMUtil.getDefaultByDesc(methodDesc);
            int value = defaultVo.getKey();
            int opcode = defaultVo.getValue();
            if (value >= 0) {
                mv.visitInsn(value);
            }
            mv.visitInsn(opcode);

            super.visitMaxs(maxStack, maxLocals);

        }

        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }

}