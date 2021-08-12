package com.dxkit;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;


/**
 * 在编译的时候查找指定字符，并输出到文件，可以排查第三方库
 *
 * @author danxingxi 2021/8/10
 */
public class FindClassVisitor extends ClassVisitor {

    private String mClassName;

    public FindClassVisitor(int api) {
        super(api);
    }

    public FindClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return super.visitField(access, name, descriptor, signature, value);
    }


    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    private String getJavaClassName() {
        return mClassName != null ? mClassName.replace('/', '.') : null;
    }
}
