package com.github.projectfluent.language.psi

import com.intellij.psi.tree.IElementType

class FluentTokenType(debugName: String) : IElementType(debugName, com.github.projectfluent.FluentLanguage.INSTANCE) {
    override fun toString(): String = "FluentTokenType.${super.toString()}"
}

