package com.github.projectfluent.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.projectfluent.language.psi.FluentTypes.*;

%%

%{
private static int indent_balance = 0;

public _FluentLexer() {
	this((java.io.Reader)null);
}
%}

%public
%class _FluentLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state StringQuote
%state TextContext

EOL=\R
WHITE_SPACE=\s+
HYPHEN="-"
COMMENT_DOCUMENT=("///")[^\r\n]*
COMMENT_LINE = #{1,3}[^\r\n]*
COMMENT_BLOCK=[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]
//SYMBOL=[\p{XID_Start}_][\p{XID_Continue}_]*
SYMBOL = [a-zA-Z][a-zA-Z0-9_-]*
//STRING=\"([^\"\\]|\\.)*\"
BYTE=(0[bBoOxXfF][0-9A-Fa-f][0-9A-Fa-f_]*)
INTEGER=(0|[1-9][0-9_]*)
DECIMAL=([0-9]+\.[0-9]*([*][*][0-9]+)?)|(\.[0-9]+([Ee][0-9]+)?)
SIGN=[+-]
DIGITS = [0-9]+

TEXT_FIRST_LINE  = {TEXT_LINE}+
TEXT_INDENT_LINE = [\t ]+{TEXT_FIRST_LINE}
TEXT_LINE        = [^\\\"\r\n]+
INDENT           = [^\\\"\r\n\[*.]
TEXT_INLINE      = {TEXT_LINE}+

CRLF         = \r\n | \n | \r | \R
BLANK_INLINE = [\t\s]*
BLANK_BLOCK  = ({BLANK_INLINE}? {CRLF})+
BLANK        = ({BLANK_INLINE} | {CRLF})+



ESCAPE_SPECIAL= \\[^\"\\uU]
ESCAPE_UNICODE= \\(u{HEX}{4}|U{HEX}{6})
HEX = [0-9a-fA-F]

%%

<YYINITIAL> {
    {WHITE_SPACE}      { return WHITE_SPACE; }
	{COMMENT_DOCUMENT} { return COMMENT_DOCUMENT; }
	{COMMENT_LINE}     { return COMMENT_LINE; }
	{COMMENT_BLOCK}    { return COMMENT_BLOCK; }
}

//<YYINITIAL> {
////	{TEXT_FIRST_LINE}      { return TEXT_FIRST_LINE; }
////	{TEXT_INDENT_LINE} { return TEXT_INDENT_LINE; }
//
//}

<YYINITIAL> {
	"(" { return PARENTHESIS_L; }
	")" { return PARENTHESIS_R; }
	"[" { return BRACKET_L; }
	"]" { return BRACKET_R; }
	"{" { return BRACE_L; }
	"}" { return BRACE_R; }
	"<" { return ANGLE_L; }
	">" { return ANGLE_R; }
	"^" { return ACCENT; }
	":" { return COLON; }
	";" { return SEMICOLON; }
	"," { return COMMA; }
	"$" { return DOLLAR; }
	"." { return DOT; }
	"*" { return STAR; }
	"@" { return AT; }

	{SYMBOL}                { return SYMBOL; }
	{BYTE}                  { return BYTE; }
	{INTEGER}               { return INTEGER; }
	{DECIMAL}               { return DECIMAL; }
}
// =====================================================================================================================
<YYINITIAL> = {
	yybegin(TextContext);
	return EQ;
}
// 如果首行无缩进, 直接结束
<TextContext> {CRLF}[-a-zA-Z] {
	yypushback(1);
    yybegin(YYINITIAL);
    return WHITE_SPACE;
}
<TextContext> {
	{TEXT_LINE} { return TEXT_LINE; }
	{CRLF}      { return WHITE_SPACE; }
}
// =====================================================================================================================
<YYINITIAL> \" {
	yybegin(StringQuote);
    return STRING_QUOTE;
}
// String escaped highlight
<StringQuote> {
	{ESCAPE_UNICODE} {return STRING_ESCAPE;}
	{ESCAPE_SPECIAL} {return STRING_ESCAPE;}
	[^\"] {return STRING_CHAR;}
}

<StringQuote> \" {
	yybegin(YYINITIAL);
	return STRING_QUOTE;
}
// =====================================================================================================================
[^] { return BAD_CHARACTER; }
