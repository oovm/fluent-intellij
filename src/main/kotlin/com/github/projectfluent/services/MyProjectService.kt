package com.github.projectfluent.services

import com.intellij.openapi.project.Project
import com.github.projectfluent.FluentBundle

class MyProjectService(project: Project) {

    init {
        println(FluentBundle.message("projectService", project.name))
    }
}
