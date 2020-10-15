package com.github.projectfluent.ide.formatter

import com.github.projectfluent.FluentLanguage
import com.github.projectfluent.language.psi.JssTypes
import com.intellij.formatting.SpacingBuilder
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.tree.TokenSet

data class JssFormatterContext(
    val commonSettings: CommonCodeStyleSettings,
    val spacingBuilder: SpacingBuilder
) {
    companion object {
        fun create(settings: CodeStyleSettings): JssFormatterContext {
            val commonSettings = settings.getCommonSettings(com.github.projectfluent.FluentLanguage.INSTANCE)
            return JssFormatterContext(commonSettings, createSpacingBuilder(commonSettings))
        }

        private val remove_space_before = TokenSet.create(
            JssTypes.PARENTHESIS_R,
            JssTypes.BRACKET_R,
            JssTypes.COMMA,
            JssTypes.SEMICOLON
        )
        private val remove_space_after = TokenSet.create(
            JssTypes.PARENTHESIS_L,
            JssTypes.BRACKET_L,
            JssTypes.COLON,
        )
        private val remove_space_newline_after = TokenSet.create(JssTypes.IDIOM_MARK, JssTypes.PROPERTIES_MARK)

        private val separators = TokenSet.create(JssTypes.COMMA, JssTypes.SEMICOLON)

        private fun createSpacingBuilder(commonSettings: CommonCodeStyleSettings): SpacingBuilder {
            val custom = SpacingBuilder(commonSettings)
                // ,
                .after(separators).spacing(1, 1, 0, commonSettings.KEEP_LINE_BREAKS, 0)
                // k: v
                .after(JssTypes.COLON).spacing(1, 1, 0, false, 0)
                // k = v
                .around(JssTypes.EQ).spacing(1, 1, 0, commonSettings.KEEP_LINE_BREAKS, 0)
                // SpacingBuilder { }
                .before(JssTypes.PROPERTIES_BLOCK).spacing(1, 1, 0, commonSettings.KEEP_LINE_BREAKS, 0)

            return custom
                .before(remove_space_before).spaceIf(false)
                .after(remove_space_after).spaceIf(false)
                .after(remove_space_newline_after).spacing(0, 0, 0, false, 0)
        }
    }
}