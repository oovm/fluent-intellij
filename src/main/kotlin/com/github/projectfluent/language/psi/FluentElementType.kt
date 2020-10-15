package com.github.projectfluent.language.psi

import com.intellij.psi.tree.IElementType

class FluentElementType(debugName: String) : IElementType(debugName, com.github.projectfluent.FluentLanguage.INSTANCE)
