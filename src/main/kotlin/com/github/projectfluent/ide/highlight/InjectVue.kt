package com.github.projectfluent.ide.highlight

import com.github.projectfluent.FluentLanguage
import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.xml.XmlTextImpl
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlText

class InjectVue : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context is XmlTextImpl) {
            val tag = context.parent;
            if (tag is XmlTag && tag.name == "fluent") {
                val range = context.textRange.shiftLeft(context.startOffset);
                registrar.startInjecting(FluentLanguage)
                registrar.addPlace(null, null, context, range)
                registrar.doneInjecting()
            }
        }
    }

    override fun elementsToInjectIn(): MutableList<out Class<out PsiElement>> {
        return mutableListOf(XmlText::class.java)
    }
}