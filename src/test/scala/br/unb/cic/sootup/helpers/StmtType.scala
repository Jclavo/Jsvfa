package br.unb.cic.sootup.helpers

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class StmtType extends AnyFunSuite:

  test("method_has_source_stmt") {

    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic"
    )

    jsvfa.run()
    val body = jsvfa.getEntryPoint.getBody

    assert(jsvfa.getSourceStatements(body).size === 1)
  }

  test("method_has_sink_stmt") {

    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic"
    )

    jsvfa.run()
    val body = jsvfa.getEntryPoint.getBody

    assert(jsvfa.getSinkStatements(body).size === 1)
  }

  test("method_has_not_source_or_sink") {

    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.rules.SimpleVariableAssignment",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/rules"
    )

    jsvfa.run()
    val body = jsvfa.getEntryPoint.getBody

    assert(jsvfa.getSourceStatements(body).size === 0)
    assert(jsvfa.getSinkStatements(body).size === 0)
  }
