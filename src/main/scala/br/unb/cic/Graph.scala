package br.unb.cic

import br.unb.cic.syntax.{EdgeSVFA, NodeSVFA}
import org.typelevel.paiges.Doc
import scalax.collection.edges.{DiEdgeImplicits, DiEdge}
import scalax.collection.mutable.{Graph}

class GraphSFVA {

  val graph = scalax.collection.mutable.Graph.empty[NodeSVFA, DiEdge[NodeSVFA]]

  def addNode(node: NodeSVFA): Unit = graph.add(node)

  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph.add((source ~> target))

  def exportToDot(): String = {
//    val edges = graph.map { edge =>
//      Doc.text("\"") +
//      Doc.text(edge.getNodeFrom().show()) +
//      Doc.text("\"") +
//      Doc.space +
//      Doc.text("->") +
//      Doc.space +
//      Doc.text("\"") +
//      Doc.text(edge.getNodeTo().show()) +
//      Doc.text("\"")
//    }

//    var body = Doc.intercalate(Doc.text("\n"), edges)
//
//    // add prefix and sufix
//    val prefix = Doc.text("digraph CFG { ")
//    val suffix = Doc.text("}")
//    val res = body.tightBracketBy(prefix, suffix)
//    res.render(20)
      ""
  }

  def show(): Unit = {
//    graph.foreach(edge => {
//      val to = edge.getNodeTo().getStmt()
//      val from = edge.getNodeFrom().getStmt()
//      println(s"$to -> $from")
//    })
  }

  def edgesTotal(): Int = graph.size
}

