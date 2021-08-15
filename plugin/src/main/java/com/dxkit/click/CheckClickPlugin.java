package com.dxkit.click;

import com.android.build.api.transform.Transform;
import com.dxkit.BasePlugin;

import org.gradle.api.Project;

/**
 * 点击处理
 *
 * @author danxingxi
 */
public class CheckClickPlugin extends BasePlugin {

    @Override
    protected void initExtension(Project project) {
        project.getExtensions().create("clickExtension", ClickExtension.class);
    }

    @Override
    protected Transform getTransform(Project project) {
        return new ClickTransform(project);
    }

}