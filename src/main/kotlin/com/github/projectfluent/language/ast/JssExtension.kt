package com.github.projectfluent.language.ast

import com.github.projectfluent.ide.formatter.JssFormatterContext
import com.github.projectfluent.ide.formatter.JssFormattingModelBuilder
import com.github.projectfluent.language.psi.JssTypes
import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType

private fun JssAstBlock.computeIndent(child: ASTNode): Indent? {
    val isCornerChild = node.firstChildNode == child || node.lastChildNode == child
    return when (node.elementType) {
        JssTypes.BRACKET_BLOCK -> when {
            isCornerChild -> Indent.getNoneIndent()
            else -> Indent.getNormalIndent()
        }
        JssTypes.BRACE_BLOCK -> when {
            isCornerChild -> Indent.getNoneIndent()
            else -> Indent.getNormalIndent()
        }
        else -> Indent.getNoneIndent()
    }
}

fun JssAstBlock.buildChildren(): List<Block> {
    return node.getChildren(null)
        .filter { !it.isWhitespaceOrEmpty() }
        .map { childNode ->
            JssFormattingModelBuilder.createBlock(
                node = childNode,
                alignment = null,
                indent = computeIndent(childNode),
                wrap = null,
                ctx
            )
        }
}

private fun ASTNode?.isWhitespaceOrEmpty(): Boolean {
    return this == null || textLength == 0 || elementType == TokenType.WHITE_SPACE
}

fun Block.computeSpacing(child1: Block?, child2: Block, ctx: JssFormatterContext): Spacing? {
    return ctx.spacingBuilder.getSpacing(this, child1, child2)
}
