package com.github.oovm.fluent_intellij.services

import com.intellij.openapi.project.Project
import com.github.oovm.fluent_intellij.FluentBundle

class MyProjectService(project: Project) {

    init {
        println(FluentBundle.message("projectService", project.name))
    }
}
