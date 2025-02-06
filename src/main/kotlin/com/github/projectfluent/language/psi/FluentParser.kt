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
                    // Skip whitespace and comments between symbol and EQ
                    var lookAheadOffset = 1
                    while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
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
                        // Skip whitespace and comments between symbol and EQ
                        var lookAheadOffset = 1
                        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
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
        
        // Parse attributes
        while (!builder.eof()) {
            // Skip whitespace first
            while (builder.tokenType == TokenType.WHITE_SPACE) {
                builder.advanceLexer()
            }
            
            // Check if this is a message boundary
            if (isMessageBoundary(builder)) {
                break
            }
            
            // If the next token is a dot, try to parse an attribute
            if (builder.tokenType == FluentTypes.DOT) {
                if (!parseAttribute(builder)) {
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
        // No whitespace between HYPHEN and SYMBOL in valid Fluent syntax
        val idMarker = builder.mark()
        if (builder.tokenType == FluentTypes.HYPHEN) {
            builder.advanceLexer() // Consume hyphen as part of TERM_ID
        }
        if (builder.tokenType == FluentTypes.SYMBOL) {
            builder.advanceLexer() // Consume symbol as part of TERM_ID
        }
        idMarker.done(FluentTypes.TERM_ID)
        
        // Skip whitespace between term ID and EQ
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val whitespaceMarker = builder.mark()
            builder.advanceLexer()
            whitespaceMarker.done(TokenType.WHITE_SPACE)
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
        
        // Parse attributes - same approach as in parseMessage
        while (!builder.eof()) {
            // Skip whitespace first
            while (builder.tokenType == TokenType.WHITE_SPACE) {
                builder.advanceLexer()
            }

            // Check if this is a message boundary
            if (isMessageBoundary(builder)) {
                break
            }

            // If the next token is a dot, try to parse an attribute
            if (builder.tokenType == FluentTypes.DOT) {
                if (!parseAttribute(builder)) {
                    // This is not an attribute, break
                    break
                }
            } else {
                // This is not an attribute or new message, break
                break
            }
        }
        
        termMarker.done(FluentTypes.TERM)
    }

    private fun parseAttribute(builder: PsiBuilder): Boolean {
        // Check if this is actually an attribute
        // Check if there's a comment after the dot without consuming the dot
        var lookAheadOffset = 1
        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
            lookAheadOffset++
        }
        
        // If there's a comment after the dot, it's not an attribute
        if (builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
            return false
        }
        
        // Check if this is an attribute start
        var symbolOffset = lookAheadOffset
        if (builder.lookAhead(symbolOffset) != FluentTypes.SYMBOL) {
            return false
        }
        
        var eqOffset = symbolOffset + 1
        while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
            eqOffset++
        }
        
        if (builder.lookAhead(eqOffset) != FluentTypes.EQ) {
            return false
        }
        
        // Now parse the attribute
        val attributeMarker = builder.mark()
        
        // Parse attribute ID (DOT + SYMBOL together as ATTRIBUTE_ID)
        val idMarker = builder.mark()
        if (builder.tokenType == FluentTypes.DOT) {
            builder.advanceLexer() // Consume DOT token
        }
        
        // Skip only whitespace between DOT and SYMBOL
        while (builder.tokenType == TokenType.WHITE_SPACE) {
            val marker = builder.mark()
            builder.advanceLexer()
            marker.done(TokenType.WHITE_SPACE)
        }
        
        if (builder.tokenType == FluentTypes.SYMBOL) {
            builder.advanceLexer() // Consume symbol
        } else {
            // This is not an attribute, drop the markers and return
            idMarker.drop()
            attributeMarker.drop()
            return false
        }
        idMarker.done(FluentTypes.ATTRIBUTE_ID)
        
        // Skip whitespace and comments between attribute ID and EQ
        while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
            val marker = builder.mark()
            val tokenType = builder.tokenType
            builder.advanceLexer()
            marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
        }
        
        // Parse EQ - wrap it in a FluentElement that contains the EQ token
        if (builder.tokenType == FluentTypes.EQ) {
            val eqElementMarker = builder.mark()
            builder.advanceLexer() // Consume EQ token - it will be added as a child of eqElementMarker as a token
            eqElementMarker.done(FluentTypes.EQ)
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
        
        attributeMarker.done(FluentTypes.ATTRIBUTE)
        return true
    }

    private fun parsePattern(builder: PsiBuilder) {
        val patternMarker = builder.mark()
        
        while (!builder.eof()) {
            // Skip whitespace first
            while (!builder.eof() && builder.tokenType == TokenType.WHITE_SPACE) {
                val marker = builder.mark()
                builder.advanceLexer()
                marker.done(TokenType.WHITE_SPACE)
            }
            
            if (builder.eof()) break
            
            // Check if this is a comment line
            if (builder.tokenType == FluentTypes.COMMENT_LINE) {
                // This is a comment, end pattern parsing
                break
            }
            
            // Check if this is a message boundary (after skipping whitespace)
            if (isMessageBoundary(builder)) {
                // New message or term start, end pattern parsing
                break
            }
            
            // Check if this is an attribute start (DOT followed by SYMBOL and EQ)
            // Attributes should not be part of the pattern
            if (builder.tokenType == FluentTypes.DOT && isAttributeStart(builder)) {
                break
            }
            
            val tokenType = builder.tokenType
            
            when (tokenType) {
                FluentTypes.BRACE_L -> {
                    parseInlinePlaceable(builder)
                }
                else -> {
                    // Consume the token as text
                    val inlineTextMarker = builder.mark()
                    builder.advanceLexer()
                    inlineTextMarker.done(FluentTypes.INLINE_TEXT)
                }
            }
        }
        
        patternMarker.done(FluentTypes.PATTERN)
    }
    
    private fun isAttributeStart(builder: PsiBuilder): Boolean {
        // Attribute must start with DOT followed by SYMBOL (with optional whitespace in between, but no comments)
        var lookAheadOffset = 1
        
        // Skip whitespace but not comments
        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE) {
            lookAheadOffset++
        }
        
        // If there's a comment after the dot, it's not an attribute
        if (builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
            return false
        }
        
        // If there's no symbol after the dot, it's not an attribute
        if (builder.lookAhead(lookAheadOffset) != FluentTypes.SYMBOL) {
            return false
        }
        
        // Check if the symbol is followed by EQ (with optional whitespace and comments in between)
        var eqOffset = lookAheadOffset + 1
        while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
            eqOffset++
        }
        
        // If there's no EQ after the symbol, it's not an attribute
        return builder.lookAhead(eqOffset) == FluentTypes.EQ
    }
    
    private fun hasBlankLineBefore(builder: PsiBuilder): Boolean {
        // Check if there's a blank line (double newline) before the current position
        // by examining the original text
        val originalText = builder.originalText
        val currentOffset = builder.currentOffset

        // Look backwards for double newline pattern
        // We need to find \n\n or \n\r\n before the current position
        var i = currentOffset - 1
        var foundFirstNewline = false
        var foundNonWhitespaceAfterFirstNewline = false

        while (i >= 0) {
            val c = originalText[i]
            when {
                c == '\n' -> {
                    if (foundFirstNewline) {
                        // Found \n\n pattern - this is a blank line
                        // But we need to make sure there was no non-whitespace between the two newlines
                        if (!foundNonWhitespaceAfterFirstNewline) {
                            return true
                        }
                    }
                    foundFirstNewline = true
                    foundNonWhitespaceAfterFirstNewline = false
                }
                c == '\r' || c == ' ' || c == '\t' -> {
                    // Skip carriage return, spaces, and tabs
                }
                else -> {
                    // Found non-whitespace character
                    if (foundFirstNewline) {
                        // There's content after the first newline, so no blank line
                        foundNonWhitespaceAfterFirstNewline = true
                    } else {
                        // Found non-whitespace before any newline
                        return false
                    }
                }
            }
            i--
        }

        return false
    }

    private fun hasBlankLineAfter(builder: PsiBuilder): Boolean {
        // Check if there's a blank line (double newline) after the current position
        // by examining the original text
        val originalText = builder.originalText
        val currentOffset = builder.currentOffset

        // Look forwards for double newline pattern
        // We need to find \n\n or \n\r\n after the current position
        // Skip the current character (e.g., DOT) and start from the next character
        var i = currentOffset + 1
        var foundFirstNewline = false

        while (i < originalText.length) {
            val c = originalText[i]
            when {
                c == '\n' -> {
                    if (foundFirstNewline) {
                        // Found \n\n pattern - this is a blank line
                        return true
                    }
                    foundFirstNewline = true
                }
                c == '\r' || c == ' ' || c == '\t' -> {
                    // Skip carriage return, spaces, and tabs
                }
                else -> {
                    // Found non-whitespace character
                    return false
                }
            }
            i++
        }

        return false
    }
    


    private fun parseInlinePlaceable(builder: PsiBuilder) {
        val placeableMarker = builder.mark()
        
        // Consume BRACE_L
        builder.advanceLexer()
        
        // Track the number of open braces to handle nested placeables
        var openBraces = 1
        
        while (openBraces > 0 && !builder.eof()) {
            // Check for message boundary before processing each token
            if (isMessageBoundary(builder)) {
                // New message start, break out of the loop without consuming tokens
                // Drop the placeable marker to avoid creating an incomplete placeable
                placeableMarker.drop()
                return
            }
            
            when (builder.tokenType) {
                FluentTypes.BRACE_L -> {
                    // Recursively parse nested placeable
                    parseInlinePlaceable(builder)
                }
                FluentTypes.BRACE_R -> {
                    openBraces--
                    if (openBraces > 0) {
                        builder.advanceLexer()
                    } else {
                        // This is the closing brace for the current placeable
                        builder.advanceLexer()
                    }
                }
                FluentTypes.DOLLAR -> {
                    if (startsSelectExpression(builder)) {
                        parseSelectExpression(builder)
                    } else {
                        parseVariableReference(builder)
                    }
                }
                FluentTypes.SYMBOL -> {
                    if (startsSelectExpression(builder)) {
                        parseSelectExpression(builder)
                    } else if (builder.lookAhead(1) == FluentTypes.PARENTHESIS_L) {
                        // Parse function reference
                        parseFunctionReference(builder)
                    } else {
                        // Parse message reference
                        parseMessageReference(builder)
                    }
                }
                FluentTypes.IF_KEYWORD -> {
                    // Parse select expression
                    parseSelectExpression(builder)
                }
                else -> {
                    // Consume the token as inline text
                    val inlineTextMarker = builder.mark()
                    builder.advanceLexer()
                    inlineTextMarker.done(FluentTypes.INLINE_TEXT)
                }
            }
        }
        
        // Only create the placeable if we properly closed all braces
        if (openBraces == 0) {
            placeableMarker.done(FluentTypes.INLINE_PLACEABLE)
        } else {
            // Incomplete placeable, drop the marker
            placeableMarker.drop()
        }
    }

    private fun parseExpression(builder: PsiBuilder) {
        val expressionMarker = builder.mark()
        
        when (builder.tokenType) {
            FluentTypes.BRACE_L -> {
                parseInlinePlaceable(builder)
            }
            FluentTypes.SYMBOL -> {
                if (startsSelectExpression(builder)) {
                    parseSelectExpression(builder)
                } else if (builder.lookAhead(1) == FluentTypes.PARENTHESIS_L) {
                    parseFunctionReference(builder)
                } else {
                    parseMessageReference(builder)
                }
            }
            FluentTypes.DOLLAR -> {
                if (startsSelectExpression(builder)) {
                    parseSelectExpression(builder)
                } else {
                    parseVariableReference(builder)
                }
            }
            FluentTypes.IF_KEYWORD -> {
                parseSelectExpression(builder)
            }
            else -> {
                builder.advanceLexer()
            }
        }
        
        expressionMarker.done(FluentTypes.EXPRESSION)
    }
    
    private fun parseSelectExpression(builder: PsiBuilder) {
        when (builder.tokenType) {
            FluentTypes.DOLLAR -> parseVariableReference(builder)
            FluentTypes.SYMBOL -> {
                if (builder.lookAhead(1) == FluentTypes.PARENTHESIS_L) {
                    parseFunctionReference(builder)
                } else {
                    parseMessageReference(builder)
                }
            }
            FluentTypes.IF_KEYWORD -> builder.advanceLexer()
            FluentTypes.BRACE_L -> parseInlinePlaceable(builder)
            else -> builder.advanceLexer()
        }

        while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
            val marker = builder.mark()
            val tokenType = builder.tokenType
            builder.advanceLexer()
            marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
        }
        
        // Check if we found a message boundary
        if (isMessageBoundary(builder)) {
            return
        }
        
        // Consume "->"
        if (isArrowOperator(builder)) {
            // Consume HYPHEN
            builder.advanceLexer()
            // Skip whitespace between HYPHEN and ANGLE_R
            while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
                val marker = builder.mark()
                val tokenType = builder.tokenType
                builder.advanceLexer()
                marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
            }
            // Consume ANGLE_R
            if (builder.tokenType == FluentTypes.ANGLE_R) {
                builder.advanceLexer()
            }
        }
        
        // Parse the cases
        while (builder.tokenType != FluentTypes.BRACE_R && !builder.eof() && !isMessageBoundary(builder)) {
            // Skip whitespace and comments
            while (builder.tokenType == TokenType.WHITE_SPACE || builder.tokenType == FluentTypes.COMMENT_LINE) {
                val marker = builder.mark()
                val tokenType = builder.tokenType
                builder.advanceLexer()
                marker.done(if (tokenType == TokenType.WHITE_SPACE) TokenType.WHITE_SPACE else FluentTypes.COMMENT_LINE)
            }
            
            if (builder.tokenType == FluentTypes.BRACE_R || isMessageBoundary(builder)) break
            
            // Parse case
            if (builder.tokenType == FluentTypes.STAR) {
                builder.advanceLexer() // Consume star for default case
            }
            if (builder.tokenType == FluentTypes.BRACKET_L) {
                builder.advanceLexer() // Consume BRACKET_L

                val variantKeyMarker = builder.mark()
                while (builder.tokenType != FluentTypes.BRACKET_R && !builder.eof() && !isMessageBoundary(builder)) {
                    builder.advanceLexer()
                }
                variantKeyMarker.done(FluentTypes.VARIANT_KEY)

                if (builder.tokenType == FluentTypes.BRACKET_R) {
                    builder.advanceLexer()
                }
            }
            
            // Parse case pattern
            while (builder.tokenType != FluentTypes.BRACKET_L && builder.tokenType != FluentTypes.BRACE_R && !builder.eof() && !isMessageBoundary(builder)) {
                val currentToken = builder.tokenType
                if (currentToken == FluentTypes.BRACE_L) {
                    parseInlinePlaceable(builder)
                } else {
                    if (currentToken == TokenType.WHITE_SPACE || currentToken == FluentTypes.COMMENT_LINE) {
                        builder.advanceLexer()
                    } else {
                        val inlineTextMarker = builder.mark()
                        builder.advanceLexer()
                        inlineTextMarker.done(FluentTypes.INLINE_TEXT)
                    }
                }
            }
        }
        
        // Consume the closing brace if it's there
        if (builder.tokenType == FluentTypes.BRACE_R) {
            builder.advanceLexer()
        }
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
        
        // Parse message ID
        val messageIdMarker = builder.mark()
        builder.advanceLexer() // Consume symbol
        messageIdMarker.done(FluentTypes.MESSAGE_ID)
        
        // Handle attribute access (e.g., message.attribute)
        while (builder.tokenType == FluentTypes.DOT) {
            builder.advanceLexer() // Consume DOT
            if (builder.tokenType == FluentTypes.SYMBOL) {
                val attributeMarker = builder.mark()
                builder.advanceLexer() // Consume attribute name
                attributeMarker.done(FluentTypes.ATTRIBUTE_ID)
            }
        }
        
        referenceMarker.done(FluentTypes.SYMBOL_REFERENCE)
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
        
        variableMarker.done(FluentTypes.VARIABLE_REFERENCE)
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
    
    private fun isArrowOperator(builder: PsiBuilder): Boolean {
        if (builder.tokenType != FluentTypes.HYPHEN) {
            return false
        }
        
        var lookAheadOffset = 1
        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
            lookAheadOffset++
        }
        
        return builder.lookAhead(lookAheadOffset) == FluentTypes.ANGLE_R
    }

    private fun startsSelectExpression(builder: PsiBuilder): Boolean {
        return when (builder.tokenType) {
            FluentTypes.DOLLAR -> {
                if (builder.lookAhead(1) != FluentTypes.SYMBOL) {
                    false
                } else {
                    var lookAheadOffset = 2
                    while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
                        lookAheadOffset++
                    }
                    builder.lookAhead(lookAheadOffset) == FluentTypes.HYPHEN && builder.lookAhead(lookAheadOffset + 1) == FluentTypes.ANGLE_R
                }
            }
            FluentTypes.SYMBOL -> {
                var lookAheadOffset = 1
                if (builder.lookAhead(lookAheadOffset) == FluentTypes.PARENTHESIS_L) {
                    var depth = 0
                    while (true) {
                        val tokenType = builder.lookAhead(lookAheadOffset) ?: return false
                        if (tokenType == FluentTypes.PARENTHESIS_L) {
                            depth++
                        } else if (tokenType == FluentTypes.PARENTHESIS_R) {
                            depth--
                            if (depth == 0) {
                                lookAheadOffset++
                                break
                            }
                        }
                        lookAheadOffset++
                    }
                }
                while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
                    lookAheadOffset++
                }
                builder.lookAhead(lookAheadOffset) == FluentTypes.HYPHEN && builder.lookAhead(lookAheadOffset + 1) == FluentTypes.ANGLE_R
            }
            FluentTypes.IF_KEYWORD -> true
            else -> false
        }
    }
    
    private fun isMessageStart(builder: PsiBuilder): Boolean {
        var lookAheadOffset = 1
        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
            lookAheadOffset++
        }
        return builder.lookAhead(lookAheadOffset) == FluentTypes.EQ
    }
    
    private fun isTermStart(builder: PsiBuilder): Boolean {
        var lookAheadOffset = 1
        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
            lookAheadOffset++
        }
        if (builder.lookAhead(lookAheadOffset) != FluentTypes.SYMBOL) {
            return false
        }
        
        var eqOffset = lookAheadOffset + 1
        while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
            eqOffset++
        }
        return builder.lookAhead(eqOffset) == FluentTypes.EQ
    }
    
    private fun isMessageBoundary(builder: PsiBuilder): Boolean {
        return isMessageBoundary(builder, 0)
    }
    
    private fun isMessageBoundary(builder: PsiBuilder, startOffset: Int): Boolean {
        // Skip whitespace and comments
        var lookAheadOffset = startOffset
        while (builder.lookAhead(lookAheadOffset) == TokenType.WHITE_SPACE || builder.lookAhead(lookAheadOffset) == FluentTypes.COMMENT_LINE) {
            lookAheadOffset++
        }

        // Check if this is a new message start (SYMBOL followed by EQ)
        if (builder.lookAhead(lookAheadOffset) == FluentTypes.SYMBOL) {
            var eqOffset = lookAheadOffset + 1
            while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
                eqOffset++
            }
            if (builder.lookAhead(eqOffset) == FluentTypes.EQ) {
                return true
            }
        }

        // Check if this is a term start (HYPHEN followed by SYMBOL and EQ)
        if (builder.lookAhead(lookAheadOffset) == FluentTypes.HYPHEN) {
            var symbolOffset = lookAheadOffset + 1
            while (builder.lookAhead(symbolOffset) == TokenType.WHITE_SPACE || builder.lookAhead(symbolOffset) == FluentTypes.COMMENT_LINE) {
                symbolOffset++
            }
            if (builder.lookAhead(symbolOffset) == FluentTypes.SYMBOL) {
                var eqOffset = symbolOffset + 1
                while (builder.lookAhead(eqOffset) == TokenType.WHITE_SPACE || builder.lookAhead(eqOffset) == FluentTypes.COMMENT_LINE) {
                    eqOffset++
                }
                if (builder.lookAhead(eqOffset) == FluentTypes.EQ) {
                    return true
                }
            }
        }

        return false
    }

}
