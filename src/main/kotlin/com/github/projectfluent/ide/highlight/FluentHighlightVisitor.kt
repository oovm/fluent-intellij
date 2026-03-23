package com.github.projectfluent.ide.highlight


import com.github.projectfluent.ide.highlight.FluentHighlightColor.*
import com.github.projectfluent.language.psi.FluentVisitor
import com.github.projectfluent.language.psi.nodes.*
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.HighlightVisitor
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class FluentHighlightVisitor : FluentVisitor(), HighlightVisitor {
    private var infoHolder: HighlightInfoHolder? = null

    override fun visitMessageID(o: FluentMessageIDNode) {
        highlight(o, KEY)
    }

    override fun visitTermID(o: FluentTermIDNode) {
        highlight(o, KEY)
    }

    override fun visitAttributeID(o: FluentAttributeIDNode) {
        highlight(o, KEY)
    }

    override fun visitVariableID(o: FluentVariableIDNode) {
        highlight(o, SYM_VARIABLE)
    }

    override fun visitFunctionID(o: FluentFunctionIDNode) {
        highlight(o, SYM_FUNCTION)
    }

    private fun highlight(element: PsiElement, color: FluentHighlightColor) {
        val builder = HighlightInfo.newHighlightInfo(HighlightInfoType.INFORMATION)
        builder.textAttributes(color.textAttributesKey)
        builder.range(element)

        infoHolder?.add(builder.create())
    }

    override fun analyze(file: PsiFile, whole: Boolean, holder: HighlightInfoHolder, action: Runnable): Boolean {
        infoHolder = holder
        action.run()

        return true
    }

    override fun clone(): HighlightVisitor = FluentHighlightVisitor()

    override fun suitableForFile(file: PsiFile): Boolean = file is FluentFileNode

    override fun visit(element: PsiElement) = element.accept(this)
}