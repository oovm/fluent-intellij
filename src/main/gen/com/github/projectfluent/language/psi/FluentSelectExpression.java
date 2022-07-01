// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FluentSelectExpression extends PsiElement {

  @NotNull
  List<FluentDefaultVariant> getDefaultVariantList();

  @Nullable
  FluentFunctionReference getFunctionReference();

  @Nullable
  FluentInlinePlaceable getInlinePlaceable();

  @Nullable
  FluentMessageReference getMessageReference();

  @Nullable
  FluentNumberLiteral getNumberLiteral();

  @Nullable
  FluentStringLiteral getStringLiteral();

  @Nullable
  FluentTermReference getTermReference();

  @Nullable
  FluentVariableID getVariableID();

  @NotNull
  List<FluentVariant> getVariantList();

}
