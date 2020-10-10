package com.github.projectfluent.ide.annotator

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

abstract class CheckerBase : AnnotatorBase() {
    protected abstract fun check(element: PsiElement, holder: AnnotationHolder): CheckerAnnotatorResult
    override fun annotateInternal(element: PsiElement, holder: AnnotationHolder) {
        when (val result = check(element, holder)) {
            CheckerAnnotatorResult.Ok -> {}
            is CheckerAnnotatorResult.Error -> {
                val (errorText, subRange) = result
                holder
                    .newAnnotation(HighlightSeverity.ERROR, errorText)
                    .range(subRange)
                    .create()
            }
        }
    }
}