package com.dxkit.click;

import com.dxkit.BaseTransform;
import com.quinn.hunter.transform.asm.BaseWeaver;

import org.gradle.api.Project;

/**
 * onClick相关的操作
 *
 * @author danxingxi
 */
public class ClickTransform extends BaseTransform {

    public ClickTransform(Project project) {
        super(project);
    }

    @Override
    public String getName() {
        return "ClickTransform";
    }

    @Override
    protected BaseWeaver getWeaver() {
        return new ClickWeaver();
    }
}