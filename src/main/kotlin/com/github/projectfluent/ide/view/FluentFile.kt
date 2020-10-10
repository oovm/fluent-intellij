package com.github.projectfluent.ide.view

import com.github.projectfluent.FluentLanguage.Companion
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class FluentFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, com.github.projectfluent.FluentLanguage.INSTANCE) {
    override fun getFileType(): FileType = FluentFileType.INSTANCE

    override fun toString(): String = com.github.projectfluent.FluentBundle.message("action.create_file")
}