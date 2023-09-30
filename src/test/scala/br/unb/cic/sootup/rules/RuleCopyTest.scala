package br.unb.cic.sootup.rules

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyTest extends AnyFunSuite:

  test("variable_simple_assignment") {
    val jsvfa = JSVFA()
    jsvfa.run("SimpleVariableAssignment", "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules", "br.unb.cic.sootup.resources.JSVFA.rules")
//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 3)
  }

//  test("variable_complex_assignment") {
//    val jsvfa = JSVFA()
//    jsvfa.run("ComplexVariableAssignment", "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules", "br.unb.cic.sootup.resources.JSVFA.rules")
//    //    println(jsvfa.graphSFVA.exportToDot())
//    assert(jsvfa.graphSFVA.edgesTotal() === 2)
//  }


