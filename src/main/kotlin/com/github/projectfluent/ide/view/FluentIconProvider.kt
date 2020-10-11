package com.github.projectfluent.ide.view

import com.intellij.ide.IconProvider
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import javax.swing.Icon

class FluentIconProvider : IconProvider() {
    override fun getIcon(psiElement: PsiElement, flags: Int): Icon? {
        val fileName = psiElement.containingFile.name

        return when {
            fileName.endsWith(".ftl") -> Fluent
            fileName.endsWith(".awt") -> AwtFile
            else -> null
        }
    }

    companion object {
        val Fluent = IconLoader.getIcon("/icons/aw.svg", FluentIconProvider::class.java)
        val AwtFile = IconLoader.getIcon("/icons/awc.svg", FluentIconProvider::class.java)
    }
}