package com.github.projectfluent.ide

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.nio.file.Files
import java.nio.file.Path

class FluentFormatterTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData"

    fun testFormatter() {
        val file = myFixture.configureByFile("formatter/unformatted.ftl")

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        val expected = Files.readString(Path.of(testDataPath, "formatter", "formatted.ftl"))
        assertEquals(expected, file.text)
    }
}
