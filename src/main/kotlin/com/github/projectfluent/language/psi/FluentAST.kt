package com.github.projectfluent.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiRecursiveVisitor
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceWrapper

class FluentAST(node: ASTNode) : ASTWrapperPsiElement(node) {

}