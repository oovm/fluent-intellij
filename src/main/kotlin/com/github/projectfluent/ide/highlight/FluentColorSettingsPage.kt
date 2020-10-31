package com.github.projectfluent.ide.highlight

import com.github.projectfluent.FluentBundle
import com.github.projectfluent.ide.view.FluentIconProvider

import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class FluentColorSettingsPage : ColorSettingsPage {
    private val annotatorTags = FluentColor
        .values()
        .associateBy({ it.name }, { it.textAttributesKey })

    override fun getAttributeDescriptors() = FluentColor
        .values()
        .map { it.attributesDescriptor }
        .toTypedArray()

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = FluentBundle.message("filetype.name")

    override fun getIcon() = FluentIconProvider.FluentFile

    override fun getHighlighter() = FluentSyntaxHighlighter()

    override fun getAdditionalHighlightingTagToDescriptorMap() = annotatorTags

    override fun getDemoText() =
        """# References
hello   = Hello, world!
welcome = Welcome, { ${"$"}user }!
time-elapsed = Time elapsed: { NUMBER(${"$"}duration, maximumFractionDigits: 0) }s.
-brand-name = Firefox
installing = Installing { -brand-name }.

# Selectors
your-score = You scored {
    NUMBER(${"$"}score, minimumFractionDigits: 1) ->
        [0.0]   zero points. What happened?
       *[other] { NUMBER(${"$"}score, minimumFractionDigits: 1)} points.
    }

# Attributes
login-input = Predefined value
    .placeholder = email@example.com
    .aria-label = Login input value
    .title = Type your login email
"""


}
