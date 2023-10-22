package br.unb.cic.sootup.rules;

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleCallTest extends AnyFunSuite:

  test("simple_call_without_parameters") {
    
    val jsvfa = JSVFA()
    jsvfa.run(
      "simpleCallWithoutParameters",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 4)
  }

  test("complex_call_without_parameters") {

    val jsvfa = JSVFA()
    jsvfa.run(
      "complexCallWithoutParameters",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

    assert(jsvfa.graphSFVA.edgesTotal() === 11)
  }

  test("simple_call_with_parameter") {

    val jsvfa = JSVFA()
    jsvfa.run(
      "simpleCallWithParameters",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

    assert(jsvfa.graphSFVA.edgesTotal() === 6)
  }

  test("complex_call_with_parameter") {

    val jsvfa = JSVFA()
    jsvfa.run(
      "complexCallWithParameters",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

    assert(jsvfa.graphSFVA.edgesTotal() === 14)
  }