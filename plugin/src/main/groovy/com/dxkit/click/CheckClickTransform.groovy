package com.dxkit.click

import com.dxkit.base.BaseTransform
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

/**
 * onClick相关的操作
 *
 * @author danxingxi
 */
class CheckClickTransform extends BaseTransform {

    @Override
    ClassVisitor getClassVisitor(ClassWriter classWriter) {
        return new CheckClickClassVisitor(classWriter)
    }

    @Override
    boolean isNeedTraceClass(File file) {
        def name = file.name
        if (!name.endsWith(".class")
                || name.startsWith("R.class")
                || name.startsWith('R$')) {
            return false
        }
        return true
    }

    @Override
    String getName() {
        return "CheckClickTransform"
    }
}