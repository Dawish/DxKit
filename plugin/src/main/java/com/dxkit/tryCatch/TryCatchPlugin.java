package com.dxkit.tryCatch;

import com.android.build.api.transform.Transform;
import com.dxkit.BasePlugin;

import org.gradle.api.Project;

/**
 * 为指定的方法添加try catch
 *
 * @author danxingxi
 */
public class TryCatchPlugin extends BasePlugin {

    @Override
    protected void initExtension(Project project) {
        project.getExtensions().create("tryCatchExtension", TryCatchExtension.class);
    }

    @Override
    public Transform getTransform(Project project) {
        return new TryCatchTransform(project);
    }
}