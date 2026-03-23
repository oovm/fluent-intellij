package com.github.projectfluent.language

import com.github.projectfluent.language.psi.FluentLexer
import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class FluentLexerTest : LexerTestCase() {
    override fun getDirPath(): String {
        return "lexer"
    }

    override fun createLexer(): Lexer {
        return FluentLexer()
    }

    fun testEmpty() {
        doTest("empty.ftl")
    }

    fun testWhitespace() {
        doTest("whitespace.ftl")
    }

    fun testComments() {
        doTest("comments.ftl")
    }

    fun testSimpleMessages() {
        doTest("simple-messages.ftl")
    }

    fun testStringLiterals() {
        doTest("string-literals.ftl")
    }

    fun testNumberLiterals() {
        doTest("number-literals.ftl")
    }

    fun testMessageReferences() {
        doTest("message-references.ftl")
    }

    fun testSelectExpressions() {
        doTest("select-expressions.ftl")
    }

    fun testFunctionCalls() {
        doTest("function-calls.ftl")
    }

    fun testInlinePlaceables() {
        doTest("inline-placeables.ftl")
    }
}
