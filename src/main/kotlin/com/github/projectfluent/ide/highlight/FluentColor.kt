package com.github.projectfluent.ide.highlight

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.OptionsBundle
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.util.NlsContexts.AttributeDescriptor
import java.util.function.Supplier
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default


enum class FluentColor(humanName: Supplier<@AttributeDescriptor String>, default: TextAttributesKey? = null) {
    // 特殊关键词
    KEYWORD(OptionsBundle.messagePointer("options.language.defaults.keyword"), Default.KEYWORD),
    IDIOM_SYMBOL(com.github.projectfluent.FluentBundle.messagePointer("color.token.symbol.idiom"), Default.METADATA),
    IDIOM_MARK(com.github.projectfluent.FluentBundle.messagePointer("color.token.idiom_mark"), IDIOM_SYMBOL.textAttributesKey),
    PROP_MARK(com.github.projectfluent.FluentBundle.messagePointer("color.token.properties_mark"), KEYWORD.textAttributesKey),

    // 字面量
    NULL(com.github.projectfluent.FluentBundle.messagePointer("color.token.null"), Default.KEYWORD),
    BOOLEAN(com.github.projectfluent.FluentBundle.messagePointer("color.token.boolean"), Default.KEYWORD),
    DECIMAL(com.github.projectfluent.FluentBundle.messagePointer("color.token.decimal"), Default.NUMBER),
    INTEGER(com.github.projectfluent.FluentBundle.messagePointer("color.token.integer"), Default.NUMBER),
    STRING(com.github.projectfluent.FluentBundle.messagePointer("color.token.string"), Default.STRING),
    URL(com.github.projectfluent.FluentBundle.messagePointer("color.token.url"), STRING.textAttributesKey),

    // 标识符
    IDENTIFIER(OptionsBundle.messagePointer("options.language.defaults.identifier"), Default.IDENTIFIER),
    SYM_ANNO(com.github.projectfluent.FluentBundle.messagePointer("color.token.symbol.annotation"), Default.STATIC_METHOD),
    SYM_PROP(com.github.projectfluent.FluentBundle.messagePointer("color.token.symbol.property"), Default.STATIC_FIELD),
    SYM_SCHEMA(com.github.projectfluent.FluentBundle.messagePointer("color.token.symbol.schema"), Default.PREDEFINED_SYMBOL),

    //
    TYPE_HINT(com.github.projectfluent.FluentBundle.messagePointer("color.token.symbol.type"), Default.CLASS_NAME),

    // 标点符号
    PARENTHESES(OptionsBundle.messagePointer("options.language.defaults.parentheses"), Default.PARENTHESES),
    BRACKETS(OptionsBundle.messagePointer("options.language.defaults.brackets"), Default.BRACKETS),
    BRACES(OptionsBundle.messagePointer("options.language.defaults.braces"), Default.BRACES),
    DOT(OptionsBundle.messagePointer("options.language.defaults.dot"), Default.DOT),
    COMMA(OptionsBundle.messagePointer("options.language.defaults.comma"), Default.COMMA),
    SET(com.github.projectfluent.FluentBundle.messagePointer("color.token.set"), Default.OPERATION_SIGN),
    SEMICOLON(OptionsBundle.messagePointer("options.language.defaults.semicolon"), Default.SEMICOLON),

    // 注释
    LINE_COMMENT(OptionsBundle.messagePointer("options.language.defaults.line.comment"), Default.LINE_COMMENT),
    BLOCK_COMMENT(OptionsBundle.messagePointer("options.language.defaults.block.comment"), Default.BLOCK_COMMENT),
    DOC_COMMENT(OptionsBundle.messagePointer("options.language.defaults.doc.markup"), Default.DOC_COMMENT),

    // 错误
    BAD_CHARACTER(
        OptionsBundle.messagePointer("options.java.attribute.descriptor.bad.character"),
        HighlighterColors.BAD_CHARACTER
    ),

    // 废弃
    EXTENSION(OptionsBundle.messagePointer("options.language.defaults.metadata"), Default.METADATA),
    ;

    val textAttributesKey: TextAttributesKey = TextAttributesKey.createTextAttributesKey("voml.lang.$name", default)
    val attributesDescriptor: AttributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
    val testSeverity: HighlightSeverity = HighlightSeverity(name, HighlightSeverity.INFORMATION.myVal)
}
