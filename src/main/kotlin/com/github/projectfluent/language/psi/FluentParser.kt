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
                FluentTypes.HYPHEN -> {
                    // Check if this is a term (starts with - followed by symbol and =)
                    var lookAheadOffset = 1
                    // Skip whitespace and comments when looking ahead
                    while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
                        lookAheadOffset++
                    }
                    
                    if (builder.lookAhead(lookAheadOffset) == FluentTypes.SYMBOL) {
                        // Skip whitespace and comments between symbol and EQ
                        var eqOffset = lookAheadOffset + 1
                        while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
                            eqOffset++
                        }
                        
                        if (builder.lookAhead(eqOffset) == FluentTypes.EQ) {
                            parseTerm(builder)
                        } else {
                            val hyphenMarker = builder.mark()
                            builder.advanceLexer()
                            hyphenMarker.done(FluentTypes.INLINE_TEXT)
                        }
                    } else {
                        val hyphenMarker = builder.mark()
                        builder.advanceLexer()
                        hyphenMarker.done(FluentTypes.INLINE_TEXT)
                    }
                }
                FluentTypes.SYMBOL -> {
                    // Skip whitespace between symbol and EQ
                    var lookAheadOffset = 1
                    while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                        lookAheadOffset++
                    }
                    
                    if (builder.lookAhead(lookAheadOffset) == FluentTypes.EQ) {
                        parseMessage(builder)
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
        
        rootMarker.done(root)
        return builder.treeBuilt
    }

    override fun parseLight(root: IElementType?, builder: PsiBuilder?) {
        if (root != null && builder != null) {
            val rootMarker = builder.mark()
            
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
                    FluentTypes.HYPHEN -> {
                    // Check if this is a term (starts with - followed by symbol and =)
                    var lookAheadOffset = 1
                    // Skip whitespace and comments when looking ahead
                    while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
                        lookAheadOffset++
                    }
                    
                    if (builder.lookAhead(lookAheadOffset) == FluentTypes.SYMBOL) {
                        // Skip whitespace and comments between symbol and EQ
                        var eqOffset = lookAheadOffset + 1
                        while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
                            eqOffset++
                        }
                        
                        if (builder.lookAhead(eqOffset) == FluentTypes.EQ) {
                            parseTerm(builder)
                        } else {
                            val hyphenMarker = builder.mark()
                            builder.advanceLexer()
                            hyphenMarker.done(FluentTypes.INLINE_TEXT)
                        }
                    } else {
                        val hyphenMarker = builder.mark()
                        builder.advanceLexer()
                        hyphenMarker.done(FluentTypes.INLINE_TEXT)
                    }
                }
                    FluentTypes.SYMBOL -> {
                        // Skip whitespace between symbol and EQ
                        var lookAheadOffset = 1
                        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                            lookAheadOffset++
                        }
                        
                        if (builder.lookAhead(lookAheadOffset) == FluentTypes.EQ) {
                            parseMessage(builder)
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
        
        // Parse EQ - wrap it in a FluentElement that contains the EQ token
        if (builder.tokenType == FluentTypes.EQ) {
            val eqElementMarker = builder.mark()
            builder.advanceLexer() // Consume EQ token - it will be added as a child of eqElementMarker as a token
            eqElementMarker.done(FluentTypes.EQ)
        }
        
        // Skip whitespace after EQ
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        // Parse pattern
        parsePattern(builder)
        
        // Check if there's more content after the pattern
        while (!builder.eof()) {
            // Skip whitespace
            while (builder.tokenType == TokenType.WHITE_SPACE) {
                val whitespaceMarker = builder.mark()
                builder.advanceLexer()
                whitespaceMarker.done(TokenType.WHITE_SPACE)
            }
            
            if (builder.eof()) break
            
            // Check if this is a comment line
            if (builder.tokenType == FluentTypes.COMMENT_LINE) {
                // This is a comment, which means the next content is a new message
                break
            }
            
            // Check if this is a new message
            if (builder.tokenType == FluentTypes.SYMBOL && builder.lookAhead(1) == FluentTypes.EQ) {
                // This is a new message, break and let the main parse loop handle it
                break
            }
            
            // Check if this is an attribute
            if (builder.tokenType == FluentTypes.DOT) {
                // Look ahead to check if this is an attribute or a new message
                var lookAheadOffset = 1
                while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                    lookAheadOffset++
                }
                
                if (builder.lookAhead(lookAheadOffset) == FluentTypes.SYMBOL) {
                    // In Fluent, .symbol = after a message is always an attribute
                    // (unless preceded by a blank line, which would have broken the pattern already)
                    parseAttribute(builder)
                } else {
                    // This is not an attribute, break
                    break
                }
            } else {
                // This is not an attribute or new message, break
                break
            }
        }
        
        messageMarker.done(FluentTypes.MESSAGE)
    }

    private fun parseTerm(builder: PsiBuilder) {
        val termMarker = builder.mark()
        
        // Parse term ID (HYPHEN + SYMBOL together as TERM_ID)
        val idMarker = builder.mark()
        if (builder.tokenType == FluentTypes.HYPHEN) {
            builder.advanceLexer() // Consume hyphen as part of TERM_ID
        }
        
        // Skip whitespace and comments between hyphen and symbol
        while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
            val marker = builder.mark()
            val tokenType = builder.tokenType
            builder.advanceLexer()
            marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
        }
        
        if (builder.tokenType == FluentTypes.SYMBOL) {
            builder.advanceLexer() // Consume symbol as part of TERM_ID
        }
        idMarker.done(FluentTypes.TERM_ID)
        
        // Skip whitespace and comments between symbol and EQ
        while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
            val marker = builder.mark()
            val tokenType = builder.tokenType
            builder.advanceLexer()
            marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
        }
        
        // Consume EQ
        if (builder.tokenType == FluentTypes.EQ) {
            val eqMarker = builder.mark()
            builder.advanceLexer()
            eqMarker.done(FluentTypes.EQ)
        }
        
        // Skip whitespace and comments after EQ
        while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
            val marker = builder.mark()
            val tokenType = builder.tokenType
            builder.advanceLexer()
            marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
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
        
        // Parse attribute ID (DOT + SYMBOL together as ATTRIBUTE_ID)
        val idMarker = builder.mark()
        if (builder.tokenType == FluentTypes.DOT) {
            builder.advanceLexer() // Consume DOT token
        }
        
        // Skip whitespace between DOT and attribute ID symbol
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        if (builder.tokenType == FluentTypes.SYMBOL) {
            builder.advanceLexer() // Consume symbol
        }
        idMarker.done(FluentTypes.ATTRIBUTE_ID)
        
        // Skip whitespace between attribute ID and EQ
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
        // Parse EQ - wrap it in a FluentElement that contains the EQ token
        if (builder.tokenType == FluentTypes.EQ) {
            val eqElementMarker = builder.mark()
            builder.advanceLexer() // Consume EQ token - it will be added as a child of eqElementMarker as a token
            eqElementMarker.done(FluentTypes.EQ)
        }
        
        // Skip whitespace after EQ
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
        }
        
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
                val tokenType = builder.tokenType
                builder.advanceLexer()
                marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
            }
            
            if (builder.eof()) break
            
            val tokenType = builder.tokenType
            if (tokenType == FluentTypes.SYMBOL && builder.lookAhead(1) == FluentTypes.EQ) {
                break
            }
            
            // Don't break on symbol followed by colon inside a pattern - it's probably part of the text
            if (tokenType == FluentTypes.SYMBOL && builder.lookAhead(1) == FluentTypes.COLON) {
                // Check if this is actually a term start by looking ahead for whitespace and EQ
                var lookAheadOffset = 2
                while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                    lookAheadOffset++
                }
                if (builder.lookAhead(lookAheadOffset) == FluentTypes.EQ) {
                    // This is a term start, break
                    break
                }
            }
            
            // Check for attribute dot
            if (tokenType == FluentTypes.DOT) {
                // Only break if the dot is followed by a symbol (attribute ID)
                var lookAheadOffset = 1
                while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
                    lookAheadOffset++
                }
                if (builder.lookAhead(lookAheadOffset) == FluentTypes.SYMBOL) {
                    // Check if this is a comment line before the attribute
                    var commentOffset = lookAheadOffset + 1
                    while (builder.lookAhead(commentOffset) == TokenType.WHITE_SPACE) {
                        commentOffset++
                    }
                    if (builder.lookAhead(commentOffset) == FluentTypes.COMMENT_LINE) {
                        // This is a new message, not an attribute
                        break
                    }
                    // If we encounter a dot followed by a symbol, it's the start of an attribute
                    // Break the pattern parsing to allow the parent method to parse the attribute
                    break
                }
            }
            
            // Check for term start (hyphen followed by symbol and =)
            if (tokenType == FluentTypes.HYPHEN) {
                var lookAheadOffset = 1
                // Skip whitespace and comments when looking ahead
                while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
                    lookAheadOffset++
                }
                if (builder.lookAhead(lookAheadOffset) == FluentTypes.SYMBOL) {
                    var eqOffset = lookAheadOffset + 1
                    // Skip whitespace and comments when looking ahead for EQ
                    while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
                        eqOffset++
                    }
                    if (builder.lookAhead(eqOffset) == FluentTypes.EQ) {
                        // If we encounter a hyphen followed by symbol and =, it's the start of a new term
                        // Break the pattern parsing to allow the parent method to parse the term
                        break
                    }
                }
            }
            
            when (tokenType) {
                FluentTypes.BRACE_L -> {
                    parseInlinePlaceable(builder)
                }
                FluentTypes.STRING_LITERAL, FluentTypes.TEXT_LINE, FluentTypes.COMMA, FluentTypes.COLON, FluentTypes.EQ, FluentTypes.SEMICOLON, FluentTypes.STAR, FluentTypes.TO, FluentTypes.INTEGER, FluentTypes.DECIMAL -> {
                    val textMarker = builder.mark()
                    builder.advanceLexer()
                    textMarker.done(FluentTypes.INLINE_TEXT)
                }
                FluentTypes.SYMBOL -> {
                    // Wrap SYMBOL in INLINE_TEXT as expected
                    val inlineTextMarker = builder.mark()
                    val symbolElementMarker = builder.mark()
                    builder.advanceLexer() // Consume symbol - it will be added as a child of symbolElementMarker as a token
                    symbolElementMarker.done(FluentTypes.SYMBOL)
                    inlineTextMarker.done(FluentTypes.INLINE_TEXT)
                }
                FluentTypes.HYPHEN -> {
                    // If we reach here, it's not a term start (already checked above)
                    // Treat as inline text
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
        
        if (builder.tokenType == FluentTypes.DOLLAR) {
            val idMarker = builder.mark()
            builder.advanceLexer() // Consume DOLLAR
            
            if (builder.tokenType == FluentTypes.SYMBOL) {
                builder.advanceLexer() // Consume symbol
                idMarker.done(FluentTypes.VARIABLE_ID)
            } else {
                idMarker.drop()
            }
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
        
        if (builder.tokenType == FluentTypes.SYMBOL && builder.lookAhead(1) == FluentTypes.COLON) {
            parseNamedArgument(builder)
        } else {
            parseExpression(builder)
        }
        
        argumentMarker.done(FluentTypes.ARGUMENT)
    }

    private fun parseNamedArgument(builder: PsiBuilder) {
        val namedArgumentMarker = builder.mark()
        
        builder.advanceLexer() // Consume symbol
        builder.advanceLexer() // Consume COLON
        parseExpression(builder)
        
        namedArgumentMarker.done(FluentTypes.NAMED_ARGUMENT)
    }

}