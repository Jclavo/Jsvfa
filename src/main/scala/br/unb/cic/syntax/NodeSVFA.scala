package br.unb.cic.syntax

import br.unb.cic.syntax.StmtSVFA.{isSinkStmt, isSourceStmt}
import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.SootMethod

enum NodeSVFA(method: SootMethod, stmt: Stmt):
  def getStmt(): Stmt = stmt
  
  def show(): String = s"${method.getDeclaringClassType.toString}\n<${method.getName}>\n${stmt.toString.replaceAll("\"", "'")}\nline:${stmt.getPositionInfo().getStmtPosition().getFirstLine()}"

  case SimpleNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SourceNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SinkNode(method: SootMethod, stmt: Stmt) extends NodeSVFA(method, stmt)

object NodeSVFA:
  def createNode(method: SootMethod, stmt: Stmt): NodeSVFA = isSourceStmt(stmt) match
    case true => NodeSVFA.SourceNode(method, stmt)
    case _ => isSinkStmt(stmt) match
      case true => NodeSVFA.SinkNode(method, stmt)
      case _ => NodeSVFA.SimpleNode(method, stmt)