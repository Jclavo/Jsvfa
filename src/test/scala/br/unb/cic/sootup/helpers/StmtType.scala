package br.unb.cic.sootup.helpers

import br.unb.cic.JSVFA
import br.unb.cic.syntax.{getSinkStatements, getSourceStatements}
import org.scalatest.funsuite.AnyFunSuite

class StmtType extends AnyFunSuite:

  test("method_has_source_stmt") {

    val jsvfa = JSVFA()

    val body = jsvfa.getMainMethod(
      "simpleLeak",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic",
      "br.unb.cic.sootup.resources.JSVFA.cases.basic"
    )

    assert(getSourceStatements(body).size === 1)
  }

  test("method_has_sink_stmt") {

    val jsvfa = JSVFA()

    val body = jsvfa.getMainMethod(
      "simpleLeak",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic",
      "br.unb.cic.sootup.resources.JSVFA.cases.basic"
    )

    assert(getSinkStatements(body).size === 1)
  }

  test("method_has_not_source_or_sink") {

    val jsvfa = JSVFA()

    val body = jsvfa.getMainMethod(
      "SimpleVariableAssignment",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules",
      "br.unb.cic.sootup.resources.JSVFA.rules"
    )

    assert(getSourceStatements(body).size === 0)
    assert(getSinkStatements(body).size === 0)
  }
