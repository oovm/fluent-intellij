package com.github.projectfluent.language.mixin

import com.github.projectfluent.ide.view.FluentItemPresentation
import com.github.projectfluent.language.psi.FluentAttribute
import com.github.projectfluent.language.psi.FluentElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

abstract class MixinAttribute(node: ASTNode) : FluentElement(node),
    PsiNameIdentifierOwner,
    FluentAttribute {
    override fun setName(name: String): PsiElement {
        TODO("Not yet implemented")
    }

    override fun getNameIdentifier(): PsiElement {
        return this.attributeID
    }

    override fun getPresentation(): ItemPresentation? {
        return FluentItemPresentation(AllIcons.Nodes.Method, nameIdentifier.text)
    }
}
