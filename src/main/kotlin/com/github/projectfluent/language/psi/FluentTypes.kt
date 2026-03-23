package com.github.projectfluent.language.psi

import com.github.projectfluent.language.psi.nodes.FluentAttributeIDNode
import com.github.projectfluent.language.psi.nodes.FluentAttributeNode
import com.github.projectfluent.language.psi.nodes.FluentFunctionIDNode
import com.github.projectfluent.language.psi.nodes.FluentInlinePlaceableNode
import com.github.projectfluent.language.psi.nodes.FluentMessageIDNode
import com.github.projectfluent.language.psi.nodes.FluentMessageNode
import com.github.projectfluent.language.psi.nodes.FluentTermIDNode
import com.github.projectfluent.language.psi.nodes.FluentTermNode
import com.github.projectfluent.language.psi.nodes.FluentVariableIDNode
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType

object FluentTypes {
    // Tokens
    val COMMENT_LINE: IElementType = FluentTokenType("COMMENT_LINE")
    val SYMBOL: IElementType = FluentTokenType("SYMBOL")
    val STRING_QUOTE: IElementType = FluentTokenType("STRING_QUOTE")
    val STRING_CHAR: IElementType = FluentTokenType("STRING_CHAR")
    val STRING_ESCAPE: IElementType = FluentTokenType("STRING_ESCAPE")
    val SELECTION_LINE: IElementType = FluentTokenType("SELECTION_LINE")
    val PARENTHESIS_L: IElementType = FluentTokenType("PARENTHESIS_L")
    val PARENTHESIS_R: IElementType = FluentTokenType("PARENTHESIS_R")
    val BRACKET_L: IElementType = FluentTokenType("BRACKET_L")
    val BRACKET_R: IElementType = FluentTokenType("BRACKET_R")
    val BRACE_L: IElementType = FluentTokenType("BRACE_L")
    val BRACE_R: IElementType = FluentTokenType("BRACE_R")
    val ANGLE_L: IElementType = FluentTokenType("ANGLE_L")
    val ANGLE_R: IElementType = FluentTokenType("ANGLE_R")
    val ACCENT: IElementType = FluentTokenType("ACCENT")
    val EQ: IElementType = FluentTokenType("EQ")
    val COLON: IElementType = FluentTokenType("COLON")
    val SEMICOLON: IElementType = FluentTokenType("SEMICOLON")
    val COMMA: IElementType = FluentTokenType("COMMA")
    val DOLLAR: IElementType = FluentTokenType("DOLLAR")
    val DOT: IElementType = FluentTokenType("DOT")
    val STAR: IElementType = FluentTokenType("STAR")
    val TO: IElementType = FluentTokenType("TO")
    val HYPHEN: IElementType = FluentTokenType("HYPHEN")
    val INTEGER: IElementType = FluentTokenType("INTEGER")
    val DECIMAL: IElementType = FluentTokenType("DECIMAL")
    val BLANK_BLOCK: IElementType = FluentTokenType("BLANK_BLOCK")
    val TEXT_LINE: IElementType = FluentTokenType("TEXT_LINE")
    val STRING_LITERAL: IElementType = FluentTokenType("STRING_LITERAL")
    val NUMBER_LITERAL: IElementType = FluentTokenType("NUMBER_LITERAL")
    val WHITESPACE: IElementType = FluentTokenType("WHITESPACE")

    // Elements
    val FLUENT: IElementType = FluentElementType("FLUENT")
    val ENTRY: IElementType = FluentElementType("ENTRY")
    val MESSAGE: IElementType = FluentElementType("MESSAGE")
    val TERM: IElementType = FluentElementType("TERM")
    val ATTRIBUTE: IElementType = FluentElementType("ATTRIBUTE")
    val PATTERN: IElementType = FluentElementType("PATTERN")
    val PATTERN_ELEMENT: IElementType = FluentElementType("PATTERN_ELEMENT")
    val INLINE_TEXT: IElementType = FluentElementType("INLINE_TEXT")
    val BLOCK_TEXT: IElementType = FluentElementType("BLOCK_TEXT")
    val INLINE_PLACEABLE: IElementType = FluentElementType("INLINE_PLACEABLE")
    val BLOCK_PLACEABLE: IElementType = FluentElementType("BLOCK_PLACEABLE")
    val EXPRESSION: IElementType = FluentElementType("EXPRESSION")
    val FUNCTION_REFERENCE: IElementType = FluentElementType("FUNCTION_REFERENCE")
    val MESSAGE_REFERENCE: IElementType = FluentElementType("MESSAGE_REFERENCE")
    val TERM_REFERENCE: IElementType = FluentElementType("TERM_REFERENCE")
    val SELECT_EXPRESSION: IElementType = FluentElementType("SELECT_EXPRESSION")
    val VARIANT: IElementType = FluentElementType("VARIANT")
    val DEFAULT_VARIANT: IElementType = FluentElementType("DEFAULT_VARIANT")
    val VARIANT_KEY: IElementType = FluentElementType("VARIANT_KEY")
    val CALL_ARGUMENTS: IElementType = FluentElementType("CALL_ARGUMENTS")
    val ARGUMENT: IElementType = FluentElementType("ARGUMENT")
    val NAMED_ARGUMENT: IElementType = FluentElementType("NAMED_ARGUMENT")
    val MESSAGE_ID: IElementType = FluentElementType("MESSAGE_ID")
    val TERM_ID: IElementType = FluentElementType("TERM_ID")
    val ATTRIBUTE_ID: IElementType = FluentElementType("ATTRIBUTE_ID")
    val VARIABLE_ID: IElementType = FluentElementType("VARIABLE_ID")
    val FUNCTION_ID: IElementType = FluentElementType("FUNCTION_ID")

    // Factory
    object Factory {
        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                MESSAGE -> FluentMessageNode(node)
                TERM -> FluentTermNode(node)
                ATTRIBUTE -> FluentAttributeNode(node)
                MESSAGE_ID -> FluentMessageIDNode(node)
                TERM_ID -> FluentTermIDNode(node)
                ATTRIBUTE_ID -> FluentAttributeIDNode(node)
                VARIABLE_ID -> FluentVariableIDNode(node)
                FUNCTION_ID -> FluentFunctionIDNode(node)
                INLINE_PLACEABLE -> FluentInlinePlaceableNode(node)
                else -> FluentElement(node)
            }
        }
    }
}
