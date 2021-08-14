package com.dxkit;

import com.dxkit.base.DxKitExtension;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import java.util.Arrays;
import java.util.List;

/**
 * 查看class信息
 */
public class InfoClassVisitor extends ClassVisitor {

    private String mClassName;
    private List<String> mSearchStringList;

    public InfoClassVisitor(ClassVisitor classVisitor, List<String> searchStringList) {
        super(Opcodes.ASM6, classVisitor);
        mSearchStringList = searchStringList;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", version:"+version+", access:"+access+", name:"+name+", signature:"+signature+", supperName:"+signature+", interfaces:"+ Arrays.toString(interfaces));

    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", desc:"+desc+", visible:"+visible);

        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", attr:"+attr);

    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", access:"+access+", name:"+name+", desc:"+desc+", signature:"+signature+", value:"+value);

        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", name:"+name+", outerName:"+outerName+", innerName:"+innerName+", access:"+access);

        super.visitInnerClass(name, outerName, innerName, access);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", access:"+access+", name:"+name+", desc:"+desc+", signature:"+signature+", exceptions:"+Arrays.toString(exceptions));

        System.out.println("DxKitExtension searchStrings:" + DxKitExtension.searchStrings);
        System.out.println("DxKitExtension clickUtilPath:" + DxKitExtension.searchStrings);

        if(name.equals("getNetworkTypeTest")){
            System.out.println(">>>>>>>>>>>>>>visitMethod is getNetworkTypeTest, return SimpleMethodVisitor");
            return new InfoMethodVisitor(methodVisitor, mClassName, name, mSearchStringList);
        }
        return methodVisitor;

    }


    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", owner:"+owner+", name:"+name+", desc:"+desc);

        super.visitOuterClass(owner, name, desc);
    }

    @Override
    public void visitSource(String source, String debug) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", source:"+source+", debug:"+debug);

        super.visitSource(source, debug);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+", typeRef:"+typeRef+", typePath:"+typePath+", desc:"+desc+", visible:"+visible);

        return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
    }


}
