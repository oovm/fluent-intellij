package com.github.oovm.fluent_intellij

import com.intellij.lang.Language
import org.jetbrains.annotations.NonNls

class FluentLanguage private constructor() : Language("Fluent") {
    companion object {
        @JvmStatic
        val INSTANCE = FluentLanguage()

        @NonNls
        const val BUNDLE = "messages.FluentBundle"
    }
}


