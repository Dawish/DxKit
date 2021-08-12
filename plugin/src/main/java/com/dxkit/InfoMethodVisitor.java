package com.dxkit;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import java.util.List;

/**
 * 查看method信息
 */
public class InfoMethodVisitor extends MethodVisitor {

    private String mClassName;
    private String mMethodName;
    private List<String> mSearchStringList;

    public InfoMethodVisitor(MethodVisitor mv, String className, String methodName, List<String> searchStringList) {
        super(Opcodes.ASM6, mv);
        mClassName = className;
        mMethodName = methodName;
        mSearchStringList = searchStringList;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", desc:" + desc + ", visible:" + visible);

        return super.visitAnnotation(desc, visible);
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

        return super.visitAnnotationDefault();
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", attr:" + attr);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("visitor method begin.");
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("visitor method ended.");
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode + ", owner:" + owner + ", name:" + name + ", desc:" + desc);

    }

    @Override
    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
        super.visitFrame(type, nLocal, local, nStack, stack);

    }

    @Override
    public void visitIincInsn(int var, int increment) {
        super.visitIincInsn(var, increment);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", var:" + var + ", increment:" + increment);

    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode);

    }

    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", typeRef:" + typeRef + ", typePath:" + typePath + ", desc:" + desc + ", visible:" + visible);

        return super.visitInsnAnnotation(typeRef, typePath, desc, visible);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        super.visitIntInsn(opcode, operand);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode + ", operand:" + operand);

    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode + ", label:" + label);

    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", label:" + label);

    }

    @Override
    public void visitLdcInsn(Object cst) {
        super.visitLdcInsn(cst);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", cst:" + cst);

    }

    @Override
    public void visitLineNumber(int line, Label start) {
        super.visitLineNumber(line, start);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", line:" + line + ", start label:" + line);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        super.visitLocalVariable(name, desc, signature, start, end, index);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", name:" + name + ", desc:" + desc + ", signature:" + signature);

    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
        return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, desc, visible);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
    }


    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode + ", owner:" + owner + ", name:" + name + ", desc:" + desc);

    }

    @Override
    public void visitMultiANewArrayInsn(String desc, int dims) {
        super.visitMultiANewArrayInsn(desc, dims);
    }

    @Override
    public void visitParameter(String name, int access) {
        super.visitParameter(name, access);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", name:" + name + ", access:" + access);

    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
        return super.visitParameterAnnotation(parameter, desc, visible);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        super.visitTableSwitchInsn(min, max, dflt, labels);
    }

    @Override
    public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        return super.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        return super.visitTypeAnnotation(typeRef, typePath, desc, visible);

    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode + ", type:" + type);

        System.out.println("============mClassName: " + mClassName);
        System.out.println("============mMethodName: " + mMethodName);

    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", opcode:" + opcode + ",var:" + var);

    }

    private boolean isContainSearchStr(String str){
        if (mSearchStringList != null && mSearchStringList.contains(str)) {
            System.out.println("isContainSearchStr:" + str);
            return true;
        }
        return false;
    }

}
