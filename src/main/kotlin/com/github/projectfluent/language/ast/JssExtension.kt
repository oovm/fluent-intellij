package com.github.projectfluent.language.ast

import com.github.projectfluent.ide.formatter.FluentFormatSpace
import com.intellij.formatting.Block
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType


fun ASTNode?.isWhitespaceOrEmpty(): Boolean {
    return this == null || textLength == 0 || elementType == TokenType.WHITE_SPACE
}

fun Block.computeSpacing(child1: Block?, child2: Block, ctx: FluentFormatSpace): Spacing? {
    return ctx.spacingBuilder.getSpacing(this, child1, child2)
}


