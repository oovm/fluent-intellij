// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FluentPattern extends PsiElement {

  @NotNull
  List<FluentBlockText> getBlockTextList();

  @NotNull
  List<FluentInlinePlaceable> getInlinePlaceableList();

  @NotNull
  List<FluentInlineText> getInlineTextList();

}
