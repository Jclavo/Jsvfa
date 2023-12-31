package br.unb.cic.sootup

import br.unb.cic.JSVFA
import sootup.core.inputlocation.AnalysisInputLocation
import sootup.core.jimple.common.expr.AbstractInvokeExpr
import sootup.core.jimple.common.stmt.{JAssignStmt, JInvokeStmt, Stmt}
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation
import sootup.java.core.JavaSootClass

class JSVFATest(className: String,
                mainMethodName: String, 
                mainMethodReturnType: String
               )
                extends JSVFA {

  val sourceList: Set[String] = Set("source")
  val sinkList: Set[String] = Set("sink")

  override def getClassName(): String = className

  override def getMainMethodName(): String = mainMethodName

  override def getMainMethodReturnType(): String = mainMethodReturnType

  override def getFilePath(): String = rootFilePath + getPathFromClassName()

  def rootFilePath: String = "src/test/java/" //target/scala-2.12/test-classes

  private def getPathFromClassName(): String = getClassName().split('.').reverse.tail.reverse.mkString(".").replace('.', '/')

  override def getJavaVersion(): Int = 8

  override def getPathAnalysisInputLocation(): AnalysisInputLocation[JavaSootClass] = new JavaSourcePathAnalysisInputLocation(getFilePath())

  override def isSourceStmt(stmt: Stmt): Boolean = {
    sourceList.find(_ == getMethodNameFromStmt(stmt)) match
      case Some(_) => true
      case _ => false
  }

  override def isSinkStmt(stmt: Stmt): Boolean = {
    sinkList.find(_ == getMethodNameFromStmt(stmt)) match
      case Some(_) => true
      case _ => false
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
