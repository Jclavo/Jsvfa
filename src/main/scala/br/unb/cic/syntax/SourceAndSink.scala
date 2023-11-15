package br.unb.cic.syntax

import br.unb.cic.syntax.StmtSVFA
import sootup.core.jimple.common.expr.AbstractInvokeExpr
import sootup.core.jimple.common.stmt.{JAssignStmt, JInvokeStmt, Stmt}
import sootup.core.model.Body

import scala.collection.mutable

trait SourceAndSink {

  private def getSourceAndSinkStatements(body: Body): mutable.HashMap[String, Set[Stmt]] = {

    val sourceStmt: mutable.HashMap[String, Set[Stmt]] = mutable.HashMap()

    sourceStmt("source") = Set()
    sourceStmt("sink") = Set()

    body.getStmts().forEach(stmt => {
      isSourceStmt(stmt) match
        case true => sourceStmt("source") += stmt
        case _ => isSinkStmt(stmt) match
          case true => sourceStmt("sink") += stmt
          case _ =>
    })
    sourceStmt
  }

  def getSourceStatements(body: Body): Set[Stmt] = {
    getSourceAndSinkStatements(body)("source")
  }

  def getSinkStatements(body: Body): Set[Stmt] = {
    getSourceAndSinkStatements(body)("sink")
  }

  def isSourceOrSinkStatement(stmt: Stmt): Boolean = {
    isSourceStmt(stmt) match
      case true => true
      case _ => isSinkStmt(stmt)
  }

  def isSourceStmt(stmt: Stmt): Boolean = {
    //more method name can be added here
    List("source").indexOf(getMethodNameFromStmt(stmt)) match
      case -1 => false
      case _ => true
//    stmt.isInstanceOf
  }

  def isSinkStmt(stmt: Stmt): Boolean = {
    //more method name can be added here
    List("sink").indexOf(getMethodNameFromStmt(stmt)) match
      case -1 => false
      case _ => true
  }

  /**
   * These functions returns the method's name
   * if a statement is calling one
   */
  private def getMethodNameFromStmt(stmt: Stmt): String = {
    stmt match
      case invokeStmt: JInvokeStmt => getMethodNameFromStmt(invokeStmt)
      case assignStmt: JAssignStmt[?, ?] => getMethodNameFromStmt(assignStmt)
      case _ => ""
  }

  private def getMethodNameFromStmt(invokeStmt: JInvokeStmt): String =
    invokeStmt.getInvokeExpr.getMethodSignature.getName

  private def getMethodNameFromStmt(assignmentStmt: JAssignStmt[?, ?]): String = {
    assignmentStmt.getRightOp match
      case invokeExp: AbstractInvokeExpr => invokeExp.getMethodSignature.getName
      case _ => ""
  }
}
