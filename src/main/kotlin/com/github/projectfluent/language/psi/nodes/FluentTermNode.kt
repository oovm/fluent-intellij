package com.github.projectfluent.language.psi.nodes

import com.github.projectfluent.language.psi.FluentElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

class FluentTermNode(node: ASTNode) : FluentElement(node) {
    val termID: FluentTermIDNode
        get() = findChildByClass(FluentTermIDNode::class.java) ?: throw IllegalStateException("Term must have an ID")

    fun getNameIdentifier(): PsiElement? {
        return termID
    }

    fun setName(name: String): PsiElement {
        // TODO: Implement name change functionality
        return this
    }

    override fun getName(): String? {
        return termID.text
    }
}
