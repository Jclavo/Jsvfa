package br.unb.cic

import br.unb.cic.syntax.NodeSVFA.{SimpleNode, SinkNode, SourceNode}
import br.unb.cic.syntax.NodeSVFA
import org.typelevel.paiges.Doc
import scalax.collection.edges.{DiEdgeImplicits}
import scalax.collection.mutable.{Graph, TypedGraphFactory}
import scalax.collection.edges.labeled.LDiEdge

//case class MyEdge[+N](source: N, target: N, label: String) extends LDiEdge[N, String]  // CASE 1

class GraphSFVA {

//  val graph = Graph.empty[NodeSVFA, MyEdge[NodeSVFA]] // CASE 1
  val graph = Graph.empty[NodeSVFA, LDiEdge[NodeSVFA, String]] // CASE 2
//  val graph = Graph.empty[NodeSVFA, LDiEdge[NodeSVFA, String]] // CASE 3


  def addNode(node: NodeSVFA): Unit = graph.add(node)

//  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph.addOne(MyEdge(source, target, "aaa")) // CASE 1

//  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph.add(source ~>target :+ "aa") // CASE 2
//  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph += (source ~> target :+ "aaa") // CASE 2

//    def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph.add((source ~+> target)("Loves")) // CASE 3

  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = {} //unknow case

  def getNodes: Set[NodeSVFA] = graph.nodes.map(n => n.outer).toSet

  def exportToDot(): String = {
    val edges = graph.edges.map { edge =>
      Doc.text("\"") +
      Doc.text(edge._1.show()) +
      Doc.text("\"") +
      Doc.space +
      Doc.text("->") +
      Doc.space +
      Doc.text("\"") +
      Doc.text(edge._2.show()) +
      Doc.text("\"")
    }

    val colorNodes = graph.nodes.map { node =>
      Doc.text("\"") +
      Doc.text(node.outer.show()) +
      Doc.text("\"") +
      Doc.space + Doc.text(s"[style=filled, fillcolor=${getColorForNode(node.outer)}]")
    }

    val body = Doc.intercalate(Doc.text("\n"), edges union colorNodes)

    // add prefix and sufix
    val prefix = Doc.text("digraph CFG { ")
    val suffix = Doc.text("}")
    val res = body.tightBracketBy(prefix, suffix)
    res.render(20)
  }

  private def getColorForNode(node: NodeSVFA): String = node match
    case SourceNode(_, _) => "blue"
    case SinkNode(_, _) => "red"
    case _ => "white"

  def show(): Unit = {
      graph.edges.foreach(edge => {
          val to = edge._1.getStmt()
          val from = edge._2.getStmt()
          println(s"$to -> $from")
      })
  }

  def edgesTotal(): Int = graph.size

  def isSourceNode(node: NodeSVFA): Boolean = node match
    case SourceNode(_, _) => true
    case _ => false

  def isSinkNode(node: NodeSVFA): Boolean = node match
    case SinkNode(_, _) => true
    case _ => false

  def getSourceNodes: Set[NodeSVFA] = graph.nodes.filter(n => isSourceNode(n)).map(n => n.outer).toSet

  def getSinkNodes: Set[NodeSVFA] = graph.nodes.filter(n => isSinkNode(n)).map(n => n.outer).toSet

  def getAmountOfLeaks: Int = {
    var amountOfLeaks: Int = 0
    getSourceNodes.foreach(sourceNode => {
      getSinkNodes.foreach(sinkNode => {
        if graph.get(sourceNode).pathTo(graph.get(sinkNode)).isDefined then
          amountOfLeaks = amountOfLeaks + 1
      })
    })
    amountOfLeaks
  }
}

