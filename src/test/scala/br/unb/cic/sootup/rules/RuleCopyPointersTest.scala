package br.unb.cic.sootup.rules

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyPointersTest extends AnyFunSuite:

  ignore("simple_pointers_assignment") {
    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.rules.pointers.SimplePointerAssignment",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/pointers"
    )
    jsvfa.run()
//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 2)
  }

  ignore("complex_pointers_assignment") {
    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.rules.pointers.ComplexPointerAssignment",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/pointers"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 5)
}
