package br.unb.cic.sootup.graph

import br.unb.cic.sootup.JSVFATest
import br.unb.cic.syntax.{getSinkStatements, getSourceStatements}
import org.scalatest.funsuite.AnyFunSuite

class ToDot extends AnyFunSuite:

  test("get_dot_from_source_and_sink") {

    val expectedDot = "digraph CFG { \n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i1 = 2\n  line:10\" -> \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\"\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\" [style=filled, fillcolor=white]\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i2 = $i4\n  line:11\" [style=filled, fillcolor=white]\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  virtualinvoke r0.<br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak: void sink(int)>($i2)\n  line:13\" [style=filled, fillcolor=red]\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i3 = virtualinvoke r0.<br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak: int source()>()\n  line:9\" -> \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i0 = $i3\n  line:9\"\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i0 = $i3\n  line:9\" -> \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\"\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\" -> \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i2 = $i4\n  line:11\"\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i3 = virtualinvoke r0.<br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak: int source()>()\n  line:9\" [style=filled, fillcolor=blue]\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i1 = 2\n  line:10\" [style=filled, fillcolor=white]\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i0 = $i3\n  line:9\" [style=filled, fillcolor=white]\n  \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i2 = $i4\n  line:11\" -> \"br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak\n  <main>\n  virtualinvoke r0.<br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak: void sink(int)>($i2)\n  line:13\"\n}"

    val jsvfa = JSVFATest(
      "br.unb.cic.sootup.resources.JSVFA.cases.basic.simpleLeak",
      "main",
      "void",
      "src/test/scala/br/unb/cic/sootup/resources/JSVFA/cases/basic"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.exportToDot() === expectedDot)
//    println(jsvfa.graphSFVA.getNodes().size)
  }