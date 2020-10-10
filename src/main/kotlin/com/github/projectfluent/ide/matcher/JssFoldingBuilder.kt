package com.github.projectfluent.ide.matcher

import com.github.voml.jss_intellij.ide.file_view.JssFile
import com.github.voml.jss_intellij.language.psi.JssTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class JssFoldingBuilder : CustomFoldingBuilder(), DumbAware {
    override fun buildLanguageFoldRegions(
        descriptors: MutableList<FoldingDescriptor>,
        root: PsiElement,
        document: Document,
        quick: Boolean
    ) {
        if (root !is JssFile) return
        val visitor = FluentFoldingVisitor(descriptors)
        PsiTreeUtil.processElements(root) {
            it.accept(visitor);
            true
        }
    }

    override fun getLanguagePlaceholderText(node: ASTNode, range: TextRange) =
        when (node.elementType) {
            JssTypes.BRACKET_BLOCK -> "[...]"
            JssTypes.BRACE_BLOCK -> "{...}"
            else -> "..."
        }

    override fun isRegionCollapsedByDefault(node: ASTNode) = false
}

