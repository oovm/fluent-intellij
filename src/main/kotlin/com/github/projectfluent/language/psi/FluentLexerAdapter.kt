package com.github.projectfluent.language.psi

import com.github.projectfluent.language._FluentLexer
import com.intellij.lexer.FlexAdapter

class FluentLexerAdapter : FlexAdapter(_FluentLexer())
