package br.unb.cic

import br.unb.cic.syntax.StmtSVFA.*
import br.unb.cic.syntax.EdgeSVFA.*
import br.unb.cic.syntax.{NodeSVFA, StmtSVFA}
import br.unb.cic.{GraphSFVA}
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm
import sootup.core.graph.StmtGraph
import sootup.core.jimple.basic.Local
import sootup.core.jimple.common.stmt.{JAssignStmt, Stmt}
import sootup.core.model.{SootClass, SootMethod}
import sootup.java.core.JavaProject
import sootup.java.core.language.JavaLanguage
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation

import java.util.Collections

class JSVFA {

  var graphSFVA = new GraphSFVA()

  def run(className: String, pathTestFile: String, pathPackage: String): Unit = {

    val inputLocation = new JavaSourcePathAnalysisInputLocation(pathTestFile)

    // Specify the language of the JavaProject.
    val language = new JavaLanguage(8)

    // Create a new JavaProject based on the input location
    val project = JavaProject.builder(language).addInputLocation(inputLocation).build()

    // Create a signature for the class we want to analyze
    val classType = project.getIdentifierFactory().getClassType(s"$pathPackage.$className")

    // Create a view for project, which allows us to retrieve classes
    val view = project.createView()

    // Retrieve class
    val sootClass = view.getClass(classType).get()

    sootClass.getMethods().forEach(method => {
//      println(method.getBody())
      traverse(method)
    })
  }

  def traverse(method: SootMethod): Unit = {
    val body = method.getBody
    val stmtGraph = body.getStmtGraph()

    body.getStmts().forEach(stmt => {
      analyzer(stmt, method, stmtGraph)
    })
  }

  private def analyzer(stmt: Stmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
      StmtSVFA.convert(stmt) match
        case AssignmentStmt(s) => AnalyzerAssignments(AssignmentStmt(s), method, stmtGraph)
        case _ =>
  }

  private def AnalyzerAssignments(assignmentStmt: AssignmentStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    val leftOp = assignmentStmt.stmt.getLeftOp
    val rightOp = assignmentStmt.stmt.getRightOp

    (leftOp, rightOp) match
      case (l: Local, r: Local) => ruleCopy(r, assignmentStmt, method, stmtGraph)
      case (_, _) =>
  }

  /**
   * This is a copy operation for variables and pointers.
   *
   * Case           |     Rule
   *  - s: a = b    | - b@s' -> a@s
   *  - s: p = q    | - q@s'-> p@s
   */
  private def ruleCopy(rightLocal: Local, assignmentStmt: AssignmentStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    rightLocal.getDefsForLocalUse(stmtGraph, assignmentStmt.stmt).forEach(d => {
        val from = NodeSVFA.SimpleNode(method, d)
        val to = NodeSVFA.SimpleNode(method, assignmentStmt.stmt)
        graphSFVA.add(SimpleEdge(from, to))
    })
  }
}



