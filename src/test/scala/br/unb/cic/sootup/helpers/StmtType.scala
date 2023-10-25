package br.unb.cic.sootup.helpers

import br.unb.cic.JSVFA
import br.unb.cic.syntax.getSourceStatements
import org.scalatest.funsuite.AnyFunSuite

class StmtType extends AnyFunSuite:

  test("stmt_is_simple") {

    val jsvfa = JSVFA()

    val body = jsvfa.getMainMethod(
      "simpleLeak",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic",
      "br.unb.cic.sootup.resources.JSVFA.cases.basic"
    )

    assert(getSourceStatements(body).size === 1)
  }
