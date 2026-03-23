package com.github.projectfluent.ide.matcher


import com.github.projectfluent.language.psi.*
import com.github.projectfluent.language.psi.nodes.FluentInlinePlaceableNode
import com.github.projectfluent.language.psi.nodes.FluentMessageNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class FluentFoldingVisitor(private val descriptors: MutableList<FoldingDescriptor>) : FluentRecursiveVisitor() {

    override fun visitInlinePlaceable(o: FluentInlinePlaceableNode) {
        descriptors += FoldingDescriptor(o.node, TextRange(o.firstChild.textRange.endOffset, o.lastChild.textRange.startOffset))
        super.visitInlinePlaceable(o)
    }

    override fun visitMessage(o: FluentMessageNode) {
        //TODO: folding end of =
        super.visitMessage(o)
    }



    private fun fold(element: PsiElement) {
        descriptors += FoldingDescriptor(element.node, element.textRange)
    }
}
