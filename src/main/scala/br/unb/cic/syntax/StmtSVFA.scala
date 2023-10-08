package br.unb.cic.syntax

import br.unb.cic.syntax.StmtSVFA
import sootup.core.jimple.common.stmt.{JAssignStmt, JInvokeStmt, Stmt}

enum StmtSVFA(stmt: Stmt):
  case AssignmentStmt(stmt: JAssignStmt[?,?]) extends StmtSVFA(stmt)
  case InvokeStmt(stmt: JInvokeStmt) extends StmtSVFA(stmt)
  case InvalidStmt(stmt: Stmt) extends StmtSVFA(stmt)


object StmtSVFA:
  def convert(stmt: Stmt): StmtSVFA = stmt.getClass.getSimpleName match
    case "JAssignStmt" => AssignmentStmt(stmt.asInstanceOf[JAssignStmt[?,?]])
    case "JInvokeStmt" => InvokeStmt(stmt.asInstanceOf[JInvokeStmt])
    case _ => InvalidStmt(stmt)