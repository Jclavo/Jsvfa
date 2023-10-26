package br.unb.cic.sootup.JSVFA

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class Basics extends AnyFunSuite:

  test("simple_source_and_sink") {

    val jsvfa = JSVFA()

    jsvfa.run(
      "simpleLeak",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic",
      "br.unb.cic.sootup.resources.JSVFA.cases.basic"
    )

    println(jsvfa.graphSFVA.exportToDot())
//    assert(jsvfa.graphSFVA.edgesTotal() === 20)
  }