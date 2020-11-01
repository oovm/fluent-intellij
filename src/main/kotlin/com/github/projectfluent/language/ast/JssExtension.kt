package com.github.projectfluent.language.ast

import com.github.projectfluent.ide.formatter.JssFormatterContext
import com.github.projectfluent.ide.formatter.FluentFormattingModelBuilder

import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType

private fun FluentAstBlock.computeIndent(child: ASTNode): Indent? {
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

fun FluentAstBlock.buildChildren(): List<Block> {
    return node.getChildren(null)
        .filter { !it.isWhitespaceOrEmpty() }
        .map { childNode ->
            FluentFormattingModelBuilder.createBlock(
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
