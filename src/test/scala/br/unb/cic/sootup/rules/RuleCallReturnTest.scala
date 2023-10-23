package br.unb.cic.sootup.rules

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleCallReturnTest extends AnyFunSuite:

  test("simple_call_and_return") {
    
    val jsvfa = JSVFA()
    jsvfa.run(
      "simpleCallAndReturn",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 9)
  }

  test("complex_call_and_return") {

    val jsvfa = JSVFA()
    jsvfa.run(
      "complexCallAndReturn",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

    assert(jsvfa.graphSFVA.edgesTotal() === 22)
  }