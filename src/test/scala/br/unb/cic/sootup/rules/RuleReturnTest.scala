package br.unb.cic.sootup.rules

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleReturnTest extends AnyFunSuite:

  test("simple_return") {
    
    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.simpleReturn",
      "main",
      "void",
      "src/test/java/samples/JSVFA/rules/call"
    )
    jsvfa.run()

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 7)
  }

  test("complex_return") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.complexReturn",
      "main",
      "void",
      "src/test/java/samples/JSVFA/rules/call"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 9)
  }

  test("simple_return_string") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.call.simpleReturnString",
      "main",
      "void",
      "src/test/java/samples/JSVFA/rules/call"
    )
    jsvfa.run()

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 3)
  }