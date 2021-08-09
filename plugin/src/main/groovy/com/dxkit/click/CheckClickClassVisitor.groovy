package com.dxkit.click

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

/**
 * jvm指令不懂看：https://www.cnblogs.com/lsy131479/p/11201241.html
 */
class CheckClickClassVisitor extends ClassVisitor {

    CheckClickClassVisitor(int api) {
        super(api)
    }

    CheckClickClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM6, classVisitor)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        def methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions)
        if (name == "onClick" && descriptor == "(Landroid/view/View;)V") {
            return new FastMethodVisitor(api, methodVisitor, access, name, descriptor)
        } else {
            return methodVisitor
        }
    }

    class FastMethodVisitor extends AdviceAdapter {

        FastMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor)
        }

        // 方法进入
        @Override
        protected void onMethodEnter() {
            super.onMethodEnter()
            // ASM开始扫描这个方法
            // mv.visitCode()
            // 访问局部变量指令。 局部变量指令是加载loads或存储stores局部变量值的指令。
            // mv.visitVarInsn()
            // label 代表跳转的字节码位置
            mv.visitMethodInsn(INVOKESTATIC, "com/dxkit/library/utils/FastClickUtil", "isFastDoubleClick", "()Z", false)
            Label label = new Label()
            mv.visitJumpInsn(IFEQ, label)
            mv.visitInsn(RETURN)
            mv.visitLabel(label)
        }

        // 开始执行方法，插入的代码会onMethodEnter插入的代码之后，在本来执行代码之前。
        @Override
        void visitCode() {
            super.visitCode()
///////////////////////////////////////////////// 牢记jvm是基于栈，操作都是在栈顶操作////////////////////////////////
//            # 将 x, y放入操作数栈
//            mv.visitVarInsn(Opcodes.ILOAD, 1);
//            mv.visitVarInsn(Opcodes.ILOAD, 2);
//            # 栈顶2个元素相加，将结果放回栈顶
//            mv.visitInsn(Opcodes.IADD);
//            # 创建新的局部变量表索引
//            int newLocal = newLocal(Type.INT_TYPE);
//            # 将操作数栈顶值放入创建的索引指向的位置
//            mv.visitVarInsn(Opcodes.ISTORE, newLocal);
//            # 将结果放回操作数栈顶
//            mv.visitVarInsn(Opcodes.ILOAD, newLocal);
//            # 返回操作数栈顶值
//            mv.visitInsn(Opcodes.IRETURN);

            // 把OnClick(View v)的参数v放入
            mv.visitVarInsn(ALOAD, 1)
            // INVOKESTATIC访问静态方法
            mv.visitMethodInsn(INVOKESTATIC, "com/dxkit/library/utils/SeeViewId", "seeViewIdName", "(Landroid/view/View;)V", false)

        }
    }

}