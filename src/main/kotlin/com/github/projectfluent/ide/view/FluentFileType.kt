package com.github.projectfluent.ide.view

import com.github.projectfluent.FluentBundle
import com.github.projectfluent.FluentLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class FluentFileType private constructor() : LanguageFileType(FluentLanguage.INSTANCE) {
    override fun getName(): String = FluentLanguage.LanguageID

    override fun getDescription(): String = FluentBundle.message("filetype.description")

    override fun getDefaultExtension(): String = FluentLanguage.Extensions

    override fun getIcon(): Icon = FluentIconProvider.FluentFile

    companion object {
        @JvmStatic
        val INSTANCE = FluentFileType()
    }
}