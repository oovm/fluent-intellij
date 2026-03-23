package com.github.projectfluent.language

import com.github.projectfluent.language.psi.FluentParserDefinition
import com.intellij.testFramework.ParsingTestCase

class FluentParserTest : ParsingTestCase("parser", "ftl", FluentParserDefinition) {
    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    fun testEmpty() {
        doTest(true, true)
    }

    fun testIssue8() {
        doTest(true, true)
    }

    fun testSimpleMessages() {
        doTest(true, true)
    }

    fun testPlaceables() {
        doTest(true, true)
    }

    fun testAttributes() {
        doTest(true, true)
    }

    fun testTerms() {
        doTest(true, true)
    }

    fun testSelectExpressions() {
        doTest(true, true)
    }

    fun testFunctionCalls() {
        doTest(true, true)
    }

    fun testNestedPlaceables() {
        doTest(true, true)
    }

    override fun includeRanges(): Boolean {
        return true
    }
}
