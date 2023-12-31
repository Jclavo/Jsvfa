package br.unb.cic.syntax

enum EdgeSVFA(to: NodeSVFA, from: NodeSVFA):

  def getNodeFrom(): NodeSVFA = from

  def getNodeTo(): NodeSVFA = to


  case SimpleEdge(from: NodeSVFA, to: NodeSVFA) extends EdgeSVFA(to, from)
  case CallEdge(from: NodeSVFA, to: NodeSVFA) extends EdgeSVFA(to, from)
  case AfterCallEdge(from: NodeSVFA, to: NodeSVFA) extends EdgeSVFA(to, from)