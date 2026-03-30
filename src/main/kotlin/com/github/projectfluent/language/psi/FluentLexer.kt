package com.github.projectfluent.language.psi

import com.intellij.lexer.Lexer
import com.intellij.lexer.LexerPosition
import com.intellij.psi.tree.IElementType
import com.intellij.psi.TokenType

class FluentLexer : Lexer() {
    private var buffer: CharSequence = ""
    private var startOffset: Int = 0
    private var endOffset: Int = 0
    private var currentOffset: Int = 0
    private var tokenStart: Int = 0
    private var tokenEnd: Int = 0
    private var currentToken: IElementType? = null
    private var insideExpression: Boolean = false
    private var insideString: Boolean = false

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        this.currentOffset = startOffset
        this.tokenStart = startOffset
        this.tokenEnd = startOffset
        this.currentToken = null
        this.insideExpression = false
        this.insideString = false
        advance()
    }

    override fun getState(): Int {
        return if (insideString) 1 else 0
    }

    override fun getTokenType(): IElementType? {
        return currentToken
    }

    override fun getTokenStart(): Int {
        return tokenStart
    }

    override fun getTokenEnd(): Int {
        return tokenEnd
    }

    override fun advance() {
        do {
            tokenStart = currentOffset
            currentToken = null

            if (currentOffset >= endOffset) {
                tokenEnd = currentOffset
                return
            }

            val c = buffer[currentOffset]

            if (insideString) {
                consumeStringContent()
            } else {
                when {
                    c.isWhitespace() -> {
                        consumeWhitespace()
                    }
                    c == '#' -> {
                        consumeComment()
                    }
                    c == '"' -> {
                        currentToken = FluentTypes.STRING_QUOTE
                        currentOffset++
                        insideString = true
                    }
                    c.isLetter() || c == '_' -> {
                        consumeSymbol()
                    }
                    c.isDigit() -> {
                        if (insideExpression) {
                            consumeNumberInExpression()
                        } else {
                            consumeTextLine()
                        }
                    }
                    c == '{' -> {
                        currentToken = FluentTypes.BRACE_L
                        currentOffset++
                        insideExpression = true
                    }
                    c == '}' -> {
                        currentToken = FluentTypes.BRACE_R
                        currentOffset++
                        insideExpression = false
                    }
                    c == '[' -> {
                        currentToken = FluentTypes.BRACKET_L
                        currentOffset++
                    }
                    c == ']' -> {
                        currentToken = FluentTypes.BRACKET_R
                        currentOffset++
                    }
                    c == '(' -> {
                        currentToken = FluentTypes.PARENTHESIS_L
                        currentOffset++
                    }
                    c == ')' -> {
                        currentToken = FluentTypes.PARENTHESIS_R
                        currentOffset++
                    }
                    c == '<' -> {
                        currentToken = FluentTypes.ANGLE_L
                        currentOffset++
                    }
                    c == '>' -> {
                        currentToken = FluentTypes.ANGLE_R
                        currentOffset++
                    }
                    c == '=' -> {
                        currentToken = FluentTypes.EQ
                        currentOffset++
                    }
                    c == ':' -> {
                        currentToken = FluentTypes.COLON
                        currentOffset++
                    }
                    c == ';' -> {
                        currentToken = FluentTypes.SEMICOLON
                        currentOffset++
                    }
                    c == ',' -> {
                        currentToken = FluentTypes.COMMA
                        currentOffset++
                    }
                    c == '$' -> {
                        currentToken = FluentTypes.DOLLAR
                        currentOffset++
                    }
                    c == '.' -> {
                        currentToken = FluentTypes.DOT
                        currentOffset++
                    }
                    c == '*' -> {
                        currentToken = FluentTypes.STAR
                        currentOffset++
                    }
                    c == '-' -> {
                        currentToken = FluentTypes.HYPHEN
                        currentOffset++
                    }
                    c == '`' -> {
                        currentToken = FluentTypes.ACCENT
                        currentOffset++
                    }
                    c == '\\' -> {
                        currentToken = FluentTypes.STRING_ESCAPE
                        currentOffset++
                        if (currentOffset < endOffset) {
                            currentOffset++
                        }
                    }
                    else -> {
                        consumeTextLine()
                    }
                }
            }

            tokenEnd = currentOffset
        } while (currentToken == null && currentOffset < endOffset)
    }

    private fun consumeStringContent() {
        if (currentOffset >= endOffset) {
            insideString = false
            return
        }

        val c = buffer[currentOffset]

        when {
            c == '"' -> {
                currentToken = FluentTypes.STRING_QUOTE
                currentOffset++
                insideString = false
            }
            c == '\\' -> {
                currentToken = FluentTypes.STRING_ESCAPE
                currentOffset++
                if (currentOffset < endOffset) {
                    currentOffset++
                }
            }
            c == '{' -> {
                insideString = false
                currentToken = FluentTypes.BRACE_L
                currentOffset++
                insideExpression = true
            }
            else -> {
                val start = currentOffset
                while (currentOffset < endOffset) {
                    val nextC = buffer[currentOffset]
                    if (nextC == '"' || nextC == '\\' || nextC == '{') {
                        break
                    }
                    currentOffset++
                }
                if (currentOffset > start) {
                    currentToken = FluentTypes.STRING_CHAR
                }
            }
        }
    }

    private fun consumeWhitespace() {
        while (currentOffset < endOffset && buffer[currentOffset].isWhitespace()) {
            currentOffset++
        }
        currentToken = TokenType.WHITE_SPACE
    }

    private fun consumeComment() {
        currentOffset++
        while (currentOffset < endOffset && buffer[currentOffset] != '\n') {
            currentOffset++
        }
        currentToken = FluentTypes.COMMENT_LINE
    }

    private fun consumeSymbol() {
        while (currentOffset < endOffset) {
            val c = buffer[currentOffset]
            if (!c.isLetterOrDigit() && c != '_' && c != '-') {
                break
            }
            currentOffset++
        }
        currentToken = FluentTypes.SYMBOL
    }

    private fun consumeNumberInExpression() {
        while (currentOffset < endOffset && buffer[currentOffset].isDigit()) {
            currentOffset++
        }
        if (currentOffset < endOffset && buffer[currentOffset] == '.') {
            currentOffset++
            while (currentOffset < endOffset && buffer[currentOffset].isDigit()) {
                currentOffset++
            }
            currentToken = FluentTypes.DECIMAL
        } else {
            currentToken = FluentTypes.INTEGER
        }
    }

    private fun consumeTextLine() {
        currentToken = FluentTypes.TEXT_LINE
        while (currentOffset < endOffset) {
            val nextC = buffer[currentOffset]
            when {
                nextC.isWhitespace() ||
                nextC == '#' ||
                nextC == '"' ||
                nextC.isLetter() ||
                nextC.isDigit() ||
                nextC == '{' ||
                nextC == '}' ||
                nextC == '[' ||
                nextC == ']' ||
                nextC == '(' ||
                nextC == ')' ||
                nextC == '<' ||
                nextC == '>' ||
                nextC == '=' ||
                nextC == ':' ||
                nextC == ';' ||
                nextC == ',' ||
                nextC == '$' ||
                nextC == '.' ||
                nextC == '*' ||
                nextC == '-' ||
                nextC == '`' -> {
                    break
                }
                else -> {
                    currentOffset++
                }
            }
        }
    }

    override fun getCurrentPosition(): LexerPosition {
        return object : LexerPosition {
            override fun getOffset(): Int = currentOffset
            override fun getState(): Int = getState()
        }
    }

    override fun restore(position: LexerPosition) {
        currentOffset = position.offset
        tokenStart = currentOffset
        tokenEnd = currentOffset
        currentToken = null
        insideString = position.state == 1
        advance()
    }

    override fun getBufferSequence(): CharSequence {
        return buffer
    }

    override fun getBufferEnd(): Int {
        return endOffset
    }
}
