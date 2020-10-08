// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi_node;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.projectfluent.language.psi.JssTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.projectfluent.language.psi.*;

public class JssIdiomStatementNode extends ASTWrapperPsiElement implements JssIdiomStatement {

  public JssIdiomStatementNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JssVisitor visitor) {
    visitor.visitIdiomStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JssVisitor) accept((JssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JssIdiomMark getIdiomMark() {
    return findNotNullChildByClass(JssIdiomMark.class);
  }

  @Override
  @NotNull
  public JssIdiomSymbol getIdiomSymbol() {
    return findNotNullChildByClass(JssIdiomSymbol.class);
  }

  @Override
  @NotNull
  public JssValue getValue() {
    return findNotNullChildByClass(JssValue.class);
  }

}
