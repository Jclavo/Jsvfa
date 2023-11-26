package br.unb.cic.sootup.graph

import br.unb.cic.sootup.JSVFATest
import org.scalatest.funsuite.AnyFunSuite

class ToDot extends AnyFunSuite:

  ignore("get_dot_from_source_and_sink") {

    val expectedDot = "digraph CFG { \n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i1 = 2\n  line:10\" -> \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\"\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\" [style=filled, fillcolor=white]\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i2 = $i4\n  line:11\" [style=filled, fillcolor=white]\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  virtualinvoke r0.<samples.JSVFA.cases.basic.simpleLeak: void sink(int)>($i2)\n  line:13\" [style=filled, fillcolor=red]\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i3 = virtualinvoke r0.<samples.JSVFA.cases.basic.simpleLeak: int source()>()\n  line:9\" -> \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i0 = $i3\n  line:9\"\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i0 = $i3\n  line:9\" -> \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\"\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i4 = $i0 + $i1\n  line:11\" -> \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i2 = $i4\n  line:11\"\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i3 = virtualinvoke r0.<samples.JSVFA.cases.basic.simpleLeak: int source()>()\n  line:9\" [style=filled, fillcolor=blue]\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i1 = 2\n  line:10\" [style=filled, fillcolor=white]\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i0 = $i3\n  line:9\" [style=filled, fillcolor=white]\n  \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  $i2 = $i4\n  line:11\" -> \"samples.JSVFA.cases.basic.simpleLeak\n  <main>\n  virtualinvoke r0.<samples.JSVFA.cases.basic.simpleLeak: void sink(int)>($i2)\n  line:13\"\n}"

    val jsvfa = JSVFATest(
      "samples.JSVFA.cases.basic.simpleLeak",
      "main",
      "void",
      "src/test/java/samples/JSVFA/cases/basic"
    )
    jsvfa.run()

    assert(jsvfa.graphSFVA.exportToDot() === expectedDot)
//    println(jsvfa.graphSFVA.getNodes().size)
  }