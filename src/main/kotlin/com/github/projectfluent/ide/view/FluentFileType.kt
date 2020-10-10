package com.github.projectfluent.ide.view

import com.github.projectfluent.FluentBundle
import com.github.projectfluent.FluentLanguage.Companion
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class FluentFileType private constructor() : LanguageFileType(com.github.projectfluent.FluentLanguage.INSTANCE) {
    override fun getName(): String = FluentBundle.message("filetype.name")

    override fun getDescription(): String = FluentBundle.message("filetype.description")

    override fun getDefaultExtension(): String = "ftl;awt;"

    override fun getIcon(): Icon = FluentIcons.FILE

    companion object {
        @JvmStatic
        val INSTANCE = FluentFileType()
    }
}