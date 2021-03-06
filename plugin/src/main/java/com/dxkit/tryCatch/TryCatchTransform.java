package com.dxkit.tryCatch;

import com.dxkit.BaseTransform;
import com.quinn.hunter.transform.asm.BaseWeaver;

import org.gradle.api.Project;

/**
 * onClick相关的操作
 *
 * @author danxingxi
 */
public class TryCatchTransform extends BaseTransform {

    public TryCatchTransform(Project project) {
        super(project);
    }

    @Override
    public String getName() {
        return "TryCatchTransform";
    }

    @Override
    protected BaseWeaver getWeaver() {
        return new TryCatchWeaver();
    }
}