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
  @Nullable
  public FluentFunctionReference getFunctionReference() {
    return findChildByClass(FluentFunctionReference.class);
  }

  @Override
  @Nullable
  public FluentInlinePlaceable getInlinePlaceable() {
    return findChildByClass(FluentInlinePlaceable.class);
  }

  @Override
  @Nullable
  public FluentMessageReference getMessageReference() {
    return findChildByClass(FluentMessageReference.class);
  }

  @Override
  @Nullable
  public FluentNumberLiteral getNumberLiteral() {
    return findChildByClass(FluentNumberLiteral.class);
  }

  @Override
  @Nullable
  public FluentStringLiteral getStringLiteral() {
    return findChildByClass(FluentStringLiteral.class);
  }

  @Override
  @Nullable
  public FluentTermReference getTermReference() {
    return findChildByClass(FluentTermReference.class);
  }

  @Override
  @Nullable
  public FluentVariableID getVariableID() {
    return findChildByClass(FluentVariableID.class);
  }

  @Override
  @NotNull
  public List<FluentVariant> getVariantList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentVariant.class);
  }

}
