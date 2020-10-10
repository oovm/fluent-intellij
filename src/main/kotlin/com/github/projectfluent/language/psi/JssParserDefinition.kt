package com.github.voml.jss_intellij.language.psi

import com.github.voml.jss_intellij.JssLanguage
import com.github.voml.jss_intellij.ide.file_view.JssFile
import com.github.voml.jss_intellij.language.parser.JssParser
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


class JssParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer = JssLexerAdapter()

    override fun createParser(project: Project): PsiParser = JssParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = STRING_LITERALS

    override fun createElement(node: ASTNode): PsiElement = JssTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = JssFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }

    companion object {
        val COMMENTS = TokenSet.create(JssTypes.COMMENT, JssTypes.COMMENT_BLOCK)
        val STRING_LITERALS = TokenSet.create(JssTypes.STRING_INLINE, JssTypes.STRING_MULTI)
        val FILE = IFileElementType(JssLanguage.INSTANCE)
    }
}
