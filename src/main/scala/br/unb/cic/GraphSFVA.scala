package br.unb.cic

import br.unb.cic.syntax.NodeSVFA.{SinkNode, SourceNode}
import br.unb.cic.syntax.NodeSVFA
import org.typelevel.paiges.Doc
import scalax.collection.edges.labeled.{WDiEdge, WDiEdgeFactory}
import scalax.collection.edges.DiEdgeImplicits
import scalax.collection.mutable.Graph

//type SomeInts = List[Int]

class GraphSFVA {

  val graph: Graph[NodeSVFA, WDiEdge[NodeSVFA]] = Graph.empty[NodeSVFA, WDiEdge[NodeSVFA]] // CASE 4

  def addNode(node: NodeSVFA): Unit = graph.add(node)

  def addEdge(source: NodeSVFA, target: NodeSVFA): Unit = graph.addOne(source ~> target % 0.0)

  def addEdgeOpenCS(source: NodeSVFA, target: NodeSVFA): Unit = graph.addOne(source ~> target % 1.0)

  def addEdgeCloseCS(source: NodeSVFA, target: NodeSVFA): Unit = graph.addOne(source ~> target % -1.0)

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
      Doc.text("\"") +
      Doc.space +
      Doc.text(getNodeLabel(edge)) +
      Doc.space
    }

    val colorNodes = graph.nodes.map { node =>
      Doc.text("\"") +
      Doc.text(node.outer.show()) +
      Doc.text("\"") +
      Doc.space + Doc.text(s"[style=filled, fillcolor=${getColorForNode(node.outer)}]")
    }

    val body = Doc.intercalate(Doc.text("\n"), edges union colorNodes)

    // add prefix and suffix
    val prefix = Doc.text("digraph CFG { ")
    val suffix = Doc.text("}")
    val res = body.tightBracketBy(prefix, suffix)
    res.render(20)
  }

  def getNodeLabel(edge: WDiEdge[NodeSVFA]): String = edge.weight match
    case -1 => "[ label=\")CS\" ]"
    case 1 => "[ label=\"CS(\" ]"
    case _ => ""


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
        if hasValidPath(sourceNode, sinkNode) then
          amountOfLeaks = amountOfLeaks + 1
      })
    })
    amountOfLeaks
  }

  def findPaths(source: NodeSVFA, sink: NodeSVFA): Set[List[graph.EdgeT]] = {
    findPaths(source, sink, List(source), List()).map(path => convertToGraphPath(path))
  }

  def findPaths(source: NodeSVFA, sink: NodeSVFA, currentPath: List[NodeSVFA], visited: List[NodeSVFA]): Set[List[NodeSVFA]] = {

    val diSuccessors = graph.get(source).diSuccessors.map(ds => ds.outer)
    var finalPath: Set[List[NodeSVFA]] = Set()

    if (source == sink || diSuccessors.isEmpty) {
      finalPath = Set(currentPath.reverse)
    } else {
      val newVisited = source :: visited
      diSuccessors.foreach(successor => {
        if (newVisited.filter(p => p == successor).size < 2) {
          val newCurrentPath = successor :: currentPath
          finalPath = finalPath ++ findPaths(successor, sink, newCurrentPath, newVisited)
        }

      })
    }
    finalPath
  }

  private def convertToGraphPath(path: List[NodeSVFA]): List[graph.EdgeT] = {
    var newPath: List[graph.EdgeT] = List()
    var oldPath = path

    while(oldPath.length > 1) {
      val tempPath = graph.newPathBuilder(graph.get(oldPath.head))

      if (tempPath.add(graph.get(oldPath.tail.head))) {
        newPath = tempPath.result().edges.head :: newPath
      }
      oldPath = oldPath.tail
    }
    newPath.reverse
  }

  def hasValidPath(sourceNode: NodeSVFA, sinkNode: NodeSVFA): Boolean = {
//    val p = graph.get(sourceNode).pathTo(graph.get(sinkNode))
//    if (p.isDefined  && isPathValid(sourceNode, sinkNode, p.get)) {
//      return true
//    }

    val pathBuilder = graph.newPathBuilder(graph.get(sourceNode))
    val paths = findPaths(sourceNode, sinkNode)
    paths.exists(path => isPathValid(sourceNode, sinkNode, path))
  }

  def isPathValid(sourceNode: NodeSVFA, sinkNode: NodeSVFA, path: List[graph.EdgeT]): Boolean = {
    var csList: List[GraphSFVA.this.graph.EdgeT] = List()
    var isValidPath: Boolean = true

    if  (path.size <=1) {
      return false
    }

    if (path.head.outer.source != sourceNode || path.reverse.head.outer.target != sinkNode) {
      return false
    }

    var deletedElement:graph.EdgeT = null
    path.filter(e => e.weight != 0).foreach(edge => {
        if (csList.isEmpty && deletedElement == null) {
          csList = csList :+ edge
        }
        else {
          var lastElement:graph.EdgeT = null
          if (deletedElement != null) {
            lastElement = deletedElement
            deletedElement = null
          } else {
            lastElement = csList.reverse.head
          }

          if (lastElement.weight == edge.weight) {

            if (lastElement.outer.target.getMethodName() == edge.target.getMethodName()) {
              isValidPath = false
            } else {
              csList = csList :+ edge
            }
          }
          else {
            if (csList.isEmpty) {
              csList = csList :+ edge
            } else {
              lastElement = csList.reverse.head
              if (lastElement.outer.target.getStmtLine() == edge.outer.target.getStmtLine()) {
                csList = csList.reverse.tail.reverse
                deletedElement = edge
              } else {
                isValidPath = false
              }
            }
          }
        }
    })

    if (csList.isEmpty || ! isValidPath) {
      return isValidPath
    }
    val lastElement = csList.reverse.head

    if (lastElement.weight == 1) {
      if (lastElement.outer.target.getMethodName() == sinkNode.getMethodName()) {
        return false
      }
      true
    } else {
      if (lastElement.outer.target.getMethodName() == sinkNode.getMethodName()) {
        return true
      }
      false
    }
  }
}

