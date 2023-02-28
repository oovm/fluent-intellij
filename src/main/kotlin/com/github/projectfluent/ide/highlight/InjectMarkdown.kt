package com.github.projectfluent.ide.highlight

import com.github.projectfluent.FluentLanguage
import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionContributor
import com.intellij.lang.injection.general.SimpleInjection
import com.intellij.psi.PsiElement

class InjectMarkdown : LanguageInjectionContributor {
    override fun getInjection(context: PsiElement): Injection? {
        if (context.text.startsWith("```")) {
            return SimpleInjection(FluentLanguage, "prefix", "suffix", "supportId")
        }
        return null
//        return SimpleInjection(FluentLanguage , "prefix", "suffix", "supportId" )
    }
}