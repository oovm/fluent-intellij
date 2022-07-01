package com.github.projectfluent.language.mixin

import com.github.projectfluent.ide.view.FluentItemPresentation
import com.github.projectfluent.ide.view.FluentStructureViewElement
import com.github.projectfluent.language.psi.FluentElement
import com.github.projectfluent.language.psi.FluentMessage
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationRequest
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

abstract class MixinMessage(node: ASTNode) : FluentElement(node),
    NavigatablePsiElement,
    PsiNameIdentifierOwner,
    FluentMessage {
    override fun setName(name: String): PsiElement {
        TODO("Not yet implemented")
    }

    override fun getNameIdentifier(): PsiElement? {
        this.messageID.let { return it }
    }

    override fun getPresentation(): ItemPresentation? {
        return nameIdentifier?.let { FluentItemPresentation(AllIcons.Nodes.Field, it.text) }
    }

    fun getChildView(): MutableList<FluentStructureViewElement> {
        val out = mutableListOf<FluentStructureViewElement>()
        this.attributeList.forEach {
            out.add(FluentStructureViewElement(it as NavigatablePsiElement))
        }
        return out
    }
}
