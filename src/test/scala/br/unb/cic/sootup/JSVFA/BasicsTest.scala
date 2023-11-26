package br.unb.cic.sootup.JSVFA

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class BasicsTest extends AnyFunSuite:

  test("simple_source_and_sink") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.SimpleLeak",
      "main",
      "void",
      "src/test/java/samples/JSVFA/cases/basic"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("complex_source_and_sink") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.ComplexLeak",
      "main",
      "void",
      "src/test/java/samples/JSVFA/cases/basic"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getAmountOfLeaks === 3)
  }