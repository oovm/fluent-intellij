// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FluentArgument extends PsiElement {

  @Nullable
  FluentFunctionReference getFunctionReference();

  @Nullable
  FluentInlinePlaceable getInlinePlaceable();

  @Nullable
  FluentMessageReference getMessageReference();

  @Nullable
  FluentNamedArgument getNamedArgument();

  @Nullable
  FluentNumberLiteral getNumberLiteral();

  @Nullable
  FluentStringLiteral getStringLiteral();

  @Nullable
  FluentTermReference getTermReference();

  @Nullable
  FluentVariableID getVariableID();

}
