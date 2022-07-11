package com.github.projectfluent.ide.highlight


import com.github.projectfluent.ide.highlight.FluentHighlightColor.*
import com.github.projectfluent.language.file.FluentFile
import com.github.projectfluent.language.psi.*
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.HighlightVisitor
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class FluentHighlightVisitor : FluentVisitor(), HighlightVisitor {
    private var infoHolder: HighlightInfoHolder? = null

    override fun visitMessageID(o: FluentMessageID) {
        highlight(o, SYM_MESSAGE)
    }

    override fun visitTermID(o: FluentTermID) {
        highlight(o, SYM_TERM)
    }

    override fun visitAttributeID(o: FluentAttributeID) {
        highlight(o, SYM_ATTRIBUTE)
    }

    override fun visitVariableID(o: FluentVariableID) {
        highlight(o, SYM_VARIABLE)
    }

    override fun visitFunctionID(o: FluentFunctionID) {
        highlight(o, SYM_FUNCTION)
    }

//    override fun visitSchemaStatement(o: JssSchemaStatement) {
//        //
//        val head = o.firstChild;
//        highlight(head, FluentColor.KEYWORD)
//        //
//        val prop = head.nextLeaf { it.elementType == JssTypes.SYMBOL }!!
//        highlight(prop, FluentColor.SYM_SCHEMA)
//
//        super.visitSchemaStatement(o)
//    }


    private fun highlight(element: PsiElement, color: FluentHighlightColor) {
        val builder = HighlightInfo.newHighlightInfo(HighlightInfoType.INFORMATION)
        builder.textAttributes(color.textAttributesKey)
        builder.range(element)

        infoHolder?.add(builder.create())
    }

    override fun analyze(
        file: PsiFile,
        updateWholeFile: Boolean,
        holder: HighlightInfoHolder,
        action: Runnable
    ): Boolean {
        infoHolder = holder
        action.run()

        return true
    }

    override fun clone(): HighlightVisitor = FluentHighlightVisitor()

    override fun suitableForFile(file: PsiFile): Boolean = file is FluentFile

    override fun visit(element: PsiElement) = element.accept(this)
}