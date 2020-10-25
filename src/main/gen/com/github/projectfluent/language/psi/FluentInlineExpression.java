// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FluentInlineExpression extends PsiElement {

  @Nullable
  FluentFunctionReference getFunctionReference();

  @Nullable
  FluentMessageReference getMessageReference();

  @Nullable
  FluentNumberLiteral getNumberLiteral();

  @Nullable
  FluentStringLiteral getStringLiteral();

  @Nullable
  FluentTermReference getTermReference();

  @Nullable
  FluentVariableReference getVariableReference();

  @Nullable
  FluentInlinePlaceable getInlinePlaceable();

}
