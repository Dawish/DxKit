package com.dxkit.click;

import com.quinn.hunter.transform.asm.BaseWeaver;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;


/**
 * try catch Weaver
 *
 * @author danxingxi 2021.8.15
 */
public class ClickWeaver extends BaseWeaver {

    @Override
    public boolean isWeavableClass(String fullQualifiedClassName) {
        return super.isWeavableClass(fullQualifiedClassName);
    }


    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new ClickClassVisitor(classWriter);
    }

}
