// This is a generated file. Not intended for manual editing.
package com.github.projectfluent.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class FluentVisitor extends PsiElementVisitor {

  public void visitMessage(@NotNull FluentMessage o) {
    visitPsiElement(o);
  }

  public void visitTerm(@NotNull FluentTerm o) {
    visitPsiElement(o);
  }

  public void visitAttribute(@NotNull FluentAttribute o) {
    visitPsiElement(o);
  }

  public void visitIdentifier(@NotNull FluentIdentifier o) {
    visitPsiElement(o);
  }

  public void visitInlineText(@NotNull FluentInlineText o) {
    visitPsiElement(o);
  }

  public void visitPattern(@NotNull FluentPattern o) {
    visitPsiElement(o);
  }

  public void visitString(@NotNull FluentString o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
