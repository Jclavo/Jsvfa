package br.unb.cic.sootup.rules

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyTest extends AnyFunSuite:

  test("variable_simple_assignment") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.SimpleVariableAssignment",
      "main",
      "void"
    )

    jsvfa.run()
  //    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 3)
  }

   test("variable_complex_assignment") {

     val jsvfa = JSVFATest(
       "samples.JSVFA.rules.SimpleVariableAssignment",
       "main",
       "void"
     )
     jsvfa.run()

     assert(jsvfa.graphSFVA.edgesTotal() === 3)
   }

  test("variable_full_assignment") {
    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.FullVariableAssignment",
      "main",
      "void"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 9)
  }

  test("calculator_simple") {
    val jsvfa = JSVFATest(
      "samples.basic.CalculatorSimple",
      "main",
      "void"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 8)
  }

//  test("NumberSign") {
//    val jsvfa = JSVFA()
//    jsvfa.run("NumberSign", "src/test/java/samples/basic", "samples.basic")
//    println(jsvfa.graphSFVA.exportToDot())
//    assert(jsvfa.graphSFVA.edgesTotal() === 3)
//  }


