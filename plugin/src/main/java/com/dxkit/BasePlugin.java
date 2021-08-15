package com.dxkit;

import com.android.build.api.transform.Transform;
import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 基类Plugin
 *
 * @author danxingxi 2021.8.15
 */
public abstract class BasePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        initExtension(project);

        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(getTransform(project));

    }

    protected abstract void initExtension(Project project);

    protected abstract Transform getTransform(Project project);
}
