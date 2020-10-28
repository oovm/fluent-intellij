// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi_node;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.projectfluent.language.psi.FluentTypes.*;
import com.github.projectfluent.language.psi.FluentElement;
import com.github.projectfluent.language.psi.*;

public class FluentSelectExpressionNode extends FluentElement implements FluentSelectExpression {

  public FluentSelectExpressionNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitSelectExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<FluentDefaultVariant> getDefaultVariantList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentDefaultVariant.class);
  }

  @Override
  @NotNull
  public FluentInlineExpression getInlineExpression() {
    return findNotNullChildByClass(FluentInlineExpression.class);
  }

  @Override
  @NotNull
  public List<FluentVariant> getVariantList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentVariant.class);
  }

}
