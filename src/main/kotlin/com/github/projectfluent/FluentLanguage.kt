package com.github.projectfluent

import com.intellij.lang.Language

class FluentLanguage private constructor() : Language(LanguageID) {
    companion object {
        @JvmStatic
        val INSTANCE = FluentLanguage()

        const val Bundle = "messages.FluentBundle"

        const val LanguageID = "Fluent"
        const val Extensions = "ftl;awt;"

    }
}


