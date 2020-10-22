package com.github.projectfluent.ide.view

import com.github.projectfluent.language.psi.FluentMessage
import com.github.projectfluent.language.psi_node.FluentMessageNode
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil

class FluentStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        class Builder : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return FluentStructureViewModel(psiFile)
            }
        }
        return Builder()
    }
}


class FluentStructureViewModel(psiFile: PsiFile?) :
    StructureViewModelBase(psiFile!!, FluentStructureViewElement(psiFile)),
    ElementInfoProvider {
    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is FluentMessage
    }
}

class FluentStructureViewElement(private val myElement: NavigatablePsiElement) :
    StructureViewTreeElement,
    SortableTreeElement {
    override fun getValue(): Any {
        return myElement
    }

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return myElement.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return myElement.canNavigateToSource()
    }

    override fun getAlphaSortKey(): String {
        val name = myElement.name
        return name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        val presentation = myElement.presentation
        return presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (myElement is FluentFile) {
            val properties: List<FluentMessage> = PsiTreeUtil.getChildrenOfTypeAsList(
                myElement,
                FluentMessage::class.java
            )
            val treeElements: MutableList<TreeElement> = ArrayList(properties.size)
            for (property in properties) {
                treeElements.add(FluentStructureViewElement(property as NavigatablePsiElement))
            }
            return treeElements.toTypedArray()
        }
        return TreeElement.EMPTY_ARRAY
    }
}
