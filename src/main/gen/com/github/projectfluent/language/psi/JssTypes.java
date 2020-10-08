// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.projectfluent.language.psi_node.*;

public interface JssTypes {

  IElementType ANNO_STATEMENT = new JssElementType("ANNO_STATEMENT");
  IElementType ARRAY = new JssElementType("ARRAY");
  IElementType BOOLEAN = new JssElementType("BOOLEAN");
  IElementType BRACE_BLOCK = new JssElementType("BRACE_BLOCK");
  IElementType BRACKET_BLOCK = new JssElementType("BRACKET_BLOCK");
  IElementType DEF_STATEMENT = new JssElementType("DEF_STATEMENT");
  IElementType IDIOM_MARK = new JssElementType("IDIOM_MARK");
  IElementType IDIOM_STATEMENT = new JssElementType("IDIOM_STATEMENT");
  IElementType IDIOM_SYMBOL = new JssElementType("IDIOM_SYMBOL");
  IElementType KV_PAIR = new JssElementType("KV_PAIR");
  IElementType NULL = new JssElementType("NULL");
  IElementType OBJECT = new JssElementType("OBJECT");
  IElementType PROPERTIES_BLOCK = new JssElementType("PROPERTIES_BLOCK");
  IElementType PROPERTIES_KEY = new JssElementType("PROPERTIES_KEY");
  IElementType PROPERTIES_MARK = new JssElementType("PROPERTIES_MARK");
  IElementType PROPERTIES_STATEMENT = new JssElementType("PROPERTIES_STATEMENT");
  IElementType SCHEMA_STATEMENT = new JssElementType("SCHEMA_STATEMENT");
  IElementType STRING_INLINE = new JssElementType("STRING_INLINE");
  IElementType STRING_MULTI = new JssElementType("STRING_MULTI");
  IElementType TYPE_HINT = new JssElementType("TYPE_HINT");
  IElementType URL_MAYBE_VALID = new JssElementType("URL_MAYBE_VALID");
  IElementType VALUE = new JssElementType("VALUE");

  IElementType ACCENT = new JssTokenType("^");
  IElementType ANGLE_L = new JssTokenType("<");
  IElementType ANGLE_R = new JssTokenType(">");
  IElementType AT = new JssTokenType("@");
  IElementType BRACE_L = new JssTokenType("{");
  IElementType BRACE_R = new JssTokenType("}");
  IElementType BRACKET_L = new JssTokenType("[");
  IElementType BRACKET_R = new JssTokenType("]");
  IElementType BYTE = new JssTokenType("BYTE");
  IElementType COLON = new JssTokenType(":");
  IElementType COMMA = new JssTokenType(",");
  IElementType COMMENT = new JssTokenType("COMMENT");
  IElementType COMMENT_BLOCK = new JssTokenType("COMMENT_BLOCK");
  IElementType COMMENT_DOCUMENT = new JssTokenType("COMMENT_DOCUMENT");
  IElementType DECIMAL = new JssTokenType("DECIMAL");
  IElementType DOLLAR = new JssTokenType("$");
  IElementType DOT = new JssTokenType(".");
  IElementType EQ = new JssTokenType("=");
  IElementType INTEGER = new JssTokenType("INTEGER");
  IElementType PARENTHESIS_L = new JssTokenType("(");
  IElementType PARENTHESIS_R = new JssTokenType(")");
  IElementType SEMICOLON = new JssTokenType(";");
  IElementType SIGN = new JssTokenType("SIGN");
  IElementType STAR = new JssTokenType("*");
  IElementType STRING = new JssTokenType("STRING");
  IElementType SYMBOL = new JssTokenType("SYMBOL");
  IElementType URL = new JssTokenType("URL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ANNO_STATEMENT) {
        return new JssAnnoStatementNode(node);
      }
      else if (type == ARRAY) {
        return new JssArrayNode(node);
      }
      else if (type == BOOLEAN) {
        return new JssBooleanNode(node);
      }
      else if (type == BRACE_BLOCK) {
        return new JssBraceBlockNode(node);
      }
      else if (type == BRACKET_BLOCK) {
        return new JssBracketBlockNode(node);
      }
      else if (type == DEF_STATEMENT) {
        return new JssDefStatementNode(node);
      }
      else if (type == IDIOM_MARK) {
        return new JssIdiomMarkNode(node);
      }
      else if (type == IDIOM_STATEMENT) {
        return new JssIdiomStatementNode(node);
      }
      else if (type == IDIOM_SYMBOL) {
        return new JssIdiomSymbolNode(node);
      }
      else if (type == KV_PAIR) {
        return new JssKvPairNode(node);
      }
      else if (type == NULL) {
        return new JssNullNode(node);
      }
      else if (type == OBJECT) {
        return new JssObjectNode(node);
      }
      else if (type == PROPERTIES_BLOCK) {
        return new JssPropertiesBlockNode(node);
      }
      else if (type == PROPERTIES_KEY) {
        return new JssPropertiesKeyNode(node);
      }
      else if (type == PROPERTIES_MARK) {
        return new JssPropertiesMarkNode(node);
      }
      else if (type == PROPERTIES_STATEMENT) {
        return new JssPropertiesStatementNode(node);
      }
      else if (type == SCHEMA_STATEMENT) {
        return new JssSchemaStatementNode(node);
      }
      else if (type == STRING_INLINE) {
        return new JssStringInlineNode(node);
      }
      else if (type == STRING_MULTI) {
        return new JssStringMultiNode(node);
      }
      else if (type == TYPE_HINT) {
        return new JssTypeHintNode(node);
      }
      else if (type == URL_MAYBE_VALID) {
        return new JssUrlMaybeValidNode(node);
      }
      else if (type == VALUE) {
        return new JssValueNode(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
