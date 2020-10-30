package com.github.projectfluent.ide.highlight


import com.github.projectfluent.language.psi.FluentLexerAdapter
import com.github.projectfluent.language.psi.FluentTypes.*
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class FluentSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return FluentLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(getTokenColor(tokenType)?.textAttributesKey)
    }

    private fun getTokenColor(tokenType: IElementType): FluentColor? {
        return when (tokenType) {
            //
            // AS, SCHEMA, PROP -> JssColor.KEYWORD
            // ANNOTATION -> JssColor.ANNOTATION
            //
            PARENTHESIS_L, PARENTHESIS_R -> FluentColor.PARENTHESES
            BRACKET_L, BRACKET_R -> FluentColor.BRACKETS
            BRACE_L, BRACE_R -> FluentColor.BRACES
            COLON, EQ -> FluentColor.SET
            STAR -> FluentColor.STAR
            COMMA -> FluentColor.COMMA
            // atom
            INTEGER -> FluentColor.INTEGER
            DECIMAL -> FluentColor.DECIMAL

            TEXT_LINE, SELECTION_LINE -> FluentColor.TEXT
            STRING_ESCAPE -> FluentColor.STRING_ESCAPED
//            STRING -> AwslColor.STRING
            SYMBOL -> FluentColor.IDENTIFIER
            // 注释
            COMMENT_LINE -> FluentColor.LINE_COMMENT
//            COMMENT_BLOCK -> AwslColor.BLOCK_COMMENT
//            COMMENT_DOCUMENT -> AwslColor.DOC_COMMENT
            // 错误
            TokenType.BAD_CHARACTER -> FluentColor.BAD_CHARACTER
            else -> null
        }
    }
}
