package com.github.projectfluent.ide

import com.intellij.psi.formatter.FormatterTestCase

class FluentFormatterTest : FormatterTestCase() {
    override fun getBasePath(): String {
        return "formatter"
    }

    override fun getFileExtension(): String {
        return "ftl"
    }

    fun testFormatter() {
        doTest()
    }
}