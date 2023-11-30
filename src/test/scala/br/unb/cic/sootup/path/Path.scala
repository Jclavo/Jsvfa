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

    val sourceNode = jsvfa.graphSFVA.getSourceNodes.head
    val sinkNode = jsvfa.graphSFVA.getSinkNodes.head

    val paths = jsvfa.graphSFVA.findPaths1(sourceNode, sinkNode)

    assert(paths.head.size === 5)
}

  test("path2") {

    val jsvfa = JSVFATest(
      "samples.paths.Path2",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val sourceNode = jsvfa.graphSFVA.getSourceNodes.head
    val sinkNode = jsvfa.graphSFVA.getSinkNodes.head

    val paths = jsvfa.graphSFVA.findPaths1(sourceNode, sinkNode)
    assert(paths.size === 3)
  }

  test("path3") {

    val jsvfa = JSVFATest(
      "samples.paths.Path3",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val sourceNode = jsvfa.graphSFVA.getSourceNodes.head
    val sinkNode = jsvfa.graphSFVA.getSinkNodes.head

    val paths = jsvfa.graphSFVA.findPaths1(sourceNode, sinkNode)
    assert(paths.size === 2)
  }

  test("path4") {

    val jsvfa = JSVFATest(
      "samples.paths.Path4",
      "main",
      "void",
      "src/test/java/samples/paths"
    )

    jsvfa.run()

    val sourceNode = jsvfa.graphSFVA.getSourceNodes.head
    val sinkNode = jsvfa.graphSFVA.getSinkNodes.head

    val paths = jsvfa.graphSFVA.findPaths1(sourceNode, sinkNode)
    assert(paths.size === 2)
  }