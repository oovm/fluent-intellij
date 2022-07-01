// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi_node;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.projectfluent.language.psi.FluentTypes.*;
import com.github.projectfluent.language.mixin.MixinAttribute;
import com.github.projectfluent.language.psi.*;

public class FluentAttributeNode extends MixinAttribute implements FluentAttribute {

  public FluentAttributeNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitAttribute(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public FluentAttributeID getAttributeID() {
    return findNotNullChildByClass(FluentAttributeID.class);
  }

  @Override
  @NotNull
  public FluentPattern getPattern() {
    return findNotNullChildByClass(FluentPattern.class);
  }

}
