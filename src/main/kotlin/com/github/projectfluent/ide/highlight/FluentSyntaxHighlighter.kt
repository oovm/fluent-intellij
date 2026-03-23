package com.github.projectfluent.ide.highlight


import com.github.projectfluent.language.psi.FluentLexer
import com.github.projectfluent.language.psi.FluentTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class FluentSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return FluentLexer()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(getTokenColor(tokenType)?.textAttributesKey)
    }

    private fun getTokenColor(tokenType: IElementType): FluentHighlightColor? {
        return when (tokenType) {
            //
            // AS, SCHEMA, PROP -> JssColor.KEYWORD
            // ANNOTATION -> JssColor.ANNOTATION
            //
            FluentTypes.PARENTHESIS_L, FluentTypes.PARENTHESIS_R -> FluentHighlightColor.PARENTHESES
            FluentTypes.BRACKET_L, FluentTypes.BRACKET_R -> FluentHighlightColor.BRACKETS
            FluentTypes.BRACE_L, FluentTypes.BRACE_R -> FluentHighlightColor.BRACES
            FluentTypes.COLON, FluentTypes.EQ -> FluentHighlightColor.SET
            FluentTypes.STAR -> FluentHighlightColor.STAR
            FluentTypes.COMMA -> FluentHighlightColor.COMMA
            // atom
            FluentTypes.INTEGER -> FluentHighlightColor.INTEGER
            FluentTypes.DECIMAL -> FluentHighlightColor.DECIMAL

            FluentTypes.TEXT_LINE, FluentTypes.SELECTION_LINE -> FluentHighlightColor.TEXT
            FluentTypes.STRING_QUOTE, FluentTypes.STRING_CHAR -> FluentHighlightColor.STRING
            FluentTypes.STRING_ESCAPE -> FluentHighlightColor.STRING_ESCAPED
//            STRING -> AwslColor.STRING
            FluentTypes.SYMBOL -> FluentHighlightColor.IDENTIFIER
            // Keys
            FluentTypes.MESSAGE_ID, FluentTypes.TERM_ID, FluentTypes.ATTRIBUTE_ID -> FluentHighlightColor.KEY
            // 注释
            FluentTypes.COMMENT_LINE -> FluentHighlightColor.LINE_COMMENT
//            COMMENT_BLOCK -> AwslColor.BLOCK_COMMENT
//            COMMENT_DOCUMENT -> AwslColor.DOC_COMMENT
            // 错误
            TokenType.BAD_CHARACTER -> FluentHighlightColor.BAD_CHARACTER
            else -> null
        }
    }
}
