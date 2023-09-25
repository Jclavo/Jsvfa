package br.unb.cic

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.Method
import sootup.core.jimple.common.stmt.JAssignStmt

enum StmtSVFA(stmt: Stmt):
  case AssignmentStmt(stmt: JAssignStmt[?,?]) extends StmtSVFA(stmt)
  case InvokeStmt(stmt: Stmt) extends StmtSVFA(stmt)
  case InvalidStmt(stmt: Stmt) extends StmtSVFA(stmt)


object StmtSVFA:
  def convert(stmt: Stmt): StmtSVFA = stmt.getClass.getSimpleName match
    case "JAssignStmt" => AssignmentStmt(stmt.asInstanceOf[JAssignStmt[?,?]])
    case _ => InvalidStmt(stmt)

enum NodeSVFA(method: Method, stmt: Stmt):
  def getStmt(): Stmt = stmt
  case SimpleNode(method: Method, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SourceNode(method: Method, stmt: Stmt) extends NodeSVFA(method, stmt)
  case SinkNode(method: Method, stmt: Stmt) extends NodeSVFA(method, stmt)


enum EdgeSVFA(to: NodeSVFA, from: NodeSVFA):

  def getNodeTo(): NodeSVFA = to
  def getNodeFrom(): NodeSVFA = from

  case SimpleEdge(to: NodeSVFA, from: NodeSVFA) extends EdgeSVFA(to, from)
  case CallEdge(to: NodeSVFA, from: NodeSVFA) extends EdgeSVFA(to, from)
  case AfterCallEdge(to: NodeSVFA, from: NodeSVFA) extends EdgeSVFA(to, from)

class GraphSFVA {
  private var graph: Set[EdgeSVFA] = Set()

  def add(edge: EdgeSVFA): Unit = graph += edge

  def convertToDot(): String = "in progress"

  def show(): Unit = graph.foreach(edge => {
    val to = edge.getNodeTo().getStmt()
    val from = edge.getNodeFrom().getStmt()
    println(s"$to -> $from")
  })

  def edgesTotal(): Int = graph.size

}

