// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FluentValue extends PsiElement {

  @Nullable
  FluentArray getArray();

  @Nullable
  FluentBoolean getBoolean();

  @Nullable
  FluentNull getNull();

  @Nullable
  FluentObject getObject();

  @Nullable
  FluentStringInline getStringInline();

  @Nullable
  FluentStringMulti getStringMulti();

  @Nullable
  FluentUrlMaybeValid getUrlMaybeValid();

  @Nullable
  PsiElement getByte();

  @Nullable
  PsiElement getDecimal();

  @Nullable
  PsiElement getInteger();

  @Nullable
  PsiElement getSign();

}
