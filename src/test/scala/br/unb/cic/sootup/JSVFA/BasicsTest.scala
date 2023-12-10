package br.unb.cic.sootup.JSVFA

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class BasicsTest extends AnyFunSuite:

  test("simple_source_and_sink") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.SimpleLeak",
      "main",
      "void",
    )

    jsvfa.run()

    println(jsvfa.graphSFVA.exportToDot())
//    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
//    assert(jsvfa.graphSFVA.getNodes.size === 1)
  }

  test("complex_source_and_sink") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.ComplexLeak",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 3)
  }

  test("function_call_1") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall1",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("function_call_2") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall2",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("function_call_3") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall3",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("function_call_4") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall4",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("function_call_5") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall5",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("function_call_6") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall6",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 0)
  }

  test("function_call_7") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall7",
      "main",
      "void"
    )

    jsvfa.run()
//    println(jsvfa.graphSFVA.exportToDot())
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 1)
  }

  test("function_call_8") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall8",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 2)
  }

  test("function_call_9") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall9",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 0)
  }

  test("function_call_10") {

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.FunctionCall10",
      "main",
      "void"
    )

    jsvfa.run()
    assert(jsvfa.graphSFVA.getAmountOfLeaks === 0)
  }