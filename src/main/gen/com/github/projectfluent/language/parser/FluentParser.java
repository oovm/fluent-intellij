// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.projectfluent.language.psi.FluentTypes.*;
import static com.github.projectfluent.language.psi.FluentParserExtension.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class FluentParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return Fluent(b, l + 1);
  }

  /* ********************************************************** */
  // fluent_item*
  static boolean Fluent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fluent")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fluent_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fluent", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "." identifier EQ pattern
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && identifier(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && pattern(b, l + 1);
    exit_section_(b, m, ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // inline_text?
  public static boolean block_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_text")) return false;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_TEXT, "<block text>");
    inline_text(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // message
  // 	|term
  static boolean fluent_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fluent_item")) return false;
    boolean r;
    r = message(b, l + 1);
    if (!r) r = term(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // SYMBOL
  public static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYMBOL);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // TEXT_CHAR+
  public static boolean inline_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline_text")) return false;
    if (!nextTokenIs(b, TEXT_CHAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TEXT_CHAR);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, TEXT_CHAR)) break;
      if (!empty_element_parsed_guard_(b, "inline_text", c)) break;
    }
    exit_section_(b, m, INLINE_TEXT, r);
    return r;
  }

  /* ********************************************************** */
  // identifier EQ  (attribute+ | pattern attribute*)
  public static boolean message(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "message")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && message_2(b, l + 1);
    exit_section_(b, m, MESSAGE, r);
    return r;
  }

  // attribute+ | pattern attribute*
  private static boolean message_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "message_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = message_2_0(b, l + 1);
    if (!r) r = message_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // attribute+
  private static boolean message_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "message_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "message_2_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // pattern attribute*
  private static boolean message_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "message_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pattern(b, l + 1);
    r = r && message_2_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // attribute*
  private static boolean message_2_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "message_2_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "message_2_1_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // pattern_element+
  public static boolean pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PATTERN, "<pattern>");
    r = pattern_element(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!pattern_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "pattern", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // inline_text
  // 	| block_text
  static boolean pattern_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pattern_element")) return false;
    boolean r;
    r = inline_text(b, l + 1);
    if (!r) r = block_text(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // STRING_QUOTE (STRING_CHAR|STRING_ESCAPE)* STRING_QUOTE
  public static boolean string(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string")) return false;
    if (!nextTokenIs(b, STRING_QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_QUOTE);
    r = r && string_1(b, l + 1);
    r = r && consumeToken(b, STRING_QUOTE);
    exit_section_(b, m, STRING, r);
    return r;
  }

  // (STRING_CHAR|STRING_ESCAPE)*
  private static boolean string_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!string_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "string_1", c)) break;
    }
    return true;
  }

  // STRING_CHAR|STRING_ESCAPE
  private static boolean string_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_1_0")) return false;
    boolean r;
    r = consumeToken(b, STRING_CHAR);
    if (!r) r = consumeToken(b, STRING_ESCAPE);
    return r;
  }

  /* ********************************************************** */
  // "-" identifier EQ pattern attribute*
  public static boolean term(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TERM, "<term>");
    r = consumeToken(b, "-");
    r = r && identifier(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && pattern(b, l + 1);
    r = r && term_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attribute*
  private static boolean term_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "term_4", c)) break;
    }
    return true;
  }

}
