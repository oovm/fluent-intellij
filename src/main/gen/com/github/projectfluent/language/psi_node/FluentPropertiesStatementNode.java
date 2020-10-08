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

public class FluentPropertiesStatementNode extends ASTWrapperPsiElement implements FluentPropertiesStatement {

  public FluentPropertiesStatementNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitPropertiesStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FluentPropertiesBlock getPropertiesBlock() {
    return findChildByClass(FluentPropertiesBlock.class);
  }

  @Override
  @NotNull
  public FluentPropertiesKey getPropertiesKey() {
    return findNotNullChildByClass(FluentPropertiesKey.class);
  }

  @Override
  @Nullable
  public FluentTypeHint getTypeHint() {
    return findChildByClass(FluentTypeHint.class);
  }

}
