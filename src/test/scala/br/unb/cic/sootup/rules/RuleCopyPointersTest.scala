package br.unb.cic.sootup.rules

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyPointersTest extends AnyFunSuite:

  test("simple_pointers_assignment") {
    val jsvfa = JSVFA()
    jsvfa.run(
      "SimplePointerAssignment",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/pointers",
      "br.unb.cic.sootup.resources.JSVFA.rules.pointers"
    )
//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 2)
  }

  test("complex_pointers_assignment") {
    val jsvfa = JSVFA()
    jsvfa.run(
      "ComplexPointerAssignment",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/pointers",
      "br.unb.cic.sootup.resources.JSVFA.rules.pointers"
  )
  assert(jsvfa.graphSFVA.edgesTotal() === 5)
}
