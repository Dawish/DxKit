package com.dxkit;

import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.asm.BaseWeaver;

import org.gradle.api.Project;

/**
 * 基类Transform
 *
 * @author danxingxi 2021.8.15
 */
public abstract class BaseTransform extends HunterTransform {

    public BaseTransform(Project project) {
        super(project);
        this.bytecodeWeaver = getWeaver();
    }

    protected abstract BaseWeaver getWeaver();

}
