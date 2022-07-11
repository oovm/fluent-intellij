package com.github.projectfluent.ide.view

import com.github.projectfluent.language.file.FluentFile
import com.github.projectfluent.language.psi_node.FluentAttributeNode
import com.github.projectfluent.language.psi_node.FluentMessageNode
import com.github.projectfluent.language.psi_node.FluentTermNode
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil

class FluentStructureViewElement(private val node: NavigatablePsiElement) :
    StructureViewTreeElement,
    SortableTreeElement {
    override fun getValue(): Any {
        return node
    }

    override fun navigate(requestFocus: Boolean) {
        node.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return node.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return node.canNavigateToSource()
    }

    override fun getAlphaSortKey(): String {
        val name = node.name
        return name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        val presentation = node.presentation
        return presentation ?: PresentationData()
    }

    override fun getChildren(): Array<out TreeElement> = when (node) {
        is FluentFile -> getChildOfType(
            FluentMessageNode::class.java,
            FluentTermNode::class.java,
        )
        is FluentMessageNode, is FluentTermNode -> getChildOfType(
            FluentAttributeNode::class.java,
        )
        is FluentAttributeNode -> arrayOf()
        else -> getChildOfType(
            NavigatablePsiElement::class.java,
        )
    }

    private fun getChildOfType(vararg classes: Class<out NavigatablePsiElement>): Array<FluentStructureViewElement> {
        return PsiTreeUtil.getChildrenOfAnyType(node, *classes)
            .map { FluentStructureViewElement(it) }
            .toTypedArray()
    }
}

