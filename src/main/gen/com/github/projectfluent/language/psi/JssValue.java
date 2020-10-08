// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JssValue extends PsiElement {

  @Nullable
  JssArray getArray();

  @Nullable
  JssBoolean getBoolean();

  @Nullable
  JssNull getNull();

  @Nullable
  JssObject getObject();

  @Nullable
  JssStringInline getStringInline();

  @Nullable
  JssStringMulti getStringMulti();

  @Nullable
  JssUrlMaybeValid getUrlMaybeValid();

  @Nullable
  PsiElement getByte();

  @Nullable
  PsiElement getDecimal();

  @Nullable
  PsiElement getInteger();

  @Nullable
  PsiElement getSign();

}
