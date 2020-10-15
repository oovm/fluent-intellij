package com.github.projectfluent.language.psi

import com.github.projectfluent.language.psi.JssVisitor
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveVisitor

open class FluentRecursiveVisitor : FluentVisitor(), PsiRecursiveVisitor {
    override fun visitElement(element: PsiElement) {
        ProgressManager.checkCanceled()
        element.acceptChildren(this)
    }
}
