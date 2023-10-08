package br.unb.cic.sootup.rules;

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleCallTest extends AnyFunSuite:
  test("variable_simple_assignment") {
    val jsvfa = JSVFA()
    jsvfa.run(
      "simpleCallWithoutParameters",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules/call",
      "br.unb.cic.sootup.resources.JSVFA.rules.call"
    )

//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.edgesTotal() === 4)
  }

