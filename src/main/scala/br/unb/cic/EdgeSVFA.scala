package br.unb.cic

enum EdgeSVFA(to: NodeSVFA, from: NodeSVFA):
  def getNodeTo(): NodeSVFA = to
  def getNodeFrom(): NodeSVFA = from

  case SimpleEdge(to: NodeSVFA, from: NodeSVFA) extends EdgeSVFA(to, from)
  case CallEdge(to: NodeSVFA, from: NodeSVFA) extends EdgeSVFA(to, from)
  case AfterCallEdge(to: NodeSVFA, from: NodeSVFA) extends EdgeSVFA(to, from)