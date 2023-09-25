package br.unb.cic

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.{SootMethod}

enum NodeSVFA(method: SootMethod, stmt: Stmt):
  def getStmt(): Stmt = stmt
  
  def show(): String = s"${method.getDeclaringClassType.toString}\n<${method.getName}>\n${stmt.toString}"


  case SimpleNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SourceNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SinkNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)