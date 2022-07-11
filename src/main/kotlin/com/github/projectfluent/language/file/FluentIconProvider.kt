package com.github.projectfluent.language.file

import com.intellij.ide.IconProvider
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import javax.swing.Icon

class FluentIconProvider : IconProvider() {
    override fun getIcon(psiElement: PsiElement, flags: Int): Icon? {
        val fileName = psiElement.containingFile.name

        return when {
            fileName.endsWith(".ftl") -> FluentFile
            else -> null
        }
    }

    companion object {
        val FluentFile = IconLoader.getIcon("/icons/ftl.svg", FluentIconProvider::class.java)
    }
}