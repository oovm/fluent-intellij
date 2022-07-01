package com.github.projectfluent.language.mixin

import com.github.projectfluent.ide.view.FluentItemPresentation
import com.github.projectfluent.language.psi.FluentAttribute
import com.github.projectfluent.language.psi.FluentElement
import com.github.projectfluent.language.psi.FluentMessage
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

abstract class MixinAttribute(node: ASTNode) : FluentElement(node),
    NavigatablePsiElement,
    PsiNameIdentifierOwner,
    FluentAttribute {
    override fun setName(name: String): PsiElement {
        TODO("Not yet implemented")
    }

    override fun getNameIdentifier(): PsiElement? {
        this.attributeID.let { return it }
    }

    override fun getPresentation(): ItemPresentation? {
        return nameIdentifier?.let { FluentItemPresentation(AllIcons.Nodes.Field, it.text) }
    }
}
