package com.dxkit.tryCatch;

import com.dxkit.utils.LogUtil;
import com.quinn.hunter.transform.asm.BaseWeaver;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.util.List;

/**
 * try catch Weaver
 *
 * @author danxingxi 2021.8.15
 */
public class TryCatchWeaver extends BaseWeaver {
    private List<String> methodList;

    @Override
    public boolean isWeavableClass(String fullQualifiedClassName) {
        LogUtil.log("TryCatchWeaver: fullQualifiedClassName:" + fullQualifiedClassName);

        boolean isSupport = false;
        if (fullQualifiedClassName != null && TryCatchExtension.isValid()) {

            for (String keyClassName : TryCatchExtension.methodMap.keySet()) {
                LogUtil.log("TryCatchWeaver: methodMap key:" + keyClassName);
                if (fullQualifiedClassName.contains(keyClassName)) {
                    methodList = TryCatchExtension.methodMap.get(keyClassName);
                    isSupport = true;
                    break;
                }
            }
        }

        LogUtil.log("TryCatchWeaver: isSupport:" + isSupport);
        return isSupport && super.isWeavableClass(fullQualifiedClassName);
    }


    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new TryCatchClassVisitor(classWriter, methodList);
    }

}
