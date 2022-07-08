package com.github.projectfluent.language.file

import com.github.projectfluent.FluentBundle
import com.github.projectfluent.FluentLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class FluentFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FluentLanguage) {
    override fun getFileType(): FileType = FluentFileType.INSTANCE

    override fun toString(): String = FluentBundle.message("action.create_file")
}