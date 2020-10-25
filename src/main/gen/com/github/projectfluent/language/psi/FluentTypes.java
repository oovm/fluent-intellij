// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.projectfluent.language.psi_node.*;

public interface FluentTypes {

  IElementType ARGUMENT = new FluentElementType("ARGUMENT");
  IElementType ARGUMENT_LIST = new FluentElementType("ARGUMENT_LIST");
  IElementType ATTRIBUTE = new FluentElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_ACCESSOR = new FluentElementType("ATTRIBUTE_ACCESSOR");
  IElementType BLANK = new FluentElementType("BLANK");
  IElementType BLANK_BLOCK = new FluentElementType("BLANK_BLOCK");
  IElementType BLOCK_PLACEABLE = new FluentElementType("BLOCK_PLACEABLE");
  IElementType BLOCK_TEXT = new FluentElementType("BLOCK_TEXT");
  IElementType CALL_ARGUMENTS = new FluentElementType("CALL_ARGUMENTS");
  IElementType DEFAULT_VARIANT = new FluentElementType("DEFAULT_VARIANT");
  IElementType FUNCTION_REFERENCE = new FluentElementType("FUNCTION_REFERENCE");
  IElementType IDENTIFIER = new FluentElementType("IDENTIFIER");
  IElementType INLINE_EXPRESSION = new FluentElementType("INLINE_EXPRESSION");
  IElementType INLINE_PLACEABLE = new FluentElementType("INLINE_PLACEABLE");
  IElementType INLINE_TEXT = new FluentElementType("INLINE_TEXT");
  IElementType MESSAGE = new FluentElementType("MESSAGE");
  IElementType MESSAGE_REFERENCE = new FluentElementType("MESSAGE_REFERENCE");
  IElementType NAMED_ARGUMENT = new FluentElementType("NAMED_ARGUMENT");
  IElementType NUMBER_LITERAL = new FluentElementType("NUMBER_LITERAL");
  IElementType PATTERN = new FluentElementType("PATTERN");
  IElementType SELECT_EXPRESSION = new FluentElementType("SELECT_EXPRESSION");
  IElementType STRING_LITERAL = new FluentElementType("STRING_LITERAL");
  IElementType TERM = new FluentElementType("TERM");
  IElementType TERM_REFERENCE = new FluentElementType("TERM_REFERENCE");
  IElementType VARIABLE_REFERENCE = new FluentElementType("VARIABLE_REFERENCE");
  IElementType VARIANT = new FluentElementType("VARIANT");
  IElementType VARIANT_KEY = new FluentElementType("VARIANT_KEY");
  IElementType VARIANT_LIST = new FluentElementType("VARIANT_LIST");

  IElementType ACCENT = new FluentTokenType("^");
  IElementType ANGLE_L = new FluentTokenType("<");
  IElementType ANGLE_R = new FluentTokenType(">");
  IElementType AT = new FluentTokenType("@");
  IElementType BLANK_INLINE = new FluentTokenType("BLANK_INLINE");
  IElementType BRACE_L = new FluentTokenType("{");
  IElementType BRACE_R = new FluentTokenType("}");
  IElementType BRACKET_L = new FluentTokenType("[");
  IElementType BRACKET_R = new FluentTokenType("]");
  IElementType BYTE = new FluentTokenType("BYTE");
  IElementType COLON = new FluentTokenType(":");
  IElementType COMMA = new FluentTokenType(",");
  IElementType COMMENT = new FluentTokenType("Comment");
  IElementType COMMENT_BLOCK = new FluentTokenType("Comment Block");
  IElementType COMMENT_DOCUMENT = new FluentTokenType("Comment Document");
  IElementType COMMENT_LINE = new FluentTokenType("COMMENT_LINE");
  IElementType CRLF = new FluentTokenType("Newline");
  IElementType DECIMAL = new FluentTokenType("Decimal");
  IElementType DIGITS = new FluentTokenType("DIGITS");
  IElementType DOLLAR = new FluentTokenType("$");
  IElementType DOT = new FluentTokenType(".");
  IElementType EQ = new FluentTokenType("=");
  IElementType INDENT = new FluentTokenType("INDENT");
  IElementType INTEGER = new FluentTokenType("INTEGER");
  IElementType PARENTHESIS_L = new FluentTokenType("(");
  IElementType PARENTHESIS_R = new FluentTokenType(")");
  IElementType SEMICOLON = new FluentTokenType(";");
  IElementType SIGN = new FluentTokenType("SIGN");
  IElementType STAR = new FluentTokenType("*");
  IElementType STRING_CHAR = new FluentTokenType("String Character");
  IElementType STRING_ESCAPE = new FluentTokenType("String Escaped");
  IElementType STRING_QUOTE = new FluentTokenType("String Quote");
  IElementType SYMBOL = new FluentTokenType("Symbol");
  IElementType TEXT_CHAR = new FluentTokenType("Text Character");
  IElementType TEXT_LINE = new FluentTokenType("TEXT_LINE");
  IElementType URL = new FluentTokenType("Url");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new FluentArgumentNode(node);
      }
      else if (type == ARGUMENT_LIST) {
        return new FluentArgumentListNode(node);
      }
      else if (type == ATTRIBUTE) {
        return new FluentAttributeNode(node);
      }
      else if (type == ATTRIBUTE_ACCESSOR) {
        return new FluentAttributeAccessorNode(node);
      }
      else if (type == BLANK) {
        return new FluentBlankNode(node);
      }
      else if (type == BLANK_BLOCK) {
        return new FluentBlankBlockNode(node);
      }
      else if (type == BLOCK_PLACEABLE) {
        return new FluentBlockPlaceableNode(node);
      }
      else if (type == BLOCK_TEXT) {
        return new FluentBlockTextNode(node);
      }
      else if (type == CALL_ARGUMENTS) {
        return new FluentCallArgumentsNode(node);
      }
      else if (type == DEFAULT_VARIANT) {
        return new FluentDefaultVariantNode(node);
      }
      else if (type == FUNCTION_REFERENCE) {
        return new FluentFunctionReferenceNode(node);
      }
      else if (type == IDENTIFIER) {
        return new FluentIdentifierNode(node);
      }
      else if (type == INLINE_EXPRESSION) {
        return new FluentInlineExpressionNode(node);
      }
      else if (type == INLINE_PLACEABLE) {
        return new FluentInlinePlaceableNode(node);
      }
      else if (type == INLINE_TEXT) {
        return new FluentInlineTextNode(node);
      }
      else if (type == MESSAGE) {
        return new FluentMessageNode(node);
      }
      else if (type == MESSAGE_REFERENCE) {
        return new FluentMessageReferenceNode(node);
      }
      else if (type == NAMED_ARGUMENT) {
        return new FluentNamedArgumentNode(node);
      }
      else if (type == NUMBER_LITERAL) {
        return new FluentNumberLiteralNode(node);
      }
      else if (type == PATTERN) {
        return new FluentPatternNode(node);
      }
      else if (type == SELECT_EXPRESSION) {
        return new FluentSelectExpressionNode(node);
      }
      else if (type == STRING_LITERAL) {
        return new FluentStringLiteralNode(node);
      }
      else if (type == TERM) {
        return new FluentTermNode(node);
      }
      else if (type == TERM_REFERENCE) {
        return new FluentTermReferenceNode(node);
      }
      else if (type == VARIABLE_REFERENCE) {
        return new FluentVariableReferenceNode(node);
      }
      else if (type == VARIANT) {
        return new FluentVariantNode(node);
      }
      else if (type == VARIANT_KEY) {
        return new FluentVariantKeyNode(node);
      }
      else if (type == VARIANT_LIST) {
        return new FluentVariantListNode(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
