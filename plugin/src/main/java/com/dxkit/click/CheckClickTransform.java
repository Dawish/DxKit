package com.dxkit.click;

import com.dxkit.InfoClassVisitor;
import com.dxkit.base.BaseTransform;
import com.dxkit.base.DxKitExtension;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * onClick相关的操作
 *
 * @author danxingxi
 */
public class CheckClickTransform extends BaseTransform {

    List<String> searchStringList = new ArrayList<String>();

    CheckClickTransform() {
        searchStringList.addAll(DxKitExtension.searchStrings);
    }

    @Override
    protected ClassVisitor getClassVisitor(ClassWriter classWriter) {
        return new CheckClickClassVisitor(classWriter);
//        return new InfoClassVisitor(classWriter, searchStringList);
    }

    @Override
    protected boolean isNeedTraceClass(File file) {
        String name = file.getName();
        if (!name.endsWith(".class")
                || name.startsWith("R.class")
                || name.startsWith("R$")) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "CheckClickTransform";
    }
}