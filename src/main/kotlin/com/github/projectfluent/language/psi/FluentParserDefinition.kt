package com.github.projectfluent.language.psi


import com.github.projectfluent.FluentLanguage
import com.github.projectfluent.ide.view.FluentFile
import com.github.projectfluent.language.parser.FluentParser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet


class FluentParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer = FluentLexerAdapter()

    override fun createParser(project: Project): PsiParser = FluentParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = STRING_LITERALS

    override fun createElement(node: ASTNode): PsiElement = FluentTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = FluentFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }

    companion object {
        val COMMENTS = TokenSet.create()
        val STRING_LITERALS = TokenSet.create()
        val FILE = IFileElementType(FluentLanguage.INSTANCE)
    }
}
