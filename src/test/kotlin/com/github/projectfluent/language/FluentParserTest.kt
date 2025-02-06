package com.github.projectfluent.language

import com.github.projectfluent.language.psi.nodes.FluentFileNode
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class FluentParserTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData"

    fun testEmpty() = doParseTest("empty.ftl")

    fun testIssue8() = doParseTest("issue8.ftl")

    fun testSimpleMessages() = doParseTest("SimpleMessages.ftl")

    fun testPlaceables() = doParseTest("Placeables.ftl")

    fun testAttributes() = doParseTest("attributes.ftl")

    fun testTerms() = doParseTest("terms.ftl")

    fun testSelectExpressions() = doParseTest("SelectExpressions.ftl")

    fun testFunctionCalls() = doParseTest("FunctionCalls.ftl")

    fun testNestedPlaceables() = doParseTest("NestedPlaceables.ftl")

    private fun doParseTest(fileName: String) {
        val file = myFixture.configureByFile("parser/$fileName")
        assertInstanceOf(file, FluentFileNode::class.java)

        val error = PsiTreeUtil.findChildOfType(file, PsiErrorElement::class.java)
        assertNull("Unexpected parse error: ${error?.errorDescription}", error)
    }
}
