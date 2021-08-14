package com.dxkit.click;

import com.dxkit.base.BaseTransform;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * onClick相关的操作
 *
 * @author danxingxi
 */
public class CheckClickTransform extends BaseTransform {


    CheckClickTransform() {

    }

    @Override
    protected ClassVisitor getClassVisitor(ClassWriter classWriter) {
        return new CheckClickClassVisitor(classWriter);
//        return new InfoClassVisitor(classWriter, searchStringList);
    }

    @Override
    public String getName() {
        return "CheckClickTransform";
    }
}