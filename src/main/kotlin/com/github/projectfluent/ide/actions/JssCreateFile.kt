package com.github.projectfluent.ide.actions

import com.github.projectfluent.FluentBundle
import com.github.projectfluent.ide.view.FluentIconProvider
import com.github.projectfluent.ide.view.FluentIconProvider.Companion.FluentFile
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory

class JssCreateFile :
    CreateFileFromTemplateAction(name, description, FluentFile) {
    companion object {
        private val name = com.github.projectfluent.FluentBundle.message("action.create_file")
        private val description = com.github.projectfluent.FluentBundle.message("action.create_file.description")
    }


    override fun buildDialog(project: Project, directory: PsiDirectory, builder: Builder) {
        builder
            .setTitle(name)
            // See [resources/colors/fileTemplate]
            .addKind("Empty file", FluentFile, "Jss File")
    }


    override fun getActionName(directory: PsiDirectory, newName: String, templateName: String): String = name
}
