package com.dxkit.click;

import com.dxkit.utils.LogUtil;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * jvm指令不懂看：https://www.cnblogs.com/lsy131479/p/11201241.html
 * <p>
 * MethodVisitor方法说明：https://blog.csdn.net/qq_33589510/article/details/105273233
 * <p>
 * asm说明：https://zhuanlan.zhihu.com/p/94498015?utm_source=wechat_timeline
 */
public class ClickClassVisitor extends ClassVisitor {

    ClickClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM6, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);

        LogUtil.log("------ name:" + name + " ------");
        LogUtil.log("DxKit descriptor:" + descriptor);
        LogUtil.log("DxKit signature:" + signature);

        if ("onClick".equals(name) && "(Landroid/view/View;)V".equals(descriptor)) {
            return new ClickMethodVisitor(api, methodVisitor, access, name, descriptor);
        } else {
            return methodVisitor;
        }
    }


    public class ClickMethodVisitor extends AdviceAdapter {

        ClickMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);
        }

        @Override
        protected void onMethodEnter() {
            super.onMethodEnter();
            mv.visitMethodInsn(INVOKESTATIC, "com/dxkit/library/utils/FastClickUtil", "isFastDoubleClick", "()Z", false);
            Label label = new Label();
            mv.visitJumpInsn(IFEQ, label);
            mv.visitInsn(RETURN);
            mv.visitLabel(label);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            // 把OnClick(View v)的参数v放入
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "com/dxkit/library/utils/SeeViewId", "seeViewIdName", "(Landroid/view/View;)V", false);

        }
    }

}