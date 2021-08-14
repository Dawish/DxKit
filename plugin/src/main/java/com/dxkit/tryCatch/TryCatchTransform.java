package com.dxkit.tryCatch;

import com.dxkit.base.BaseTransform;
import com.dxkit.utils.LogUtil;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.util.List;

/**
 * onClick相关的操作
 *
 * @author danxingxi
 */
public class TryCatchTransform extends BaseTransform {
    private List<String> methodList;

    public TryCatchTransform() {

    }

    @Override
    protected boolean isWeavableClass(File file) {


        boolean isSupport = false;
        if (file != null && TryCatchExtension.isValid()) {
            // 兼容windows和linux的文件路径正斜杠和反斜杠
            String tempPathName =
                    file.getAbsolutePath().replace("\\", ".").
                            replaceAll("/", ".");
            LogUtil.log("TryCatchTransform: tempName:" + tempPathName);

            for (String keyClassName : TryCatchExtension.methodMap.keySet()) {
                LogUtil.log("TryCatchTransform: methodMap key:" + keyClassName);
                if (tempPathName.contains(keyClassName)) {
                    methodList = TryCatchExtension.methodMap.get(keyClassName);
                    isSupport = true;
                    break;
                }
            }
        }

        LogUtil.log("TryCatchTransform: isSupport:" + isSupport);

        return isSupport && super.isWeavableClass(file);

    }

    @Override
    protected ClassVisitor getClassVisitor(ClassWriter classWriter) {
        return new TryCatchClassVisitor(classWriter, methodList);
    }

    @Override
    public String getName() {
        return "TryCatchTransform";
    }
}