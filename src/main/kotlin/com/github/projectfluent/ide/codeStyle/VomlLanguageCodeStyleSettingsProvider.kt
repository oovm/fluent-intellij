package com.github.projectfluent.ide.codeStyle

import com.github.projectfluent.FluentLanguage
import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.psi.codeStyle.*

class VomlLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
    override fun getLanguage() = com.github.projectfluent.FluentLanguage.INSTANCE

    override fun getIndentOptionsEditor() = SmartIndentOptionsEditor()

    override fun createConfigurable(
        settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ): CodeStyleConfigurable {
        return object : CodeStyleAbstractConfigurable(
            settings,
            modelSettings,
            configurableDisplayName
        ) {
            override fun createPanel(settings: CodeStyleSettings?) = VomlCodeStyleMainPanel(currentSettings, settings)
        }
    }

    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
        when (settingsType) {
            SettingsType.COMMENTER_SETTINGS -> {
                consumer.showStandardOptions(
                    CodeStyleSettingsCustomizable.CommenterOption.BLOCK_COMMENT_AT_FIRST_COLUMN.name,
                    CodeStyleSettingsCustomizable.CommenterOption.LINE_COMMENT_ADD_SPACE.name,
                    CodeStyleSettingsCustomizable.CommenterOption.LINE_COMMENT_AT_FIRST_COLUMN.name
                )
            }
            SettingsType.WRAPPING_AND_BRACES_SETTINGS -> {
                consumer.showStandardOptions(
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.RIGHT_MARGIN.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.KEEP_LINE_BREAKS.name
                )
            }
            SettingsType.LANGUAGE_SPECIFIC -> {
                consumer.showStandardOptions()
            }
            else -> {}
        }
    }

    override fun customizeDefaults(
        commonSettings: CommonCodeStyleSettings,
        indentOptions: CommonCodeStyleSettings.IndentOptions
    ) {
        commonSettings.RIGHT_MARGIN = DEFAULT_RIGHT_MARGIN

        commonSettings.LINE_COMMENT_AT_FIRST_COLUMN = false
        commonSettings.LINE_COMMENT_ADD_SPACE = true
        commonSettings.BLOCK_COMMENT_AT_FIRST_COLUMN = false

        indentOptions.CONTINUATION_INDENT_SIZE = indentOptions.INDENT_SIZE
    }

    override fun getCodeSample(settingsType: SettingsType) =
        """@inherit user;

@include json "some/path/test.json" as json;
@include "https://example.org/test.voml" {
	external_key as external
}

[literals]
boolean = [true, false]

[literals.number]
integer  = 10cm
decimal  = 0.1m

[literals.string]
string   = "string"
escape   = "\n"

[keywords]
// remove this key-value pair
key = null

[scopes]
	[>a1]
	key1 = "scopes.a1.key1"
	[^a2]
	key2 = "scopes.a2.key2"
		[>b1]
		key3 = "a.a2.b1.key3"
	[<]
	key4 = "scopes.a2.key4"
		[>b1]
		key5 = "a.a2.b1.key5"
	[<a3]
	key = "scopes.a3.key"

---

connection_max.a = 5cm
v = [
	@merge(override)
	@merge_as_source(unset)
	@merge_as_target(ignore)
	a = Some(1)
    b = None()
]

[name]
  . a = 2
  * a
  * b
"""

    companion object {
        const val DEFAULT_RIGHT_MARGIN = 100
    }
}
