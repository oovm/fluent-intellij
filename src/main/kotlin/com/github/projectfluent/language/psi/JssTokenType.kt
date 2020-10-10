package com.github.projectfluent.language.psi

import com.github.projectfluent.FluentLanguage
import com.intellij.psi.tree.IElementType

class JssTokenType(debugName: String) : IElementType(debugName, com.github.projectfluent.FluentLanguage.INSTANCE) {
    override fun toString(): String = "JssTokenType.${super.toString()}"
}

