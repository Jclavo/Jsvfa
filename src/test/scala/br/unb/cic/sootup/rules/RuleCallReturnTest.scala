package br.unb.cic.sootup.rules

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleCallReturnTest extends AnyFunSuite:

  test("simple_call_and_return") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.simpleCallAndReturn",
      "main",
      "void",
      "src/test/java/samples/JSVFA/rules/call"
    )
    jsvfa.run()

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 11)
  }

  test("complex_call_and_return") {
    
    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.complexCallAndReturn",
      "main",
      "void",
      "src/test/java/samples/JSVFA/rules/call"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 28)
  }