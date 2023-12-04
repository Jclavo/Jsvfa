package br.unb.cic.sootup.helpers

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class StmtTypeTest extends AnyFunSuite:

  test("method_has_source_stmt") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.SimpleLeak",
      "main",
      "void"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getSourceNodes.size === 1)
  }

  test("method_has_sink_stmt") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.SimpleLeak",
      "main",
      "void"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getSinkNodes.size === 1)
  }

  test("method_has_not_source_or_sink") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.SimpleVariableAssignment",
      "main",
      "void"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getSourceNodes.size === 0)
    assert(jsvfa.graphSFVA.getSinkNodes.size === 0)
  }
