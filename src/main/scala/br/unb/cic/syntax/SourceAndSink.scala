package br.unb.cic.syntax

import sootup.core.jimple.common.stmt.Stmt

trait SourceAndSink {

  def isSourceOrSinkStatement(stmt: Stmt): Boolean = isSourceStmt(stmt) || isSinkStmt(stmt)

  def isSourceStmt(stmt: Stmt): Boolean

  def isSinkStmt(stmt: Stmt): Boolean
}
