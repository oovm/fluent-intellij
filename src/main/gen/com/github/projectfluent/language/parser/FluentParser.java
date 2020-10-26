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
  // "." MessageID
  public static boolean AttributeAccessor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeAccessor")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && MessageID(b, l + 1);
    exit_section_(b, m, ATTRIBUTE_ACCESSOR, r);
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
  // (BLANK_INLINE | CRLF)+
  public static boolean BLANK(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BLANK")) return false;
    if (!nextTokenIs(b, "<blank>", BLANK_INLINE, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLANK, "<blank>");
    r = BLANK_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!BLANK_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BLANK", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BLANK_INLINE | CRLF
  private static boolean BLANK_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BLANK_0")) return false;
    boolean r;
    r = consumeToken(b, BLANK_INLINE);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // ([BLANK_INLINE] CRLF)+
  public static boolean BLANK_BLOCK(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BLANK_BLOCK")) return false;
    if (!nextTokenIs(b, "<blank block>", BLANK_INLINE, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLANK_BLOCK, "<blank block>");
    r = BLANK_BLOCK_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!BLANK_BLOCK_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BLANK_BLOCK", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [BLANK_INLINE] CRLF
  private static boolean BLANK_BLOCK_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BLANK_BLOCK_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BLANK_BLOCK_0_0(b, l + 1);
    r = r && consumeToken(b, CRLF);
    exit_section_(b, m, null, r);
    return r;
  }

  // [BLANK_INLINE]
  private static boolean BLANK_BLOCK_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BLANK_BLOCK_0_0")) return false;
    consumeToken(b, BLANK_INLINE);
    return true;
  }

  /* ********************************************************** */
  // [BLANK] "(" [BLANK] argument_list [BLANK] ")"
  public static boolean CallArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallArguments")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CALL_ARGUMENTS, "<call arguments>");
    r = CallArguments_0(b, l + 1);
    r = r && consumeToken(b, PARENTHESIS_L);
    r = r && CallArguments_2(b, l + 1);
    r = r && argument_list(b, l + 1);
    r = r && CallArguments_4(b, l + 1);
    r = r && consumeToken(b, PARENTHESIS_R);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [BLANK]
  private static boolean CallArguments_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallArguments_0")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK]
  private static boolean CallArguments_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallArguments_2")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK]
  private static boolean CallArguments_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallArguments_4")) return false;
    BLANK(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // CRLF [BLANK] "*" VariantKey [BLANK_INLINE] pattern
  public static boolean DefaultVariant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultVariant")) return false;
    if (!nextTokenIs(b, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CRLF);
    r = r && DefaultVariant_1(b, l + 1);
    r = r && consumeToken(b, STAR);
    r = r && VariantKey(b, l + 1);
    r = r && DefaultVariant_4(b, l + 1);
    r = r && pattern(b, l + 1);
    exit_section_(b, m, DEFAULT_VARIANT, r);
    return r;
  }

  // [BLANK]
  private static boolean DefaultVariant_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultVariant_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK_INLINE]
  private static boolean DefaultVariant_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultVariant_4")) return false;
    consumeToken(b, BLANK_INLINE);
    return true;
  }

  /* ********************************************************** */
  // Message
  //     | term
  //     | COMMENT_LINE
  static boolean Entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Entry")) return false;
    boolean r;
    r = Message(b, l + 1);
    if (!r) r = term(b, l + 1);
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
  // MessageID CallArguments
  public static boolean FunctionReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionReference")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MessageID(b, l + 1);
    r = r && CallArguments(b, l + 1);
    exit_section_(b, m, FUNCTION_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // StringLiteral
  //                       | NumberLiteral
  //                       | FunctionReference
  //                       | MessageReference
  //                       | TermReference
  //                       | VariableReference
  //                       | inline_placeable
  public static boolean InlineExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE_EXPRESSION, "<inline expression>");
    r = StringLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
    if (!r) r = FunctionReference(b, l + 1);
    if (!r) r = MessageReference(b, l + 1);
    if (!r) r = TermReference(b, l + 1);
    if (!r) r = VariableReference(b, l + 1);
    if (!r) r = inline_placeable(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MessageID EQ ((pattern attribute*) | (attribute+))
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

  // (pattern attribute*) | (attribute+)
  private static boolean Message_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Message_2_0(b, l + 1);
    if (!r) r = Message_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // pattern attribute*
  private static boolean Message_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pattern(b, l + 1);
    r = r && Message_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // attribute*
  private static boolean Message_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Message_2_0_1", c)) break;
    }
    return true;
  }

  // attribute+
  private static boolean Message_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Message_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
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
  // MessageID [AttributeAccessor]
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

  // [AttributeAccessor]
  private static boolean MessageReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MessageReference_1")) return false;
    AttributeAccessor(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MessageID [BLANK] ":" [BLANK] (StringLiteral | NumberLiteral)
  public static boolean NamedArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedArgument")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MessageID(b, l + 1);
    r = r && NamedArgument_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && NamedArgument_3(b, l + 1);
    r = r && NamedArgument_4(b, l + 1);
    exit_section_(b, m, NAMED_ARGUMENT, r);
    return r;
  }

  // [BLANK]
  private static boolean NamedArgument_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedArgument_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK]
  private static boolean NamedArgument_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedArgument_3")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // StringLiteral | NumberLiteral
  private static boolean NamedArgument_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedArgument_4")) return false;
    boolean r;
    r = StringLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ["-"] DIGITS ["." DIGITS]
  public static boolean NumberLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral")) return false;
    if (!nextTokenIs(b, "<number literal>", DIGITS, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMBER_LITERAL, "<number literal>");
    r = NumberLiteral_0(b, l + 1);
    r = r && consumeToken(b, DIGITS);
    r = r && NumberLiteral_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ["-"]
  private static boolean NumberLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral_0")) return false;
    consumeToken(b, HYPHEN);
    return true;
  }

  // ["." DIGITS]
  private static boolean NumberLiteral_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral_2")) return false;
    parseTokens(b, 0, DOT, DIGITS);
    return true;
  }

  /* ********************************************************** */
  // InlineExpression [BLANK] "->" [BLANK_INLINE] variant_list
  public static boolean SelectExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SelectExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELECT_EXPRESSION, "<select expression>");
    r = InlineExpression(b, l + 1);
    r = r && SelectExpression_1(b, l + 1);
    r = r && consumeToken(b, "->");
    r = r && SelectExpression_3(b, l + 1);
    r = r && variant_list(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [BLANK]
  private static boolean SelectExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SelectExpression_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK_INLINE]
  private static boolean SelectExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SelectExpression_3")) return false;
    consumeToken(b, BLANK_INLINE);
    return true;
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
  // "-" MessageID [AttributeAccessor] [CallArguments]
  public static boolean TermReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TermReference")) return false;
    if (!nextTokenIs(b, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HYPHEN);
    r = r && MessageID(b, l + 1);
    r = r && TermReference_2(b, l + 1);
    r = r && TermReference_3(b, l + 1);
    exit_section_(b, m, TERM_REFERENCE, r);
    return r;
  }

  // [AttributeAccessor]
  private static boolean TermReference_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TermReference_2")) return false;
    AttributeAccessor(b, l + 1);
    return true;
  }

  // [CallArguments]
  private static boolean TermReference_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TermReference_3")) return false;
    CallArguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "$" MessageID
  public static boolean VariableReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableReference")) return false;
    if (!nextTokenIs(b, DOLLAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR);
    r = r && MessageID(b, l + 1);
    exit_section_(b, m, VARIABLE_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // CRLF [BLANK] VariantKey [BLANK_INLINE] pattern
  public static boolean Variant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Variant")) return false;
    if (!nextTokenIs(b, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CRLF);
    r = r && Variant_1(b, l + 1);
    r = r && VariantKey(b, l + 1);
    r = r && Variant_3(b, l + 1);
    r = r && pattern(b, l + 1);
    exit_section_(b, m, VARIANT, r);
    return r;
  }

  // [BLANK]
  private static boolean Variant_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Variant_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK_INLINE]
  private static boolean Variant_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Variant_3")) return false;
    consumeToken(b, BLANK_INLINE);
    return true;
  }

  /* ********************************************************** */
  // "[" [BLANK] (NumberLiteral | MessageID) [BLANK] "]"
  public static boolean VariantKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariantKey")) return false;
    if (!nextTokenIs(b, BRACKET_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_L);
    r = r && VariantKey_1(b, l + 1);
    r = r && VariantKey_2(b, l + 1);
    r = r && VariantKey_3(b, l + 1);
    r = r && consumeToken(b, BRACKET_R);
    exit_section_(b, m, VARIANT_KEY, r);
    return r;
  }

  // [BLANK]
  private static boolean VariantKey_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariantKey_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // NumberLiteral | MessageID
  private static boolean VariantKey_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariantKey_2")) return false;
    boolean r;
    r = NumberLiteral(b, l + 1);
    if (!r) r = MessageID(b, l + 1);
    return r;
  }

  // [BLANK]
  private static boolean VariantKey_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariantKey_3")) return false;
    BLANK(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (Argument [BLANK] "," [BLANK])* [Argument]
  public static boolean argument_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT_LIST, "<argument list>");
    r = argument_list_0(b, l + 1);
    r = r && argument_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (Argument [BLANK] "," [BLANK])*
  private static boolean argument_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!argument_list_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_0", c)) break;
    }
    return true;
  }

  // Argument [BLANK] "," [BLANK]
  private static boolean argument_list_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Argument(b, l + 1);
    r = r && argument_list_0_0_1(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && argument_list_0_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [BLANK]
  private static boolean argument_list_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_0_0_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [BLANK]
  private static boolean argument_list_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_0_0_3")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // [Argument]
  private static boolean argument_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1")) return false;
    Argument(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // AttributeID MessageID EQ pattern
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AttributeID(b, l + 1);
    r = r && MessageID(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && pattern(b, l + 1);
    exit_section_(b, m, ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // BLANK_BLOCK [BLANK_INLINE] inline_placeable
  public static boolean block_placeable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_placeable")) return false;
    if (!nextTokenIs(b, "<block placeable>", BLANK_INLINE, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_PLACEABLE, "<block placeable>");
    r = BLANK_BLOCK(b, l + 1);
    r = r && block_placeable_1(b, l + 1);
    r = r && inline_placeable(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [BLANK_INLINE]
  private static boolean block_placeable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_placeable_1")) return false;
    consumeToken(b, BLANK_INLINE);
    return true;
  }

  /* ********************************************************** */
  // BLANK_BLOCK BLANK_INLINE INDENT [inline_text]
  public static boolean block_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_text")) return false;
    if (!nextTokenIs(b, "<block text>", BLANK_INLINE, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_TEXT, "<block text>");
    r = BLANK_BLOCK(b, l + 1);
    r = r && consumeTokens(b, 0, BLANK_INLINE, INDENT);
    r = r && block_text_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [inline_text]
  private static boolean block_text_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_text_3")) return false;
    inline_text(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "{" [BLANK] (SelectExpression | InlineExpression) [BLANK] "}"
  public static boolean inline_placeable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline_placeable")) return false;
    if (!nextTokenIs(b, BRACE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACE_L);
    r = r && inline_placeable_1(b, l + 1);
    r = r && inline_placeable_2(b, l + 1);
    r = r && inline_placeable_3(b, l + 1);
    r = r && consumeToken(b, BRACE_R);
    exit_section_(b, m, INLINE_PLACEABLE, r);
    return r;
  }

  // [BLANK]
  private static boolean inline_placeable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline_placeable_1")) return false;
    BLANK(b, l + 1);
    return true;
  }

  // SelectExpression | InlineExpression
  private static boolean inline_placeable_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline_placeable_2")) return false;
    boolean r;
    r = SelectExpression(b, l + 1);
    if (!r) r = InlineExpression(b, l + 1);
    return r;
  }

  // [BLANK]
  private static boolean inline_placeable_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline_placeable_3")) return false;
    BLANK(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // TEXT_LINE
  public static boolean inline_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline_text")) return false;
    if (!nextTokenIs(b, TEXT_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TEXT_LINE);
    exit_section_(b, m, INLINE_TEXT, r);
    return r;
  }

  /* ********************************************************** */
  // pattern_element+
  public static boolean pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pattern")) return false;
    if (!nextTokenIs(b, TEXT_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pattern_element(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!pattern_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "pattern", c)) break;
    }
    exit_section_(b, m, PATTERN, r);
    return r;
  }

  /* ********************************************************** */
  // inline_text
  static boolean pattern_element(PsiBuilder b, int l) {
    return inline_text(b, l + 1);
  }

  /* ********************************************************** */
  // TermID MessageID EQ pattern attribute*
  public static boolean term(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term")) return false;
    if (!nextTokenIs(b, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TermID(b, l + 1);
    r = r && MessageID(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && pattern(b, l + 1);
    r = r && term_4(b, l + 1);
    exit_section_(b, m, TERM, r);
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

  /* ********************************************************** */
  // Variant* DefaultVariant Variant* CRLF
  public static boolean variant_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variant_list")) return false;
    if (!nextTokenIs(b, CRLF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variant_list_0(b, l + 1);
    r = r && DefaultVariant(b, l + 1);
    r = r && variant_list_2(b, l + 1);
    r = r && consumeToken(b, CRLF);
    exit_section_(b, m, VARIANT_LIST, r);
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
