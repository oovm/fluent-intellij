package com.github.voml.jss_intellij.language.psi

import com.github.voml.jss_intellij._JssLexer
import com.intellij.lexer.FlexAdapter

class JssLexerAdapter : FlexAdapter(_JssLexer())
