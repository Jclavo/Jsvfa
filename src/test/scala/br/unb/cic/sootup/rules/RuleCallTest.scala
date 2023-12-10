package br.unb.cic.sootup.rules;

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleCallTest extends AnyFunSuite:

  test("simple_call_without_parameters") {
    
    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.simpleCallWithoutParameters",
      "main",
      "void"
    )
    jsvfa.run()

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 3)
  }

  test("complex_call_without_parameters") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.complexCallWithoutParameters",
      "main",
      "void"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 8)
  }

  test("simple_call_with_parameter") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.simpleCallWithParameters",
      "main",
      "void"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 7)
  }

  test("complex_call_with_parameter") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.complexCallWithParameters",
      "main",
      "void"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 18)
  }