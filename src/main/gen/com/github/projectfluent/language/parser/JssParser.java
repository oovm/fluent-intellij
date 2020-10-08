// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.projectfluent.language.psi.JssTypes.*;
import static com.github.projectfluent.language.psi.JssParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JssParser implements PsiParser, LightPsiParser {

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
  // statement*
  static boolean Fluent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fluent")) return false;
    while (true) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fluent", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRING | SYMBOL
  static boolean anno_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anno_key")) return false;
    if (!nextTokenIs(b, "", STRING, SYMBOL)) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, SYMBOL);
    return r;
  }

  /* ********************************************************** */
  // anno_key set value
  public static boolean anno_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anno_statement")) return false;
    if (!nextTokenIs(b, "<anno statement>", STRING, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNO_STATEMENT, "<anno statement>");
    r = anno_key(b, l + 1);
    r = r && set(b, l + 1);
    r = r && value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // <<bracket_block value ignore>>
  public static boolean array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array")) return false;
    if (!nextTokenIs(b, BRACKET_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bracket_block(b, l + 1, JssParser::value, JssParser::ignore);
    exit_section_(b, m, ARRAY, r);
    return r;
  }

  /* ********************************************************** */
  // "true" | "false"
  public static boolean boolean_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_$")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN, "<boolean $>");
    r = consumeToken(b, "true");
    if (!r) r = consumeToken(b, "false");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BRACE_L (<<item>>|<<sp>>)* BRACE_R
  public static boolean brace_block(PsiBuilder b, int l, Parser _item, Parser _sp) {
    if (!recursion_guard_(b, l, "brace_block")) return false;
    if (!nextTokenIs(b, BRACE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACE_L);
    r = r && brace_block_1(b, l + 1, _item, _sp);
    r = r && consumeToken(b, BRACE_R);
    exit_section_(b, m, BRACE_BLOCK, r);
    return r;
  }

  // (<<item>>|<<sp>>)*
  private static boolean brace_block_1(PsiBuilder b, int l, Parser _item, Parser _sp) {
    if (!recursion_guard_(b, l, "brace_block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!brace_block_1_0(b, l + 1, _item, _sp)) break;
      if (!empty_element_parsed_guard_(b, "brace_block_1", c)) break;
    }
    return true;
  }

  // <<item>>|<<sp>>
  private static boolean brace_block_1_0(PsiBuilder b, int l, Parser _item, Parser _sp) {
    if (!recursion_guard_(b, l, "brace_block_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _item.parse(b, l);
    if (!r) r = _sp.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BRACKET_L [<<item>> (<<sp>> <<item>>)* [<<sp>>]] BRACKET_R
  public static boolean bracket_block(PsiBuilder b, int l, Parser _item, Parser _sp) {
    if (!recursion_guard_(b, l, "bracket_block")) return false;
    if (!nextTokenIs(b, BRACKET_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_L);
    r = r && bracket_block_1(b, l + 1, _item, _sp);
    r = r && consumeToken(b, BRACKET_R);
    exit_section_(b, m, BRACKET_BLOCK, r);
    return r;
  }

  // [<<item>> (<<sp>> <<item>>)* [<<sp>>]]
  private static boolean bracket_block_1(PsiBuilder b, int l, Parser _item, Parser _sp) {
    if (!recursion_guard_(b, l, "bracket_block_1")) return false;
    bracket_block_1_0(b, l + 1, _item, _sp);
    return true;
  }

  // <<item>> (<<sp>> <<item>>)* [<<sp>>]
  private static boolean bracket_block_1_0(PsiBuilder b, int l, Parser _item, Parser _sp) {
    if (!recursion_guard_(b, l, "bracket_block_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _item.parse(b, l);
    r = r && bracket_block_1_0_1(b, l + 1, _sp, _item);
    r = r && bracket_block_1_0_2(b, l + 1, _sp);
    exit_section_(b, m, null, r);
    return r;
  }

  // (<<sp>> <<item>>)*
  private static boolean bracket_block_1_0_1(PsiBuilder b, int l, Parser _sp, Parser _item) {
    if (!recursion_guard_(b, l, "bracket_block_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!bracket_block_1_0_1_0(b, l + 1, _sp, _item)) break;
      if (!empty_element_parsed_guard_(b, "bracket_block_1_0_1", c)) break;
    }
    return true;
  }

  // <<sp>> <<item>>
  private static boolean bracket_block_1_0_1_0(PsiBuilder b, int l, Parser _sp, Parser _item) {
    if (!recursion_guard_(b, l, "bracket_block_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _sp.parse(b, l);
    r = r && _item.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  // [<<sp>>]
  private static boolean bracket_block_1_0_2(PsiBuilder b, int l, Parser _sp) {
    if (!recursion_guard_(b, l, "bracket_block_1_0_2")) return false;
    _sp.parse(b, l);
    return true;
  }

  /* ********************************************************** */
  // ("def"|"define"|"definition") properties_key [type_hint] [properties_block]
  public static boolean def_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "def_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DEF_STATEMENT, "<def statement>");
    r = def_statement_0(b, l + 1);
    r = r && properties_key(b, l + 1);
    r = r && def_statement_2(b, l + 1);
    r = r && def_statement_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "def"|"define"|"definition"
  private static boolean def_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "def_statement_0")) return false;
    boolean r;
    r = consumeToken(b, "def");
    if (!r) r = consumeToken(b, "define");
    if (!r) r = consumeToken(b, "definition");
    return r;
  }

  // [type_hint]
  private static boolean def_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "def_statement_2")) return false;
    type_hint(b, l + 1);
    return true;
  }

  // [properties_block]
  private static boolean def_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "def_statement_3")) return false;
    properties_block(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // EQ | COLON
  static boolean eq(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eq")) return false;
    if (!nextTokenIs(b, "", COLON, EQ)) return false;
    boolean r;
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, COLON);
    return r;
  }

  /* ********************************************************** */
  // DOLLAR
  public static boolean idiom_mark(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "idiom_mark")) return false;
    if (!nextTokenIs(b, DOLLAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR);
    exit_section_(b, m, IDIOM_MARK, r);
    return r;
  }

  /* ********************************************************** */
  // idiom_mark idiom_symbol [COLON] value
  public static boolean idiom_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "idiom_statement")) return false;
    if (!nextTokenIs(b, DOLLAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = idiom_mark(b, l + 1);
    r = r && idiom_symbol(b, l + 1);
    r = r && idiom_statement_2(b, l + 1);
    r = r && value(b, l + 1);
    exit_section_(b, m, IDIOM_STATEMENT, r);
    return r;
  }

  // [COLON]
  private static boolean idiom_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "idiom_statement_2")) return false;
    consumeToken(b, COLON);
    return true;
  }

  /* ********************************************************** */
  // SYMBOL
  public static boolean idiom_symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "idiom_symbol")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYMBOL);
    exit_section_(b, m, IDIOM_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // SEMICOLON | COMMA
  static boolean ignore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ignore")) return false;
    if (!nextTokenIs(b, "", COMMA, SEMICOLON)) return false;
    boolean r;
    r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, COMMA);
    return r;
  }

  /* ********************************************************** */
  // (string_inline|SYMBOL) eq value
  public static boolean kv_pair(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kv_pair")) return false;
    if (!nextTokenIs(b, "<kv pair>", STRING, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KV_PAIR, "<kv pair>");
    r = kv_pair_0(b, l + 1);
    r = r && eq(b, l + 1);
    r = r && value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // string_inline|SYMBOL
  private static boolean kv_pair_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kv_pair_0")) return false;
    boolean r;
    r = string_inline(b, l + 1);
    if (!r) r = consumeToken(b, SYMBOL);
    return r;
  }

  /* ********************************************************** */
  // "null"
  public static boolean null_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "null_$")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NULL, "<null $>");
    r = consumeToken(b, "null");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [SIGN] (INTEGER | DECIMAL) | [SIGN] BYTE
  static boolean num(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "num")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = num_0(b, l + 1);
    if (!r) r = num_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [SIGN] (INTEGER | DECIMAL)
  private static boolean num_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "num_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = num_0_0(b, l + 1);
    r = r && num_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [SIGN]
  private static boolean num_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "num_0_0")) return false;
    consumeToken(b, SIGN);
    return true;
  }

  // INTEGER | DECIMAL
  private static boolean num_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "num_0_1")) return false;
    boolean r;
    r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, DECIMAL);
    return r;
  }

  // [SIGN] BYTE
  private static boolean num_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "num_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = num_1_0(b, l + 1);
    r = r && consumeToken(b, BYTE);
    exit_section_(b, m, null, r);
    return r;
  }

  // [SIGN]
  private static boolean num_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "num_1_0")) return false;
    consumeToken(b, SIGN);
    return true;
  }

  /* ********************************************************** */
  // <<brace_block kv_pair ignore>>
  public static boolean object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object")) return false;
    if (!nextTokenIs(b, BRACE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = brace_block(b, l + 1, JssParser::kv_pair, JssParser::ignore);
    exit_section_(b, m, OBJECT, r);
    return r;
  }

  /* ********************************************************** */
  // PARENTHESIS_L <<param>> PARENTHESIS_R
  static boolean parenthesis(PsiBuilder b, int l, Parser _param) {
    if (!recursion_guard_(b, l, "parenthesis")) return false;
    if (!nextTokenIs(b, PARENTHESIS_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARENTHESIS_L);
    r = r && _param.parse(b, l);
    r = r && consumeToken(b, PARENTHESIS_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<brace_block properties_inner ignore>>
  public static boolean properties_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_block")) return false;
    if (!nextTokenIs(b, BRACE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = brace_block(b, l + 1, JssParser::properties_inner, JssParser::ignore);
    exit_section_(b, m, PROPERTIES_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // idiom_statement
  //   | properties_statement
  //   | anno_statement
  //   | COMMENT_DOCUMENT
  //   | kv_pair
  static boolean properties_inner(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_inner")) return false;
    boolean r;
    r = idiom_statement(b, l + 1);
    if (!r) r = properties_statement(b, l + 1);
    if (!r) r = anno_statement(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT_DOCUMENT);
    if (!r) r = kv_pair(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // STRING | SYMBOL
  public static boolean properties_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_key")) return false;
    if (!nextTokenIs(b, "<properties key>", STRING, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTIES_KEY, "<properties key>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, SYMBOL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOT
  public static boolean properties_mark(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_mark")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    exit_section_(b, m, PROPERTIES_MARK, r);
    return r;
  }

  /* ********************************************************** */
  // ("properties" | "property" | "prop"| "field" | DOT) properties_key [type_hint] [properties_block]
  public static boolean properties_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTIES_STATEMENT, "<properties statement>");
    r = properties_statement_0(b, l + 1);
    r = r && properties_key(b, l + 1);
    r = r && properties_statement_2(b, l + 1);
    r = r && properties_statement_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "properties" | "property" | "prop"| "field" | DOT
  private static boolean properties_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_statement_0")) return false;
    boolean r;
    r = consumeToken(b, "properties");
    if (!r) r = consumeToken(b, "property");
    if (!r) r = consumeToken(b, "prop");
    if (!r) r = consumeToken(b, "field");
    if (!r) r = consumeToken(b, DOT);
    return r;
  }

  // [type_hint]
  private static boolean properties_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_statement_2")) return false;
    type_hint(b, l + 1);
    return true;
  }

  // [properties_block]
  private static boolean properties_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "properties_statement_3")) return false;
    properties_block(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "schema" SYMBOL [type_hint] <<brace_block properties_inner ignore>>
  public static boolean schema_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "schema_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SCHEMA_STATEMENT, "<schema statement>");
    r = consumeToken(b, "schema");
    r = r && consumeToken(b, SYMBOL);
    r = r && schema_statement_2(b, l + 1);
    r = r && brace_block(b, l + 1, JssParser::properties_inner, JssParser::ignore);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [type_hint]
  private static boolean schema_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "schema_statement_2")) return false;
    type_hint(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COLON
  static boolean set(PsiBuilder b, int l) {
    return consumeToken(b, COLON);
  }

  /* ********************************************************** */
  // schema_statement
  //   | properties_statement
  //   | def_statement
  //   | COMMENT_DOCUMENT
  //   | object
  //   | ignore
  static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    r = schema_statement(b, l + 1);
    if (!r) r = properties_statement(b, l + 1);
    if (!r) r = def_statement(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT_DOCUMENT);
    if (!r) r = object(b, l + 1);
    if (!r) r = ignore(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // string_inline|string_multi
  static boolean str(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "str")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    r = string_inline(b, l + 1);
    if (!r) r = string_multi(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean string_inline(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_inline")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, STRING_INLINE, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean string_multi(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_multi")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, STRING_MULTI, r);
    return r;
  }

  /* ********************************************************** */
  // COLON type_symbol
  public static boolean type_hint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_hint")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && type_symbol(b, l + 1);
    exit_section_(b, m, TYPE_HINT, r);
    return r;
  }

  /* ********************************************************** */
  // SYMBOL | STRING
  static boolean type_symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_symbol")) return false;
    if (!nextTokenIs(b, "", STRING, SYMBOL)) return false;
    boolean r;
    r = consumeToken(b, SYMBOL);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  /* ********************************************************** */
  // URL
  public static boolean url_maybe_valid(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "url_maybe_valid")) return false;
    if (!nextTokenIs(b, URL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, URL);
    exit_section_(b, m, URL_MAYBE_VALID, r);
    return r;
  }

  /* ********************************************************** */
  // null | boolean | num | str | array | object | url_maybe_valid
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = null_$(b, l + 1);
    if (!r) r = boolean_$(b, l + 1);
    if (!r) r = num(b, l + 1);
    if (!r) r = str(b, l + 1);
    if (!r) r = array(b, l + 1);
    if (!r) r = object(b, l + 1);
    if (!r) r = url_maybe_valid(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
