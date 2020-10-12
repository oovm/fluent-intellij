// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.projectfluent.language.psi_node.*;

public interface FluentTypes {

  IElementType ANNO_STATEMENT = new FluentElementType("ANNO_STATEMENT");
  IElementType ARRAY = new FluentElementType("ARRAY");
  IElementType BOOLEAN = new FluentElementType("BOOLEAN");
  IElementType BRACE_BLOCK = new FluentElementType("BRACE_BLOCK");
  IElementType BRACKET_BLOCK = new FluentElementType("BRACKET_BLOCK");
  IElementType DEF_STATEMENT = new FluentElementType("DEF_STATEMENT");
  IElementType IDIOM_MARK = new FluentElementType("IDIOM_MARK");
  IElementType IDIOM_STATEMENT = new FluentElementType("IDIOM_STATEMENT");
  IElementType IDIOM_SYMBOL = new FluentElementType("IDIOM_SYMBOL");
  IElementType KV_PAIR = new FluentElementType("KV_PAIR");
  IElementType NULL = new FluentElementType("NULL");
  IElementType OBJECT = new FluentElementType("OBJECT");
  IElementType PROPERTIES_BLOCK = new FluentElementType("PROPERTIES_BLOCK");
  IElementType PROPERTIES_KEY = new FluentElementType("PROPERTIES_KEY");
  IElementType PROPERTIES_MARK = new FluentElementType("PROPERTIES_MARK");
  IElementType PROPERTIES_STATEMENT = new FluentElementType("PROPERTIES_STATEMENT");
  IElementType SCHEMA_STATEMENT = new FluentElementType("SCHEMA_STATEMENT");
  IElementType STRING_INLINE = new FluentElementType("STRING_INLINE");
  IElementType STRING_MULTI = new FluentElementType("STRING_MULTI");
  IElementType TYPE_HINT = new FluentElementType("TYPE_HINT");
  IElementType URL_MAYBE_VALID = new FluentElementType("URL_MAYBE_VALID");
  IElementType VALUE = new FluentElementType("VALUE");

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
  IElementType STRING = new FluentTokenType("String");
  IElementType SYMBOL = new FluentTokenType("Symbol");
  IElementType URL = new FluentTokenType("Url");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ANNO_STATEMENT) {
        return new FluentAnnoStatementNode(node);
      }
      else if (type == ARRAY) {
        return new FluentArrayNode(node);
      }
      else if (type == BOOLEAN) {
        return new FluentBooleanNode(node);
      }
      else if (type == BRACE_BLOCK) {
        return new FluentBraceBlockNode(node);
      }
      else if (type == BRACKET_BLOCK) {
        return new FluentBracketBlockNode(node);
      }
      else if (type == DEF_STATEMENT) {
        return new FluentDefStatementNode(node);
      }
      else if (type == IDIOM_MARK) {
        return new FluentIdiomMarkNode(node);
      }
      else if (type == IDIOM_STATEMENT) {
        return new FluentIdiomStatementNode(node);
      }
      else if (type == IDIOM_SYMBOL) {
        return new FluentIdiomSymbolNode(node);
      }
      else if (type == KV_PAIR) {
        return new FluentKvPairNode(node);
      }
      else if (type == NULL) {
        return new FluentNullNode(node);
      }
      else if (type == OBJECT) {
        return new FluentObjectNode(node);
      }
      else if (type == PROPERTIES_BLOCK) {
        return new FluentPropertiesBlockNode(node);
      }
      else if (type == PROPERTIES_KEY) {
        return new FluentPropertiesKeyNode(node);
      }
      else if (type == PROPERTIES_MARK) {
        return new FluentPropertiesMarkNode(node);
      }
      else if (type == PROPERTIES_STATEMENT) {
        return new FluentPropertiesStatementNode(node);
      }
      else if (type == SCHEMA_STATEMENT) {
        return new FluentSchemaStatementNode(node);
      }
      else if (type == STRING_INLINE) {
        return new FluentStringInlineNode(node);
      }
      else if (type == STRING_MULTI) {
        return new FluentStringMultiNode(node);
      }
      else if (type == TYPE_HINT) {
        return new FluentTypeHintNode(node);
      }
      else if (type == URL_MAYBE_VALID) {
        return new FluentUrlMaybeValidNode(node);
      }
      else if (type == VALUE) {
        return new FluentValueNode(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
