package com.dxkit.click

import com.android.build.api.transform.Transform
import com.dxkit.base.BaseTransformPlugin
import org.gradle.api.Project

/**
 * 点击处理
 */
class CheckClickPlugin extends BaseTransformPlugin {

    @Override
    Transform getCustomTransform(Project project) {
        String[] searchStrings = project.DxKitExt.searchStrings
        println "afterEvaluate searchStrings : ${project.DxKitExt.searchStrings}"
        return new CheckClickTransform(searchStrings)
    }
}