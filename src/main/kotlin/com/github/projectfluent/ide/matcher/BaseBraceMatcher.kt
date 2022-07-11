package com.github.projectfluent.ide.matcher

import com.github.projectfluent.language.psi.FluentParserDefinition
import com.github.projectfluent.language.psi.FluentTypes.*
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

class BaseBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, next: IElementType?): Boolean {
        return next in InsertPairBraceBefore
    }

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

    companion object {
        private val PAIRS = arrayOf(
            BracePair(BRACE_L, BRACE_R, true),
//            BracePair(AwslTypes.BRACKET_L, AwslTypes.BRACKET_R, true),
//            BracePair(AwslTypes.PARENTHESIS_L, AwslTypes.PARENTHESIS_R, true),
            // BracePair(VomlTypes.EXT_PREFIX, VomlTypes.BRACKETR, false)
        )

        private val InsertPairBraceBefore = TokenSet.orSet(
            FluentParserDefinition.commentTokens,
            TokenSet.create(
                TokenType.WHITE_SPACE,
                COMMA,
//                AwslTypes.PARENTHESIS_R,
//                AwslTypes.BRACKET_R,
//                AwslTypes.BRACE_R,
            )
        )
    }
}
