package com.github.projectfluent.language.ast

import com.github.projectfluent.language.file.FluentFile
import com.intellij.openapi.project.Project

class ASTNodeFactory(project: Project) {
    fun createFile(text: String): FluentFile {
        createFromText
        return createFromText(project, text)
    }
}