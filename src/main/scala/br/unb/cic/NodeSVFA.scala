package br.unb.cic

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.Method

enum NodeSVFA(method: Method, stmt: Stmt):
  def getStmt(): Stmt = stmt
  
  case SimpleNode(method: Method, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SourceNode(method: Method, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SinkNode(method: Method, stmt: Stmt) extends NodeSVFA(method, stmt)