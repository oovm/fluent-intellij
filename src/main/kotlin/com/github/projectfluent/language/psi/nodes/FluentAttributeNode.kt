package com.github.projectfluent.language.psi.nodes

import com.github.projectfluent.language.psi.FluentElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

class FluentAttributeNode(node: ASTNode) : FluentElement(node) {
    val attributeID: FluentAttributeIDNode
        get() = findChildByClass(FluentAttributeIDNode::class.java) ?: throw IllegalStateException("Attribute must have an ID")

    fun getNameIdentifier(): PsiElement? {
        return attributeID
    }

    fun setName(name: String): PsiElement {
        // TODO: Implement name change functionality
        return this
    }

    override fun getName(): String? {
        return attributeID.text
    }
}
