package com.dxkit.click;

import com.android.build.api.transform.Transform;
import com.dxkit.base.BaseTransformPlugin;

import org.gradle.api.Project;

/**
 * 点击处理
 */
public class CheckClickPlugin extends BaseTransformPlugin {

    @Override
    public Transform getCustomTransform(Project project) {
        return new CheckClickTransform();
    }
}