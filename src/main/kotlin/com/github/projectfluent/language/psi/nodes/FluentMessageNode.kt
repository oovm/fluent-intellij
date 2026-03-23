package com.github.projectfluent.language.psi.nodes

import com.github.projectfluent.language.psi.FluentElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

class FluentMessageNode(node: ASTNode) : FluentElement(node) {
    val messageID: FluentMessageIDNode
        get() = findChildByClass(FluentMessageIDNode::class.java) ?: throw IllegalStateException("Message must have an ID")

    fun getNameIdentifier(): PsiElement? {
        return messageID
    }

    fun setName(name: String): PsiElement {
        // TODO: Implement name change functionality
        return this
    }

    override fun getName(): String? {
        return messageID.text
    }
}
