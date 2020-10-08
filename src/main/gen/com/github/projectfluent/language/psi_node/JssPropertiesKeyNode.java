// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi_node;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.projectfluent.language.psi.JssTypes.*;
import com.github.projectfluent.language.mixin.JssPropertiesKeyMixin;
import com.github.projectfluent.language.psi.*;

public class JssPropertiesKeyNode extends JssPropertiesKeyMixin implements JssPropertiesKey {

  public JssPropertiesKeyNode(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JssVisitor visitor) {
    visitor.visitPropertiesKey(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JssVisitor) accept((JssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

  @Override
  @Nullable
  public PsiElement getSymbol() {
    return findChildByType(SYMBOL);
  }

}
