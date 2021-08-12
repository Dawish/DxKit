package com.dxkit.base

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * gradlew infoTask
 */
class InfoTask extends DefaultTask {

    @TaskAction
    void printInfo(){
        println "isForbidStickClick : ${project.DxKitExt.isForbidStickClick},\n clickUtilPath: ${project.DxKitExt.clickUtilPath}"
        println "searchStrings : ${project.DxKitExt.searchStrings}"
    }

}
