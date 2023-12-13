package br.unb.cic.sootup.JSVFA

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class CallGraphTest extends AnyFunSuite:

  test("call_graph_1") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.callGraph.CallGraph1",
      "main",
      "void"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("call_graph_2") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.callGraph.CallGraph2",
      "main",
      "void"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }
