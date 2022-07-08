package com.github.projectfluent.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.NavigatablePsiElement

open class FluentElement(node: ASTNode) : ASTWrapperPsiElement(node)