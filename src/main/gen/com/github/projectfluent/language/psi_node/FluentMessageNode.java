// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi_node;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.projectfluent.language.psi.FluentTypes.*;
import com.github.projectfluent.language.mixin.MixinMessage;
import com.github.projectfluent.language.psi.*;

public class FluentMessageNode extends MixinMessage implements FluentMessage {

  public FluentMessageNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitMessage(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<FluentAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentAttribute.class);
  }

  @Override
  @NotNull
  public FluentMessageID getMessageID() {
    return findNotNullChildByClass(FluentMessageID.class);
  }

  @Override
  @Nullable
  public FluentPattern getPattern() {
    return findChildByClass(FluentPattern.class);
  }

}
