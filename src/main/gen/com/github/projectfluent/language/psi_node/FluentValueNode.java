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

public class FluentValueNode extends ASTWrapperPsiElement implements FluentValue {

  public FluentValueNode(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FluentVisitor visitor) {
    visitor.visitValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FluentVisitor) accept((FluentVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FluentArray getArray() {
    return findChildByClass(FluentArray.class);
  }

  @Override
  @Nullable
  public FluentBoolean getBoolean() {
    return findChildByClass(FluentBoolean.class);
  }

  @Override
  @Nullable
  public FluentNull getNull() {
    return findChildByClass(FluentNull.class);
  }

  @Override
  @Nullable
  public FluentObject getObject() {
    return findChildByClass(FluentObject.class);
  }

  @Override
  @Nullable
  public FluentStringInline getStringInline() {
    return findChildByClass(FluentStringInline.class);
  }

  @Override
  @Nullable
  public FluentStringMulti getStringMulti() {
    return findChildByClass(FluentStringMulti.class);
  }

  @Override
  @Nullable
  public FluentUrlMaybeValid getUrlMaybeValid() {
    return findChildByClass(FluentUrlMaybeValid.class);
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
