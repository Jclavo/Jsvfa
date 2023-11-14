package br.unb.cic.sootup.rules

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyTest extends AnyFunSuite:

  test("variable_simple_assignment") {

    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.rules.SimpleVariableAssignment",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules"
    )

    jsvfa.run()
  //    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 3)
  }

   test("variable_complex_assignment") {

     val jsvfa = JSVFATest(
       "br.unb.cic.sootup.resources.JSVFA.rules.SimpleVariableAssignment",
       "main",
       "void",
       "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules"
     )
     jsvfa.run()

     assert(jsvfa.graphSFVA.edgesTotal() === 3)
   }

  test("variable_full_assignment") {
    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.rules.FullVariableAssignment",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 9)
  }

  test("calculator_simple") {
    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.basic.CalculatorSimple",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/basic"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 8)
  }

//  test("NumberSign") {
//    val jsvfa = JSVFA()
//    jsvfa.run("NumberSign", "src/test/scala/br/unb/cic/sootup/resources/basic", "br.unb.cic.sootup.resources.basic")
//    println(jsvfa.graphSFVA.exportToDot())
//    assert(jsvfa.graphSFVA.edgesTotal() === 3)
//  }


