package br.unb.cic.syntax

import br.unb.cic.syntax.StmtSVFA
import sootup.core.jimple.common.expr.AbstractInvokeExpr
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

  /**
   * these function returns the type of stmt
   */
  private def getMethodNameFromStmt(stmt: Stmt): String = {
    val stmtSVFA = StmtSVFA.convert(stmt)

    stmtSVFA match {
      case InvokeStmt(s) => getMethodNameFromStmt(s)
      case AssignmentStmt(s) => getMethodNameFromStmt(s)
      case _ => ""
    }
  }

  private def getMethodNameFromStmt(invokeStmt: JInvokeStmt): String = {
    invokeStmt.getInvokeExpr.getMethodSignature.getName
  }

  private def getMethodNameFromStmt(assignmentStmt: JAssignStmt[?, ?]): String = {
    assignmentStmt.getRightOp match
      case r: AbstractInvokeExpr => r.getMethodSignature.getName
      case _ => ""
  }

  def isSourceStmt(stmt: Stmt): Boolean = {
    //more method name can be added
    List("source").indexOf(getMethodNameFromStmt(stmt)) match
      case -1 => false
      case _ => true
  }