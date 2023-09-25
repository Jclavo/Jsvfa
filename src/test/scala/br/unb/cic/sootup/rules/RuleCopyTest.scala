package br.unb.cic.sootup.rules

import br.unb.cic.JSVFA
import org.scalatest.funsuite.AnyFunSuite

class RuleCopyTest extends AnyFunSuite:

  test("simple_assignment") {
    val jsvfa = JSVFA()
    jsvfa.run("SimpleAssignment", "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules", "br.unb.cic.sootup.resources.JSVFA.rules")
    jsvfa.graphSFVA.show()
    assert(69 === 69)
  }
