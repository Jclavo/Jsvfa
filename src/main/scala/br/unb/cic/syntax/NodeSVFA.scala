package br.unb.cic.syntax

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.SootMethod

enum NodeSVFA(method: SootMethod, stmt: Stmt):
  def getStmt(): Stmt = stmt

  def getStmtLine(): Int = stmt.getPositionInfo().getStmtPosition().getFirstLine()
  
  def show(): String = s"${method.getDeclaringClassType.toString}\n<${method.getName}>\n${stmt.toString.replaceAll("\"", "'")}\nline:${stmt.getPositionInfo().getStmtPosition().getFirstLine()}"

  case SimpleNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SourceNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SinkNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
