package com.github.projectfluent.language

import com.github.projectfluent.language.psi.FluentParserDefinition
import com.intellij.testFramework.ParsingTestCase

class FluentParserTest : ParsingTestCase("parser", "ftl", FluentParserDefinition) {
    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    fun testEmpty() {
        doTest(true)
    }

    fun testIssue8() {
        doTest(true)
    }

    fun testSimpleMessages() {
        doTest(true)
    }

    fun testPlaceables() {
        doTest(true)
    }

    fun testAttributes() {
        doTest(true)
    }

    fun testTerms() {
        doTest(true)
    }

    fun testSelectExpressions() {
        doTest(true)
    }

    fun testFunctionCalls() {
        doTest(true)
    }

    fun testNestedPlaceables() {
        doTest(true)
    }

    override fun includeRanges(): Boolean {
        return true
    }
}
