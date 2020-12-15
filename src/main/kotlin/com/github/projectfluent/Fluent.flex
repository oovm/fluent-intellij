package com.github.projectfluent.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;import com.intellij.util.containers.IntStack;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.projectfluent.language.psi.FluentTypes.*;

%%

%{
private static int indent_balance = 0;
private static IntStack brace_stack = new IntStack(9);

public _FluentLexer() {
	this((java.io.Reader)null);
}

public void brace_block(int state) {
    brace_stack.push(state);
    yybegin(CodeContext);
}

public void brace_recover() {
    if (brace_stack.empty()) {
        yybegin(YYINITIAL);
    }
    else {
        yybegin(brace_stack.pop());
    }
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
%state TextContextSpace
%state CodeContext
%state SelectionStart
%state SelectionText

WHITE_SPACE=\s+
COMMENT_DOCUMENT=("///")[^\r\n]*
COMMENT_LINE = #{1,3}[^\r\n]*
COMMENT_BLOCK=[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]
//SYMBOL=[\p{XID_Start}_][\p{XID_Continue}_]*
SYMBOL = [a-zA-Z][a-zA-Z0-9_-]*
//STRING=\"([^\"\\]|\\.)*\"
BYTE=(0[bBoOxXfF][0-9A-Fa-f][0-9A-Fa-f_]*)
INTEGER=(0|[1-9][0-9_]*)
DECIMAL=([0-9]+\.[0-9]*([Ee][0-9]+)?)|(\.[0-9]+([Ee][0-9]+)?)

TEXT_LINE  = [^\\\r\n{}]+

CRLF       = \r\n | \n | \r | \R


ESCAPE_SPECIAL= \\[^]
ESCAPE_UNICODE= \\(u{HEX}{4}|U{HEX}{6})
HEX = [0-9a-fA-F]

%%

<YYINITIAL, CodeContext> {
    {WHITE_SPACE}      { return WHITE_SPACE; }
//	{COMMENT_DOCUMENT} { return COMMENT_DOCUMENT; }
	{COMMENT_LINE}     { return COMMENT_LINE; }
//	{COMMENT_BLOCK}    { return COMMENT_BLOCK; }
}

//<YYINITIAL> {
////	{TEXT_FIRST_LINE}      { return TEXT_FIRST_LINE; }
////	{TEXT_INDENT_LINE} { return TEXT_INDENT_LINE; }
//
//}

<YYINITIAL> {
	"{" { return BRACE_L; }
	"}" { return BRACE_R; }
	"<" { return ANGLE_L; }
	">" { return ANGLE_R; }
	"^" { return ACCENT; }
	";" { return SEMICOLON; }
	"$" { return DOLLAR; }
	"." { return DOT; }
	"-" { return HYPHEN; }
}
<YYINITIAL> {
	{SYMBOL} { return SYMBOL; }
}

// =====================================================================================================================
<YYINITIAL> = {
	yybegin(TextContextSpace);
	return EQ;
}
// 将 = 之后的符号都视为空格而非文本
<TextContextSpace> [\s\t\n\r]+ {
	return WHITE_SPACE;
}
<TextContextSpace> [^\s\t\n\r] {
	yypushback(1);
	yybegin(TextContext);
}
// 如果首行无缩进, 直接结束
<TextContext> {CRLF}[-#a-zA-Z] {
	yypushback(1);
    yybegin(YYINITIAL);
    return WHITE_SPACE;
}
// 如果缩进开头是 .
<TextContext> {CRLF}{WHITE_SPACE}+[.] {
	yypushback(1);
    yybegin(YYINITIAL);
    return WHITE_SPACE;
}
<TextContext> {
	{TEXT_LINE} { return TEXT_LINE; }
	{CRLF}      { return WHITE_SPACE; }
}
// =====================================================================================================================
// 代码域 CodeContext , 从 `{` 开始, 到 `}` 结束
<TextContext> \{ {
	brace_block(TextContext);
	return BRACE_L;
}
<CodeContext> } {
	brace_recover();
	return BRACE_R;
}
<CodeContext> {
	"(" { return PARENTHESIS_L; }
	")" { return PARENTHESIS_R; }
	"[" { return BRACKET_L; }
	"]" { return BRACKET_R; }
	\$  {return DOLLAR;}
	"," { return COMMA; }
	":" { return COLON; }
	"*" { return STAR; }
	"-" { return HYPHEN; }
}
<CodeContext> {
	{SYMBOL}  {return SYMBOL;}
	[-]?{INTEGER} {return INTEGER;}
	[-]?{DECIMAL} {return DECIMAL;}
}
// =====================================================================================================================
// 选择域 SelectionStart
<CodeContext> -> {
	yybegin(SelectionStart);
	return TO;
}
<SelectionStart> {
    {WHITE_SPACE} { return WHITE_SPACE; }
	{SYMBOL}      {return SYMBOL;}
	[-]?{INTEGER} {return INTEGER;}
	[-]?{DECIMAL} {return DECIMAL;}
}
<SelectionStart> \* {
	return STAR;
}
<SelectionStart> \[ {
	return BRACKET_L;
}
<SelectionStart> \] {
	yybegin(SelectionText);
	return BRACKET_R;
}
<SelectionText> {CRLF}{WHITE_SPACE}*[\[*] {
	yypushback(1);
    yybegin(SelectionStart);
    return WHITE_SPACE;
}
<SelectionText> \{ {
	brace_block(SelectionText);
	return BRACE_L;
}
<SelectionText> \} {
	brace_recover();
	return BRACE_R;
}
<SelectionText> {
	{TEXT_LINE} { return SELECTION_LINE; }
	{CRLF}      { return WHITE_SPACE; }
}
// =====================================================================================================================
// 文本域, 文本域只出现在代码中
<CodeContext> \" {
	yybegin(StringQuote);
    return STRING_QUOTE;
}
// String escaped highlight
<StringQuote> {
	{ESCAPE_UNICODE} {return STRING_ESCAPE;}
	{ESCAPE_SPECIAL} {return STRING_ESCAPE;}
	[^\"]+ {return STRING_CHAR;}
}

<StringQuote> \" {
	yybegin(CodeContext);
	return STRING_QUOTE;
}
// =====================================================================================================================
[^] { return BAD_CHARACTER; }
