package br.unb.cic.syntax

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.SootMethod

enum NodeSVFA(method: SootMethod, stmt: Stmt):

  def getStmt(): Stmt = stmt

  def getStmtLine(): Int = stmt.getPositionInfo().getStmtPosition().getFirstLine()

  def getMethodName(): String = method.getName

  def show(): String = s"${method.getDeclaringClassType.toString}\n<${getMethodName()}>\n${stmt.toString.replaceAll("\"", "'")}\nline:${getStmtLine()}"


  case SimpleNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SourceNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SinkNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
