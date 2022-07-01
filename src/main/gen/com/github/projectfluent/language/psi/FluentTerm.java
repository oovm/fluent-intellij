// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FluentTerm extends PsiElement {

  @NotNull
  List<FluentAttribute> getAttributeList();

  @NotNull
  FluentPattern getPattern();

  @NotNull
  FluentTermID getTermID();

}
