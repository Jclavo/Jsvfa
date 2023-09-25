package br.unb.cic

import org.typelevel.paiges.Doc

class GraphSFVA {

  private var graph: Set[EdgeSVFA] = Set()

  def add(edge: EdgeSVFA): Unit = graph += edge

  def exportToDot(): String = {
    val edges = graph.map { edge =>
      Doc.text("\"") +
      Doc.text(edge.getNodeFrom().show()) +
      Doc.text("\"") +
      Doc.space +
      Doc.text("->") +
      Doc.space +
      Doc.text("\"") +
      Doc.text(edge.getNodeTo().show()) +
      Doc.text("\"")
    }

    var body = Doc.intercalate(Doc.text("\n"), edges)

    // add prefix and sufix
    val prefix = Doc.text("digraph CFG { ")
    val suffix = Doc.text("}")
    val res = body.tightBracketBy(prefix, suffix)
    res.render(20)
  }

  def show(): Unit = graph.foreach(edge => {
    val to = edge.getNodeTo().getStmt()
    val from = edge.getNodeFrom().getStmt()
    println(s"$to -> $from")
  })

  def edgesTotal(): Int = graph.size
}

