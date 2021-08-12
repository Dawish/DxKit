package com.dxkit.base

import com.android.build.api.transform.Transform
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 如果一直以来不成功，可以检查：
 * 是不是groovy文件系的有问题
 * 是不是gradle-plugins中的properties文件与使用时的apply名称不一致
 * 是不是build tools的版本问题
 */
abstract class BaseTransformPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create('DxKitExt', DxKitExtension.class);
        project.task('infoTask', type: InfoTask)

        // 注册方式1
        AppExtension appExtension = project.extensions.getByType(AppExtension)
        appExtension.registerTransform(getCustomTransform(project))
        // 注册之后会在TransformManager#addTransform中生成一个task.

        // 注册方式2
        // project.android.registerTransform(getCustomTransform())

    }

    /**
     * 需要注册的自定义Transform
     */
    abstract Transform getCustomTransform(Project project)
}