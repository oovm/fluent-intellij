package com.github.projectfluent.language.ast

import com.github.projectfluent.ide.formatter.FluentFormatBlock
import com.github.projectfluent.ide.formatter.FluentFormatSpace
import com.github.projectfluent.ide.formatter.FluentFormatBuilder

import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType

private fun FluentFormatBlock.computeIndent(child: ASTNode): Indent? {
    val isCornerChild = node.firstChildNode == child || node.lastChildNode == child
    return when (node.elementType) {
//        BRACKET_BLOCK -> when {
//            isCornerChild -> Indent.getNoneIndent()
//            else -> Indent.getNormalIndent()
//        }
//        BRACE_BLOCK -> when {
//            isCornerChild -> Indent.getNoneIndent()
//            else -> Indent.getNormalIndent()
//        }
        else -> Indent.getNoneIndent()
    }
}

fun ASTNode?.isWhitespaceOrEmpty(): Boolean {
    return this == null || textLength == 0 || elementType == TokenType.WHITE_SPACE
}

fun Block.computeSpacing(child1: Block?, child2: Block, ctx: FluentFormatSpace): Spacing? {
    return ctx.spacingBuilder.getSpacing(this, child1, child2)
}
