package br.unb.cic

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.Method
import sootup.core.jimple.common.stmt.JAssignStmt

enum StmtType:
  case AssignmentStmt
  case InvokeStmt
  case InvalidStmt


case class StmtSVFA(stmt: Stmt) {
  def getType(): StmtType = stmt.getClass.getSimpleName match
    case "JAssignStmt" => StmtType.AssignmentStmt
    case _ => StmtType.InvalidStmt
}


enum NodeType:
  case SimpleNode
  case SourceNode
  case SinkNode

trait NodeSVFA(method: Method, stmt: StmtSVFA, label: NodeType) {
  def getType: NodeType = label
}

enum EdgeType:
  case SimpleEdge
  case CallEdge
  case AfterCallEdge

trait EdgeSVFA(to: NodeSVFA, from: NodeSVFA, label: EdgeType)


trait GraphSFVA {
  private var graph: Set[EdgeSVFA] = Set()

  def add(edge: EdgeSVFA): Unit = graph += edge

  def convertToDot(): String = "in progress"
}

