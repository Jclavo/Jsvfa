package br.unb.cic

import br.unb.cic.syntax.NodeSVFA.{SinkNode, SourceNode}
import br.unb.cic.syntax.NodeSVFA
import org.typelevel.paiges.Doc
import scalax.collection.edges.labeled.{WDiEdge, WDiEdgeFactory}
import scalax.collection.edges.DiEdgeImplicits
import scalax.collection.mutable.Graph


class GraphSFVA {

  val graph = Graph.empty[NodeSVFA, WDiEdge[NodeSVFA]] // CASE 4

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

    // add prefix and sufix
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
        if isPathValid(sourceNode, sinkNode) then
          amountOfLeaks = amountOfLeaks + 1
      })
    })
    amountOfLeaks
  }

//  private def findPaths(source: NodeSVFA, target: NodeSVFA, visited: List[NodeSVFA],
//                        currentPath: graph.PathBuilder, paths: List[graph.Path]): Set[graph.Path] = {
//
//    var res: Set[graph.Path] = Set()
//
//    val adjacencyList = graph.get(source).diSuccessors
//
//    if (adjacencyList.contains(graph.get(target))) {
//      currentPath += graph.get(target)
//      return Set(currentPath.result)
//    } else {
//      val newVisited = source :: visited
//      adjacencyList.foreach(next => {
//        if (newVisited.filter(p => p == next).size < 2) {
//          val nextPath = currentPath
//          nextPath += graph.get(next)
//          return findPaths(next, target, newVisited, nextPath, paths)
//        }
//      })
//    }
//    res
//  }

//  def path(from: Node, to: Node, cfg: Graph, visited: List[Node], limit: Int): Set[Path] = {
//    var res: Set[Path] = if (from == to) Set(List(from)) else Set()
//
//    val newVisited = from :: visited
//
//    for((n, t) <- cfg if (n == from) && (newVisited.filter(p => p == t).size < limit)) {
//      res = res ++ path(t, to, cfg, newVisited, limit).map(path => from :: path)
//    }
//    res
//  }

//  def findPaths(source: NodeSVFA, sink: NodeSVFA, visited: List[NodeSVFA], limit: Int): Set[graph.PathBuilder] = {
//
//    var res: Set[graph.PathBuilder] = if (source == sink) Set(graph.newPathBuilder(graph.get(source))) else Set()
//
//    val newVisited = source :: visited // add element in the list
//
//    // add elements from another sequence : val s4 = s3 ++ List(6, 7) => Set(5, 1, 6, 2, 7, 3, 4)
//    // add one element: set += 4 => Set(1, 2, 3, 4)
//    graph.get(source).diSuccessors.map(ds => ds.outer).foreach(successor => {
//      if (newVisited.filter(p => p == successor).size < limit) {
//        res = res ++ findPaths(successor, sink, newVisited, limit).map(path => path += graph.get(source))
//      }
//    })
//    res
//  }

  def findPaths(source: NodeSVFA, sink: NodeSVFA, visited: List[NodeSVFA], limit: Int): Set[graph.PathBuilder] = {

    var res: Set[graph.PathBuilder] = Set()

    if (source == sink) {
      res = Set(graph.newPathBuilder(graph.get(source)))
    }
    else {
      val newVisited = source :: visited // add element in the list

      // add elements from another sequence : val s4 = s3 ++ List(6, 7) => Set(5, 1, 6, 2, 7, 3, 4)
      // add one element: set += 4 => Set(1, 2, 3, 4)
      graph.get(source).diSuccessors.map(ds => ds.outer).foreach(successor => {
        if (newVisited.filter(p => p == successor).size < limit) {
          res = res ++ findPaths(successor, sink, newVisited, limit).map(path => path += graph.get(source))
        }
      })
    }
    res
  }

  def findPaths1(source: NodeSVFA, sink: NodeSVFA): Unit = {
    findPaths1(source, sink, graph.newPathBuilder(graph.get(source)))
  }

  def findPaths1(source: NodeSVFA, sink: NodeSVFA, currentPath: graph.PathBuilder): Unit = {

//      println(currentPath.result().edges.size)
      val diSuccessors = graph.get(source).diSuccessors.map(ds => ds.outer)

      if (source == sink || diSuccessors.size == 0) {
        println(currentPath.result().edges.size)
        println(currentPath.result())
        return
      }


      diSuccessors.foreach(successor => {
        val x = currentPath
        x += graph.get(successor)
        findPaths1(successor, sink, x)
      })

  }

  private def isPathValid(sourceNode: NodeSVFA, sinkNode: NodeSVFA): Boolean = {
//    val p = graph.get(sourceNode).pathTo(graph.get(sinkNode))
//    if (p.isDefined  && isPathValid(sourceNode, sinkNode, p.get)) {
//      return true
//    }
//
//    val path = graph.get(sourceNode).pathTo(graph.get(sinkNode)).get
    val pathBuilder = graph.newPathBuilder(graph.get(sourceNode))
    val paths = findPaths(sourceNode, sinkNode, List(), 1)
//    println(s"paths: ${paths.size}")
    paths.filter(path => isPathValid(sourceNode, sinkNode, path.result())).size > 0
  }

  private def isPathValid(sourceNode: NodeSVFA, sinkNode: NodeSVFA, path: graph.Path): Boolean = {
    var csList: List[GraphSFVA.this.graph.EdgeT] = List()
    var isValidPath: Boolean = true
    println(s"path: ${path}")
    println("--------------")
//    if  (path.edges.size <=1) {
//      return false
//    }
    path.edges.foreach(edge => {
      if (edge.weight == -1 || edge.weight == 1) {
        if (csList.isEmpty) {
          csList = csList :+ edge
        }
        else {
          val lastElement = csList.reverse.head
          if (lastElement.weight == edge.weight) {

//            if (lastElement.outer.target.getStmt() == edge.target.getStmt()) {
//              isValidPath = false
//            } else {
              csList = csList :+ edge
//            }
          }
          else {
            if (lastElement.outer.source.getStmtLine() == edge.outer.source.getStmtLine()) {
              csList = csList.reverse.tail.reverse
            } else {
              csList = csList :+ edge
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
      if (lastElement.outer.source.getMethodName() == sinkNode.getMethodName()) {
        return false
      }
      true
    }
  }
}

