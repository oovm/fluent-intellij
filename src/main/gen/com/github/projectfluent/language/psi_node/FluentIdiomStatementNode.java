// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi_node;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.projectfluent.language.psi.FluentTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.projectfluent.language.psi.*;

public class FluentIdiomStatementNode extends ASTWrapperPsiElement implements FluentIdiomStatement {

  public FluentIdiomStatementNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitIdiomStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public FluentIdiomMark getIdiomMark() {
    return findNotNullChildByClass(FluentIdiomMark.class);
  }

  @Override
  @NotNull
  public FluentIdiomSymbol getIdiomSymbol() {
    return findNotNullChildByClass(FluentIdiomSymbol.class);
  }

  @Override
  @NotNull
  public FluentValue getValue() {
    return findNotNullChildByClass(FluentValue.class);
  }

}
