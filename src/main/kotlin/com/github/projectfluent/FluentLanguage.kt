package com.github.projectfluent

import com.intellij.lang.Language
import org.jetbrains.annotations.NonNls

class FluentLanguage private constructor() : Language("Fluent") {
    companion object {
        @JvmStatic
        val INSTANCE = com.github.projectfluent.FluentLanguage()

        @NonNls
        const val BUNDLE = "messages.FluentBundle"
    }
}


