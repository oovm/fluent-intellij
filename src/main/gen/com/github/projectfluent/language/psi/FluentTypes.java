// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.projectfluent.language.psi_node.*;

public interface FluentTypes {

  IElementType ARGUMENT = new FluentElementType("ARGUMENT");
  IElementType ATTRIBUTE = new FluentElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_ID = new FluentElementType("ATTRIBUTE_ID");
  IElementType BLOCK_PLACEABLE = new FluentElementType("BLOCK_PLACEABLE");
  IElementType BLOCK_TEXT = new FluentElementType("BLOCK_TEXT");
  IElementType CALL_ARGUMENTS = new FluentElementType("CALL_ARGUMENTS");
  IElementType DEFAULT_VARIANT = new FluentElementType("DEFAULT_VARIANT");
  IElementType FUNCTION_ID = new FluentElementType("FUNCTION_ID");
  IElementType FUNCTION_REFERENCE = new FluentElementType("FUNCTION_REFERENCE");
  IElementType INLINE_PLACEABLE = new FluentElementType("INLINE_PLACEABLE");
  IElementType INLINE_TEXT = new FluentElementType("INLINE_TEXT");
  IElementType MESSAGE = new FluentElementType("MESSAGE");
  IElementType MESSAGE_ID = new FluentElementType("MESSAGE_ID");
  IElementType MESSAGE_REFERENCE = new FluentElementType("MESSAGE_REFERENCE");
  IElementType NAMED_ARGUMENT = new FluentElementType("NAMED_ARGUMENT");
  IElementType NUMBER_LITERAL = new FluentElementType("NUMBER_LITERAL");
  IElementType PATTERN = new FluentElementType("PATTERN");
  IElementType SELECT_EXPRESSION = new FluentElementType("SELECT_EXPRESSION");
  IElementType STRING_LITERAL = new FluentElementType("STRING_LITERAL");
  IElementType TERM = new FluentElementType("TERM");
  IElementType TERM_ID = new FluentElementType("TERM_ID");
  IElementType TERM_REFERENCE = new FluentElementType("TERM_REFERENCE");
  IElementType VARIABLE_ID = new FluentElementType("VARIABLE_ID");
  IElementType VARIANT = new FluentElementType("VARIANT");
  IElementType VARIANT_KEY = new FluentElementType("VARIANT_KEY");

  IElementType ACCENT = new FluentTokenType("^");
  IElementType ANGLE_L = new FluentTokenType("<");
  IElementType ANGLE_R = new FluentTokenType(">");
  IElementType AT = new FluentTokenType("@");
  IElementType BLANK_BLOCK = new FluentTokenType("BLANK_BLOCK");
  IElementType BRACE_L = new FluentTokenType("{");
  IElementType BRACE_R = new FluentTokenType("}");
  IElementType BRACKET_L = new FluentTokenType("[");
  IElementType BRACKET_R = new FluentTokenType("]");
  IElementType COLON = new FluentTokenType(":");
  IElementType COMMA = new FluentTokenType(",");
  IElementType COMMENT = new FluentTokenType("Comment");
  IElementType COMMENT_BLOCK = new FluentTokenType("Comment Block");
  IElementType COMMENT_DOCUMENT = new FluentTokenType("Comment Document");
  IElementType COMMENT_LINE = new FluentTokenType("COMMENT_LINE");
  IElementType DECIMAL = new FluentTokenType("DECIMAL");
  IElementType DOLLAR = new FluentTokenType("$");
  IElementType DOT = new FluentTokenType(".");
  IElementType EQ = new FluentTokenType("=");
  IElementType HYPHEN = new FluentTokenType("-");
  IElementType INTEGER = new FluentTokenType("INTEGER");
  IElementType PARENTHESIS_L = new FluentTokenType("(");
  IElementType PARENTHESIS_R = new FluentTokenType(")");
  IElementType SELECTION_LINE = new FluentTokenType("SELECTION_LINE");
  IElementType SEMICOLON = new FluentTokenType(";");
  IElementType STAR = new FluentTokenType("*");
  IElementType STRING_CHAR = new FluentTokenType("String Character");
  IElementType STRING_ESCAPE = new FluentTokenType("String Escaped");
  IElementType STRING_QUOTE = new FluentTokenType("String Quote");
  IElementType SYMBOL = new FluentTokenType("Symbol");
  IElementType TEXT_LINE = new FluentTokenType("TEXT_LINE");
  IElementType TO = new FluentTokenType("->");
  IElementType URL = new FluentTokenType("Url");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new FluentArgumentNode(node);
      }
      else if (type == ATTRIBUTE) {
        return new FluentAttributeNode(node);
      }
      else if (type == ATTRIBUTE_ID) {
        return new FluentAttributeIDNode(node);
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
      else if (type == FUNCTION_ID) {
        return new FluentFunctionIDNode(node);
      }
      else if (type == FUNCTION_REFERENCE) {
        return new FluentFunctionReferenceNode(node);
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
      else if (type == MESSAGE_ID) {
        return new FluentMessageIDNode(node);
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
      else if (type == TERM_ID) {
        return new FluentTermIDNode(node);
      }
      else if (type == TERM_REFERENCE) {
        return new FluentTermReferenceNode(node);
      }
      else if (type == VARIABLE_ID) {
        return new FluentVariableIDNode(node);
      }
      else if (type == VARIANT) {
        return new FluentVariantNode(node);
      }
      else if (type == VARIANT_KEY) {
        return new FluentVariantKeyNode(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
