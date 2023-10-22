package br.unb.cic.sootup.rules

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleReturnTest extends AnyFunSuite:

  test("simple_return") {
    
    val jsvfa = JSVFA()
    jsvfa.run(
      "simpleReturn",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 7)
  }

  test("complex_return") {

    val jsvfa = JSVFA()
    jsvfa.run(
      "complexReturn",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

    assert(jsvfa.graphSFVA.edgesTotal() === 9)
  }