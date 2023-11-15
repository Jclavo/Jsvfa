package br.unb.cic.sootup.JSVFA

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class BasicsTest extends AnyFunSuite:

  test("simple_source_and_sink") {

     val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic"
    )

    jsvfa.run()

    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }