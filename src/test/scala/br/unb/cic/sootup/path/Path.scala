package br.unb.cic.sootup.path

import org.scalatest.funsuite.AnyFunSuite
import br.unb.cic.sootup.JSVFATest

class Path extends AnyFunSuite:

  test("path1") {

    val jsvfa = JSVFATest(
      "samples.paths.Path1",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)

    assert(paths.size === 1)
    assert(paths.head.size === 9)

    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
}

  test("path2") {

    val jsvfa = JSVFATest(
      "samples.paths.Path2",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)
    assert(paths.size === 3)

    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
  }

  test("path3") {

    val jsvfa = JSVFATest(
      "samples.paths.Path3",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)
    assert(paths.size === 2)

//    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
  }

  test("path4") {

    val jsvfa = JSVFATest(
      "samples.paths.Path4",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)
    assert(paths.size === 2)

    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
  }

  test("path5") {

    val jsvfa = JSVFATest(
      "samples.paths.Path5",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)
    assert(paths.size === 10)

//    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
  }

  test("path6") {

    val jsvfa = JSVFATest(
      "samples.paths.Path6",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)
    assert(paths.size === 6)

    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
  }

  test("path7") {

    val jsvfa = JSVFATest(
      "samples.paths.Path7",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val graphSFVA = jsvfa.graphSFVA
    val sourceNode = graphSFVA.getSourceNodes.head
    val sinkNode = graphSFVA.getSinkNodes.head

    val paths = graphSFVA.findPaths(sourceNode, sinkNode)
    assert(paths.size === 1)

    assert(graphSFVA.hasValidPath(sourceNode, sinkNode) === true)
  }