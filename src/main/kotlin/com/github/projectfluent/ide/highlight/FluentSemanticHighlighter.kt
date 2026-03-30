package com.github.projectfluent.ide.highlight


import com.github.projectfluent.language.psi.FluentRecursiveVisitor
import com.github.projectfluent.language.psi.nodes.FluentAttributeIDNode
import com.github.projectfluent.language.psi.nodes.FluentFileNode
import com.github.projectfluent.language.psi.nodes.FluentFunctionIDNode
import com.github.projectfluent.language.psi.nodes.FluentMessageIDNode
import com.github.projectfluent.language.psi.nodes.FluentTermIDNode
import com.github.projectfluent.language.psi.nodes.FluentVariableIDNode
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.HighlightVisitor
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class FluentSemanticHighlighter : FluentRecursiveVisitor(), HighlightVisitor {
    private var infoHolder: HighlightInfoHolder? = null

    override fun visitMessageID(messageID: FluentMessageIDNode) {
        highlight(messageID, FluentHighlightColor.SYM_MESSAGE)
    }

    override fun visitTermID(termID: FluentTermIDNode) {
        highlight(termID, FluentHighlightColor.SYM_TERM)
    }

    override fun visitAttributeID(attributeID: FluentAttributeIDNode) {
        highlight(attributeID, FluentHighlightColor.SYM_ATTRIBUTE)
    }

    override fun visitVariableID(variableID: FluentVariableIDNode) {
        highlight(variableID, FluentHighlightColor.SYM_VARIABLE)
    }

    override fun visitFunctionID(functionID: FluentFunctionIDNode) {
        highlight(functionID, FluentHighlightColor.SYM_FUNCTION)
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
        visit(file)

        return true
    }

    override fun clone(): HighlightVisitor = FluentSemanticHighlighter()

    override fun suitableForFile(file: PsiFile): Boolean = file is FluentFileNode

    override fun visit(element: PsiElement) {
        element.accept(this)
    }
}
