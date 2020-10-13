package com.github.voml.jss_intellij.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.voml.jss_intellij.language.psi.JssTypes.*;

%%

%{
public _JssLexer() {
	this((java.io.Reader)null);
}
%}

%public
%class _JssLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state StringQuote

EOL=\R
WHITE_SPACE=\s+

COMMENT_DOCUMENT=("///")[^\r\n]*
COMMENT = #{1,3}[^\r\n]*
COMMENT_BLOCK=[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]
URL= [A-Za-z0-9]+:"//"[\-\p{XID_Continue}./?&#]+
//SYMBOL=[\p{XID_Start}_][\p{XID_Continue}_]*
SYMBOL = [a-zA-Z][a-zA-Z0-9_-]*
//STRING=\"([^\"\\]|\\.)*\"
BYTE=(0[bBoOxXfF][0-9A-Fa-f][0-9A-Fa-f_]*)
INTEGER=(0|[1-9][0-9_]*)
DECIMAL=([0-9]+\.[0-9]*([*][*][0-9]+)?)|(\.[0-9]+([Ee][0-9]+)?)
SIGN=[+-]


ESCAPE_SPECIAL= \\(\" | \\)
ESCAPE_UNICODE= \\(u{HEX}{4}|U{HEX}{6})
HEX = [0-9a-fA-F]

%%

<YYINITIAL> {
	{WHITE_SPACE}      { return WHITE_SPACE; }
	{COMMENT_DOCUMENT} { return COMMENT_DOCUMENT; }
	{COMMENT}          { return COMMENT; }
	{COMMENT_BLOCK}    { return COMMENT_BLOCK; }
}

<YYINITIAL> {

  "("                     { return PARENTHESIS_L; }
  ")"                     { return PARENTHESIS_R; }
  "["                     { return BRACKET_L; }
  "]"                     { return BRACKET_R; }
  "{"                     { return BRACE_L; }
  "}"                     { return BRACE_R; }
  "<"                     { return ANGLE_L; }
  ">"                     { return ANGLE_R; }
  "^"                     { return ACCENT; }
  "="                     { return EQ; }
  ":"                     { return COLON; }
  ";"                     { return SEMICOLON; }
  ","                     { return COMMA; }
  "$"                     { return DOLLAR; }
  "."                     { return DOT; }
  "*"                     { return STAR; }
  "@"                     { return AT; }


  {URL}                   { return URL; }
  {SYMBOL}                { return SYMBOL; }
  {BYTE}                  { return BYTE; }
  {INTEGER}               { return INTEGER; }
  {DECIMAL}               { return DECIMAL; }
  {SIGN}                  { return SIGN; }
}

<YYINITIAL> \" {
	yybegin(StringQuote);
    return STRING_QUOTE;
}

<StringQuote> {
	{ESCAPE_UNICODE} {return STRING_ESCAPE;}
	{ESCAPE_SPECIAL}{return STRING_ESCAPE;}
	[^\"] {return STRING_CHAR;}
}

<StringQuote> \" {
	yybegin(YYINITIAL);
	return STRING_QUOTE;
}

[^] { return BAD_CHARACTER; }
