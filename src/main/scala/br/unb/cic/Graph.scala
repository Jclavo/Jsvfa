package br.unb.cic

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

