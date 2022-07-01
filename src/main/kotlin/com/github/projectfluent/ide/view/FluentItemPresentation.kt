package com.github.projectfluent.ide.view

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import javax.swing.Icon

class FluentItemPresentation(private val icon: Icon?, val text: String) : ItemPresentation {
    override fun getPresentableText(): String = text
    override fun getIcon(unused: Boolean): Icon = when (icon) {
        null -> AllIcons.Nodes.Unknown
        else -> icon
    }
}