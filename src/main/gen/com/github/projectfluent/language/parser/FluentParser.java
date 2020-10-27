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
  // NamedArgument
  //                       | InlineExpression
  public static boolean Argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT, "<argument>");
    r = NamedArgument(b, l + 1);
    if (!r) r = InlineExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (Argument COMMA)* [Argument]
  public static boolean ArgumentList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT_LIST, "<argument list>");
    r = ArgumentList_0(b, l + 1);
    r = r && ArgumentList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (Argument COMMA)*
  private static boolean ArgumentList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ArgumentList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArgumentList_0", c)) break;
    }
    return true;
  }

  // Argument COMMA
  private static boolean ArgumentList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Argument(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // [Argument]
  private static boolean ArgumentList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList_1")) return false;
    Argument(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // AttributeID MessageID EQ Pattern
  public static boolean Attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attribute")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AttributeID(b, l + 1);
    r = r && MessageID(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && Pattern(b, l + 1);
    exit_section_(b, m, ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // DOT SYMBOL
  public static boolean AttributeID(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeID")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, SYMBOL);
    exit_section_(b, m, ATTRIBUTE_ID, r);
    return r;
  }

  /* ********************************************************** */
  // PARENTHESIS_L ArgumentList  PARENTHESIS_R
  public static boolean CallArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallArguments")) return false;
    if (!nextTokenIs(b, PARENTHESIS_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARENTHESIS_L);
    r = r && ArgumentList(b, l + 1);
    r = r && consumeToken(b, PARENTHESIS_R);
    exit_section_(b, m, CALL_ARGUMENTS, r);
    return r;
  }

  /* ********************************************************** */
  // STAR VariantKey  Pattern
  public static boolean DefaultVariant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultVariant")) return false;
    if (!nextTokenIs(b, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STAR);
    r = r && VariantKey(b, l + 1);
    r = r && Pattern(b, l + 1);
    exit_section_(b, m, DEFAULT_VARIANT, r);
    return r;
  }

  /* ********************************************************** */
  // Message
  //     | Term
  //     | COMMENT_LINE
  static boolean Entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Entry")) return false;
    boolean r;
    r = Message(b, l + 1);
    if (!r) r = Term(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT_LINE);
    return r;
  }

  /* ********************************************************** */
  // Entry*
  static boolean Fluent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fluent")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Entry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fluent", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SYMBOL
  public static boolean FunctionID(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionID")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYMBOL);
    exit_section_(b, m, FUNCTION_ID, r);
    return r;
  }

  /* ********************************************************** */
  // FunctionID CallArguments
  public static boolean FunctionReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionReference")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionID(b, l + 1);
    r = r && CallArguments(b, l + 1);
    exit_section_(b, m, FUNCTION_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // StringLiteral
  //     | NumberLiteral
  //     | FunctionReference
  //     | MessageReference
  //     | TermReference
  //     | VariableID
  //     | InlinePlaceable
  public static boolean InlineExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE_EXPRESSION, "<inline expression>");
    r = StringLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
    if (!r) r = FunctionReference(b, l + 1);
    if (!r) r = MessageReference(b, l + 1);
    if (!r) r = TermReference(b, l + 1);
    if (!r) r = VariableID(b, l + 1);
    if (!r) r = InlinePlaceable(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BRACE_L (SelectExpression | InlineExpression) BRACE_R
  public static boolean InlinePlaceable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlinePlaceable")) return false;
    if (!nextTokenIs(b, BRACE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACE_L);
    r = r && InlinePlaceable_1(b, l + 1);
    r = r && consumeToken(b, BRACE_R);
    exit_section_(b, m, INLINE_PLACEABLE, r);
    return r;
  }

  // SelectExpression | InlineExpression
  private static boolean InlinePlaceable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlinePlaceable_1")) return false;
    boolean r;
    r = SelectExpression(b, l + 1);
    if (!r) r = InlineExpression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // MessageID EQ ((Pattern Attribute*) | (Attribute+))
  public static boolean Message(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MessageID(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && Message_2(b, l + 1);
    exit_section_(b, m, MESSAGE, r);
    return r;
  }

  // (Pattern Attribute*) | (Attribute+)
  private static boolean Message_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Message_2_0(b, l + 1);
    if (!r) r = Message_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Pattern Attribute*
  private static boolean Message_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Pattern(b, l + 1);
    r = r && Message_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Attribute*
  private static boolean Message_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Message_2_0_1", c)) break;
    }
    return true;
  }

  // Attribute+
  private static boolean Message_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Attribute(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!Attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Message_2_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SYMBOL
  public static boolean MessageID(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MessageID")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYMBOL);
    exit_section_(b, m, MESSAGE_ID, r);
    return r;
  }

  /* ********************************************************** */
  // MessageID [AttributeID]
  public static boolean MessageReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MessageReference")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MessageID(b, l + 1);
    r = r && MessageReference_1(b, l + 1);
    exit_section_(b, m, MESSAGE_REFERENCE, r);
    return r;
  }

  // [AttributeID]
  private static boolean MessageReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MessageReference_1")) return false;
    AttributeID(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MessageID COLON (StringLiteral | NumberLiteral)
  public static boolean NamedArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedArgument")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MessageID(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && NamedArgument_2(b, l + 1);
    exit_section_(b, m, NAMED_ARGUMENT, r);
    return r;
  }

  // StringLiteral | NumberLiteral
  private static boolean NamedArgument_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedArgument_2")) return false;
    boolean r;
    r = StringLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean NumberLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, NUMBER_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // pattern_element+
  public static boolean Pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PATTERN, "<pattern>");
    r = pattern_element(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!pattern_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Pattern", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // InlineExpression TO variant_list
  public static boolean SelectExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SelectExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELECT_EXPRESSION, "<select expression>");
    r = InlineExpression(b, l + 1);
    r = r && consumeToken(b, TO);
    r = r && variant_list(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING_QUOTE STRING_ESCAPE STRING_CHAR STRING_QUOTE
  public static boolean StringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteral")) return false;
    if (!nextTokenIs(b, STRING_QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, STRING_QUOTE, STRING_ESCAPE, STRING_CHAR, STRING_QUOTE);
    exit_section_(b, m, STRING_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // TermID EQ Pattern Attribute*
  public static boolean Term(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Term")) return false;
    if (!nextTokenIs(b, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TermID(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && Pattern(b, l + 1);
    r = r && Term_3(b, l + 1);
    exit_section_(b, m, TERM, r);
    return r;
  }

  // Attribute*
  private static boolean Term_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Term_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Term_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // HYPHEN SYMBOL
  public static boolean TermID(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TermID")) return false;
    if (!nextTokenIs(b, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HYPHEN, SYMBOL);
    exit_section_(b, m, TERM_ID, r);
    return r;
  }

  /* ********************************************************** */
  // TermID [CallArguments]
  public static boolean TermReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TermReference")) return false;
    if (!nextTokenIs(b, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TermID(b, l + 1);
    r = r && TermReference_1(b, l + 1);
    exit_section_(b, m, TERM_REFERENCE, r);
    return r;
  }

  // [CallArguments]
  private static boolean TermReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TermReference_1")) return false;
    CallArguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DOLLAR SYMBOL
  public static boolean VariableID(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableID")) return false;
    if (!nextTokenIs(b, DOLLAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOLLAR, SYMBOL);
    exit_section_(b, m, VARIABLE_ID, r);
    return r;
  }

  /* ********************************************************** */
  // VariantKey  Pattern
  public static boolean Variant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Variant")) return false;
    if (!nextTokenIs(b, BRACKET_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VariantKey(b, l + 1);
    r = r && Pattern(b, l + 1);
    exit_section_(b, m, VARIANT, r);
    return r;
  }

  /* ********************************************************** */
  // BRACKET_L (NumberLiteral | MessageID) BRACKET_R
  public static boolean VariantKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariantKey")) return false;
    if (!nextTokenIs(b, BRACKET_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_L);
    r = r && VariantKey_1(b, l + 1);
    r = r && consumeToken(b, BRACKET_R);
    exit_section_(b, m, VARIANT_KEY, r);
    return r;
  }

  // NumberLiteral | MessageID
  private static boolean VariantKey_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariantKey_1")) return false;
    boolean r;
    r = NumberLiteral(b, l + 1);
    if (!r) r = MessageID(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BLANK_BLOCK  InlinePlaceable
  public static boolean block_placeable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_placeable")) return false;
    if (!nextTokenIs(b, BLANK_BLOCK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BLANK_BLOCK);
    r = r && InlinePlaceable(b, l + 1);
    exit_section_(b, m, BLOCK_PLACEABLE, r);
    return r;
  }

  /* ********************************************************** */
  // BLANK_BLOCK BLANK_INLINE INDENT [TEXT_LINE]
  public static boolean block_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_text")) return false;
    if (!nextTokenIs(b, BLANK_BLOCK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BLANK_BLOCK, BLANK_INLINE, INDENT);
    r = r && block_text_3(b, l + 1);
    exit_section_(b, m, BLOCK_TEXT, r);
    return r;
  }

  // [TEXT_LINE]
  private static boolean block_text_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_text_3")) return false;
    consumeToken(b, TEXT_LINE);
    return true;
  }

  /* ********************************************************** */
  // TEXT_LINE
  //     | block_text
  //     | InlinePlaceable
  //     | block_placeable
  static boolean pattern_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pattern_element")) return false;
    boolean r;
    r = consumeToken(b, TEXT_LINE);
    if (!r) r = block_text(b, l + 1);
    if (!r) r = InlinePlaceable(b, l + 1);
    if (!r) r = block_placeable(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Variant* DefaultVariant Variant*
  public static boolean variant_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variant_list")) return false;
    if (!nextTokenIs(b, "<variant list>", BRACKET_L, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIANT_LIST, "<variant list>");
    r = variant_list_0(b, l + 1);
    r = r && DefaultVariant(b, l + 1);
    r = r && variant_list_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Variant*
  private static boolean variant_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variant_list_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Variant(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variant_list_0", c)) break;
    }
    return true;
  }

  // Variant*
  private static boolean variant_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variant_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Variant(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variant_list_2", c)) break;
    }
    return true;
  }

}
