package com.github.oovm.fluent_intellij.ide.view

import com.github.oovm.fluent_intellij.FluentBundle
import com.github.oovm.fluent_intellij.FluentLanguage
import com.github.voml.jss_intellij.JssBundle
import com.github.voml.jss_intellij.JssLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class JssFileType private constructor() : LanguageFileType(FluentLanguage.INSTANCE) {
    override fun getName(): String = FluentBundle.message("filetype.name")

    override fun getDescription(): String = FluentBundle.message("filetype.description")

    override fun getDefaultExtension(): String = "ftl;awt;"

    override fun getIcon(): Icon = JssIcons.FILE

    companion object {
        @JvmStatic
        val INSTANCE = JssFileType()
    }
}