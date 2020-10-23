// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.projectfluent.language.psi_node.*;

public interface FluentTypes {

  IElementType ATTRIBUTE = new FluentElementType("ATTRIBUTE");
  IElementType BLOCK_TEXT = new FluentElementType("BLOCK_TEXT");
  IElementType IDENTIFIER = new FluentElementType("IDENTIFIER");
  IElementType INLINE_TEXT = new FluentElementType("INLINE_TEXT");
  IElementType MESSAGE = new FluentElementType("MESSAGE");
  IElementType PATTERN = new FluentElementType("PATTERN");
  IElementType STRING = new FluentElementType("STRING");
  IElementType TERM = new FluentElementType("TERM");

  IElementType ACCENT = new FluentTokenType("^");
  IElementType ANGLE_L = new FluentTokenType("<");
  IElementType ANGLE_R = new FluentTokenType(">");
  IElementType AT = new FluentTokenType("@");
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
  IElementType DECIMAL = new FluentTokenType("DECIMAL");
  IElementType DOLLAR = new FluentTokenType("$");
  IElementType DOT = new FluentTokenType(".");
  IElementType EQ = new FluentTokenType("=");
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
  IElementType URL = new FluentTokenType("Url");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new FluentAttributeNode(node);
      }
      else if (type == BLOCK_TEXT) {
        return new FluentBlockTextNode(node);
      }
      else if (type == IDENTIFIER) {
        return new FluentIdentifierNode(node);
      }
      else if (type == INLINE_TEXT) {
        return new FluentInlineTextNode(node);
      }
      else if (type == MESSAGE) {
        return new FluentMessageNode(node);
      }
      else if (type == PATTERN) {
        return new FluentPatternNode(node);
      }
      else if (type == STRING) {
        return new FluentStringNode(node);
      }
      else if (type == TERM) {
        return new FluentTermNode(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
