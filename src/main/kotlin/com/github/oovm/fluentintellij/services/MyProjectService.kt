package com.github.oovm.fluentintellij.services

import com.intellij.openapi.project.Project
import com.github.oovm.fluentintellij.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
