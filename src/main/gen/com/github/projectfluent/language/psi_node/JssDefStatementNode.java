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

public class JssDefStatementNode extends ASTWrapperPsiElement implements JssDefStatement {

  public JssDefStatementNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JssVisitor visitor) {
    visitor.visitDefStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JssVisitor) accept((JssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JssPropertiesBlock getPropertiesBlock() {
    return findChildByClass(JssPropertiesBlock.class);
  }

  @Override
  @NotNull
  public JssPropertiesKey getPropertiesKey() {
    return findNotNullChildByClass(JssPropertiesKey.class);
  }

  @Override
  @Nullable
  public JssTypeHint getTypeHint() {
    return findChildByClass(JssTypeHint.class);
  }

}
