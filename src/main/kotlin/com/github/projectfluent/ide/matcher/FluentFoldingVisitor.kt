package com.github.projectfluent.ide.matcher

import com.github.voml.jss_intellij.language.psi.JssBraceBlock
import com.github.voml.jss_intellij.language.psi.JssBracketBlock
import com.github.voml.jss_intellij.language.psi.FluentRecursiveVisitor
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.psi.PsiElement

class FluentFoldingVisitor(private val descriptors: MutableList<FoldingDescriptor>) : FluentRecursiveVisitor() {

//    override fun visitSchemaStatement(o: JssSchemaStatement) {
//        val block = o.childOfType<Br>()!!
//        fold(block)
//        super.visitSchemaStatement(o)
//    }

    override fun visitBraceBlock(o: JssBraceBlock) {
        fold(o)
        super.visitBraceBlock(o)
    }

    override fun visitBracketBlock(o: JssBracketBlock) {
        fold(o)
        super.visitBracketBlock(o)
    }


//    override fun visitObjectBody(o: VomlObjectBody) {
//        if (o.objectEntryList.isNotEmpty()) {
//            fold(o)
//            super.visitObjectBody(o)
//        }
//    }
//
//    override fun visitTupleBody(o: VomlTupleBody) {
//        if (o.valueList.isNotEmpty()) {
//            fold(o)
//            super.visitTupleBody(o)
//        }
//    }
//
//    override fun visitMap(o: VomlMap) {
//        if (o.mapEntryList.isNotEmpty()) {
//            fold(o)
//            super.visitMap(o)
//        }
//    }


    private fun fold(element: PsiElement) {
        descriptors += FoldingDescriptor(element.node, element.textRange)
    }
}
