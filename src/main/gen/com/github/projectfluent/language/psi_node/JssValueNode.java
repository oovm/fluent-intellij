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

public class JssValueNode extends ASTWrapperPsiElement implements JssValue {

  public JssValueNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JssVisitor visitor) {
    visitor.visitValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JssVisitor) accept((JssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JssArray getArray() {
    return findChildByClass(JssArray.class);
  }

  @Override
  @Nullable
  public JssBoolean getBoolean() {
    return findChildByClass(JssBoolean.class);
  }

  @Override
  @Nullable
  public JssNull getNull() {
    return findChildByClass(JssNull.class);
  }

  @Override
  @Nullable
  public JssObject getObject() {
    return findChildByClass(JssObject.class);
  }

  @Override
  @Nullable
  public JssStringInline getStringInline() {
    return findChildByClass(JssStringInline.class);
  }

  @Override
  @Nullable
  public JssStringMulti getStringMulti() {
    return findChildByClass(JssStringMulti.class);
  }

  @Override
  @Nullable
  public JssUrlMaybeValid getUrlMaybeValid() {
    return findChildByClass(JssUrlMaybeValid.class);
  }

  @Override
  @Nullable
  public PsiElement getByte() {
    return findChildByType(BYTE);
  }

  @Override
  @Nullable
  public PsiElement getDecimal() {
    return findChildByType(DECIMAL);
  }

  @Override
  @Nullable
  public PsiElement getInteger() {
    return findChildByType(INTEGER);
  }

  @Override
  @Nullable
  public PsiElement getSign() {
    return findChildByType(SIGN);
  }

}
