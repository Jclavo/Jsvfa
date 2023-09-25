package br.unb.cic

import sootup.core.jimple.common.stmt.{JAssignStmt, Stmt}

enum StmtSVFA(stmt: Stmt):
  case AssignmentStmt(stmt: JAssignStmt[?,?]) extends StmtSVFA(stmt)
  case InvokeStmt(stmt: Stmt) extends StmtSVFA(stmt)
  case InvalidStmt(stmt: Stmt) extends StmtSVFA(stmt)


object StmtSVFA:
  def convert(stmt: Stmt): StmtSVFA = stmt.getClass.getSimpleName match
    case "JAssignStmt" => AssignmentStmt(stmt.asInstanceOf[JAssignStmt[?,?]])
    case _ => InvalidStmt(stmt)