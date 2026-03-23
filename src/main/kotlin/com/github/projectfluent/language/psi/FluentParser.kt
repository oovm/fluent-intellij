package com.github.projectfluent.language.psi

import com.intellij.lang.ASTNode
import com.intellij.lang.LightPsiParser
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.psi.TokenType

class FluentParser : PsiParser, LightPsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        val rootMarker = builder.mark()
        val fluentMarker = builder.mark()
        
        while (!builder.eof()) {
            when (builder.tokenType) {
                TokenType.WHITE_SPACE -> {
                    val whitespaceMarker = builder.mark()
                    builder.advanceLexer()
                    whitespaceMarker.done(TokenType.WHITE_SPACE)
                }
                FluentTypes.COMMENT_LINE -> {
                    val commentMarker = builder.mark()
                    builder.advanceLexer()
                    commentMarker.done(FluentTypes.COMMENT_LINE)
                }
                FluentTypes.SYMBOL -> {
                    // Skip whitespace between symbol and EQ/COLON
                    var lookAheadOffset = 1
                    while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                        lookAheadOffset++
                    }
                    
                    if (builder.lookAhead(lookAheadOffset) == FluentTypes.EQ) {
                        parseMessage(builder)
                    } else if (builder.lookAhead(lookAheadOffset) == FluentTypes.COLON) {
                        parseTerm(builder)
                    } else {
                        val symbolMarker = builder.mark()
                        builder.advanceLexer()
                        symbolMarker.done(FluentTypes.INLINE_TEXT)
                    }
                }
                else -> {
                    builder.advanceLexer()
                }
            }
        }
        
        fluentMarker.done(FluentTypes.FLUENT)
        rootMarker.done(root)
        return builder.treeBuilt
    }

    override fun parseLight(root: IElementType?, builder: PsiBuilder?) {
        if (root != null && builder != null) {
            val rootMarker = builder.mark()
            val fluentMarker = builder.mark()
            
            while (!builder.eof()) {
                when (builder.tokenType) {
                    TokenType.WHITE_SPACE -> {
                        val whitespaceMarker = builder.mark()
                        builder.advanceLexer()
                        whitespaceMarker.done(TokenType.WHITE_SPACE)
                    }
                    FluentTypes.COMMENT_LINE -> {
                        val commentMarker = builder.mark()
                        builder.advanceLexer()
                        commentMarker.done(FluentTypes.COMMENT_LINE)
                    }
                    FluentTypes.SYMBOL -> {
                        // Skip whitespace between symbol and EQ/COLON
                        var lookAheadOffset = 1
                        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                            lookAheadOffset++
                        }
                        
                        if (builder.lookAhead(lookAheadOffset) == FluentTypes.EQ) {
                            parseMessage(builder)
                        } else if (builder.lookAhead(lookAheadOffset) == FluentTypes.COLON) {
                            parseTerm(builder)
                        } else {
                            val symbolMarker = builder.mark()
                            builder.advanceLexer()
                            symbolMarker.done(FluentTypes.INLINE_TEXT)
                        }
                    }
                    else -> {
                        builder.advanceLexer()
                    }
                }
            }
            
            fluentMarker.done(FluentTypes.FLUENT)
            rootMarker.done(root)
        }
    }

    private fun parseFluent(builder: PsiBuilder) {
        // This method is no longer used since we moved the parsing logic to parse() and parseLight()
    }

    private fun parseMessage(builder: PsiBuilder) {
        val messageMarker = builder.mark()
        
        // Parse message ID
        val idMarker = builder.mark()
        builder.advanceLexer() // Consume symbol
        idMarker.done(FluentTypes.MESSAGE_ID)
        
        // Skip whitespace between symbol and EQ
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        // Consume EQ
        if (builder.tokenType == FluentTypes.EQ) {
            builder.advanceLexer()
        }
        
        // Skip whitespace after EQ
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        // Parse pattern
        parsePattern(builder)
        
        // Parse attributes
        while (builder.tokenType == FluentTypes.DOT) {
            parseAttribute(builder)
        }
        
        messageMarker.done(FluentTypes.MESSAGE)
    }

    private fun parseTerm(builder: PsiBuilder) {
        val termMarker = builder.mark()
        
        // Parse term ID
        val idMarker = builder.mark()
        builder.advanceLexer() // Consume symbol
        idMarker.done(FluentTypes.TERM_ID)
        
        // Skip whitespace between symbol and COLON
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        // Consume COLON
        if (builder.tokenType == FluentTypes.COLON) {
            builder.advanceLexer()
        }
        
        // Skip whitespace after COLON
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        // Parse pattern
        parsePattern(builder)
        
        // Parse attributes
        while (builder.tokenType == FluentTypes.DOT) {
            parseAttribute(builder)
        }
        
        termMarker.done(FluentTypes.TERM)
    }

    private fun parseAttribute(builder: PsiBuilder) {
        val attributeMarker = builder.mark()
        
        // Consume DOT
        builder.advanceLexer()
        
        // Parse attribute ID
        val idMarker = builder.mark()
        builder.advanceLexer() // Consume symbol
        idMarker.done(FluentTypes.ATTRIBUTE_ID)
        
        // Consume EQ
        builder.advanceLexer()
        
        // Parse pattern
        parsePattern(builder)
        
        attributeMarker.done(FluentTypes.ATTRIBUTE)
    }

    private fun parsePattern(builder: PsiBuilder) {
        val patternMarker = builder.mark()
        
        while (!builder.eof()) {
            // Skip whitespace and comments first
            while (!builder.eof() && (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE)) {
                val marker = builder.mark()
                builder.advanceLexer()
                marker.done(if (builder.tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
            }
            
            if (builder.eof()) break
            
            val tokenType = builder.tokenType
            if (tokenType == FluentTypes.SYMBOL && (builder.lookAhead(1) == FluentTypes.EQ || builder.lookAhead(1) == FluentTypes.COLON)) {
                break
            }
            
            // Check for attribute dot
            if (tokenType == FluentTypes.DOT) {
                // If we encounter a dot, it's the start of an attribute
                // Break the pattern parsing to allow the parent method to parse the attribute
                break
            }
            
            when (tokenType) {
                FluentTypes.BRACE_L -> {
                    parseInlinePlaceable(builder)
                }
                FluentTypes.STRING_LITERAL, FluentTypes.TEXT_LINE, FluentTypes.SYMBOL, FluentTypes.COMMA, FluentTypes.COLON, FluentTypes.EQ, FluentTypes.SEMICOLON, FluentTypes.STAR, FluentTypes.TO, FluentTypes.HYPHEN, FluentTypes.INTEGER, FluentTypes.DECIMAL -> {
                    val textMarker = builder.mark()
                    builder.advanceLexer()
                    textMarker.done(FluentTypes.INLINE_TEXT)
                }
                else -> {
                    builder.advanceLexer()
                }
            }
        }
        
        patternMarker.done(FluentTypes.PATTERN)
    }

    private fun parseInlinePlaceable(builder: PsiBuilder) {
        val placeableMarker = builder.mark()
        
        // Consume BRACE_L
        builder.advanceLexer()
        
        parseExpression(builder)
        
        // Consume BRACE_R
        if (builder.tokenType == FluentTypes.BRACE_R) {
            builder.advanceLexer()
        }
        
        placeableMarker.done(FluentTypes.INLINE_PLACEABLE)
    }

    private fun parseExpression(builder: PsiBuilder) {
        val expressionMarker = builder.mark()
        
        when (builder.tokenType) {
            FluentTypes.SYMBOL -> {
                if (builder.lookAhead(1) == FluentTypes.PARENTHESIS_L) {
                    parseFunctionReference(builder)
                } else {
                    parseMessageReference(builder)
                }
            }
            FluentTypes.DOLLAR -> {
                parseVariableReference(builder)
            }
            else -> {
                builder.advanceLexer()
            }
        }
        
        expressionMarker.done(FluentTypes.EXPRESSION)
    }

    private fun parseFunctionReference(builder: PsiBuilder) {
        val functionMarker = builder.mark()
        
        // Parse function ID
        val idMarker = builder.mark()
        builder.advanceLexer() // Consume symbol
        idMarker.done(FluentTypes.FUNCTION_ID)
        
        // Consume PARENTHESIS_L
        if (builder.tokenType == FluentTypes.PARENTHESIS_L) {
            builder.advanceLexer()
            parseCallArguments(builder)
            
            // Consume PARENTHESIS_R
            if (builder.tokenType == FluentTypes.PARENTHESIS_R) {
                builder.advanceLexer()
            }
        }
        
        functionMarker.done(FluentTypes.FUNCTION_REFERENCE)
    }

    private fun parseMessageReference(builder: PsiBuilder) {
        val referenceMarker = builder.mark()
        
        builder.advanceLexer() // Consume symbol
        
        // Handle attribute access (e.g., message.attribute)
        while (builder.tokenType == FluentTypes.DOT) {
            builder.advanceLexer() // Consume DOT
            if (builder.tokenType == FluentTypes.SYMBOL) {
                val attributeMarker = builder.mark()
                builder.advanceLexer() // Consume attribute name
                attributeMarker.done(FluentTypes.ATTRIBUTE_ID)
            }
        }
        
        referenceMarker.done(FluentTypes.MESSAGE_REFERENCE)
    }

    private fun parseVariableReference(builder: PsiBuilder) {
        val variableMarker = builder.mark()
        
        builder.advanceLexer() // Consume DOLLAR
        
        if (builder.tokenType == FluentTypes.SYMBOL) {
            val idMarker = builder.mark()
            builder.advanceLexer() // Consume symbol
            idMarker.done(FluentTypes.VARIABLE_ID)
        }
        
        variableMarker.done(FluentTypes.EXPRESSION)
    }

    private fun parseCallArguments(builder: PsiBuilder) {
        val argumentsMarker = builder.mark()
        
        while (builder.tokenType != FluentTypes.PARENTHESIS_R && !builder.eof()) {
            parseArgument(builder)
            
            if (builder.tokenType == FluentTypes.COMMA) {
                builder.advanceLexer() // Consume COMMA
            }
        }
        
        argumentsMarker.done(FluentTypes.CALL_ARGUMENTS)
    }

    private fun parseArgument(builder: PsiBuilder) {
        val argumentMarker = builder.mark()
        
        if (builder.tokenType == FluentTypes.SYMBOL && builder.lookAhead(1) == FluentTypes.EQ) {
            parseNamedArgument(builder)
        } else {
            parseExpression(builder)
        }
        
        argumentMarker.done(FluentTypes.ARGUMENT)
    }

    private fun parseNamedArgument(builder: PsiBuilder) {
        val namedArgumentMarker = builder.mark()
        
        builder.advanceLexer() // Consume symbol
        builder.advanceLexer() // Consume EQ
        parseExpression(builder)
        
        namedArgumentMarker.done(FluentTypes.NAMED_ARGUMENT)
    }

}