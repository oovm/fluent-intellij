package com.github.projectfluent.ide.highlight

import com.github.voml.jss_intellij.ide.file_view.JssFile
import com.github.voml.jss_intellij.language.psi.*
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.HighlightVisitor
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.elementType
import com.intellij.psi.util.nextLeaf

class JssHighlightVisitor : JssVisitor(), HighlightVisitor {
    private var infoHolder: HighlightInfoHolder? = null

    override fun visitSchemaStatement(o: JssSchemaStatement) {
        //
        val head = o.firstChild;
        highlight(head, JssColor.KEYWORD)
        //
        val prop = head.nextLeaf { it.elementType == JssTypes.SYMBOL }!!
        highlight(prop, JssColor.SYM_SCHEMA)

        super.visitSchemaStatement(o)
    }


    override fun visitPropertiesStatement(o: JssPropertiesStatement) {
        //
        val head = o.firstChild;
        when (head.elementType) {
            JssTypes.SYMBOL -> highlight(head, JssColor.KEYWORD)
        }
        //
        val prop = head.nextLeaf { it.elementType == JssTypes.SYMBOL }!!
        highlight(prop, JssColor.SYM_PROP)

        super.visitPropertiesStatement(o)
    }

    override fun visitDefStatement(o: JssDefStatement) {
        //
        val head = o.firstChild;
        when (head.elementType) {
            JssTypes.SYMBOL -> highlight(head, JssColor.KEYWORD)
        }
        //
        val prop = head.nextLeaf { it.elementType == JssTypes.SYMBOL }!!
        highlight(prop, JssColor.SYM_PROP)

        super.visitDefStatement(o)
    }

    override fun visitPropertiesMark(o: JssPropertiesMark) {
        highlight(o, JssColor.PROP_MARK)
    }

    override fun visitTypeHint(o: JssTypeHint) {
        val ty = o.lastChild;
        highlight(ty, JssColor.TYPE_HINT)

        super.visitTypeHint(o)
    }

    override fun visitIdiomMark(o: JssIdiomMark) {
        highlight(o, JssColor.IDIOM_MARK)
    }

    override fun visitIdiomSymbol(o: JssIdiomSymbol) {
        highlight(o, JssColor.IDIOM_SYMBOL)
    }


    override fun visitAnnoStatement(o: JssAnnoStatement) {
        highlight(o.firstChild, JssColor.SYM_ANNO)

        super.visitAnnoStatement(o)
    }


    override fun visitKvPair(o: JssKvPair) {
        highlight(o.firstChild, JssColor.SYM_PROP)
        super.visitKvPair(o)
    }

    override fun visitValue(o: JssValue) {
        val head = o.firstChild;
        when (head.elementType) {
            JssTypes.NULL -> highlight(head, JssColor.NULL)
            JssTypes.BOOLEAN -> highlight(head, JssColor.BOOLEAN)
            else -> super.visitValue(o)
        }
    }

    private fun highlight(element: PsiElement, color: JssColor) {
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

    override fun clone(): HighlightVisitor = JssHighlightVisitor()

    override fun suitableForFile(file: PsiFile): Boolean = file is JssFile

    override fun visit(element: PsiElement) = element.accept(this)
}