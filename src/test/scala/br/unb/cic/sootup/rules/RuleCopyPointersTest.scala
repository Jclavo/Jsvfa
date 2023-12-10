package br.unb.cic.sootup.rules

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyPointersTest extends AnyFunSuite:

  ignore("simple_pointers_assignment") {
    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.pointers.SimplePointerAssignment",
      "main",
      "void"
    )
    jsvfa.run()
//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 2)
  }

  ignore("complex_pointers_assignment") {
    val jsvfa = JSVFATest(
      "samples.JSVFA.rules.pointers.ComplexPointerAssignment",
      "main",
      "void"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.edgesTotal() === 5)
}
