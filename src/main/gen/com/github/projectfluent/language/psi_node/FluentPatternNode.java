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

public class FluentPatternNode extends FluentElement implements FluentPattern {

  public FluentPatternNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitPattern(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<FluentBlockText> getBlockTextList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentBlockText.class);
  }

  @Override
  @NotNull
  public List<FluentInlinePlaceable> getInlinePlaceableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentInlinePlaceable.class);
  }

  @Override
  @NotNull
  public List<FluentInlineText> getInlineTextList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FluentInlineText.class);
  }

}
