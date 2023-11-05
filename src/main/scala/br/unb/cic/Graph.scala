package br.unb.cic

import br.unb.cic.syntax.{EdgeSVFA, NodeSVFA}
import org.typelevel.paiges.Doc
import scalax.collection.edges.{DiEdge, DiEdgeImplicits}
import scalax.collection.mutable.Graph

class GraphSFVA {

  val graph = Graph.empty[NodeSVFA, DiEdge[NodeSVFA]]

  def addNode(node: NodeSVFA): Unit = graph.add(node)

  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph.add((source ~> target))

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

    var body = Doc.intercalate(Doc.text("\n"), edges)

    // add prefix and sufix
    val prefix = Doc.text("digraph CFG { ")
    val suffix = Doc.text("}")
    val res = body.tightBracketBy(prefix, suffix)
    res.render(20)
  }

  def show(): Unit = {
      graph.edges.foreach(edge => {
          val to = edge._1.getStmt()
          val from = edge._2.getStmt()
          println(s"$to -> $from")
      })
  }

  def edgesTotal(): Int = graph.size
}

