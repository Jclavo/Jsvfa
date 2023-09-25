package br.unb.cic

import sootup.callgraph.ClassHierarchyAnalysisAlgorithm
import sootup.core.graph.StmtGraph
import sootup.java.core.JavaProject
import sootup.java.core.language.JavaLanguage
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation
import sootup.core.model.SootMethod
import sootup.core.model.SootClass
import sootup.core.jimple.common.stmt.Stmt
import sootup.core.jimple.common.stmt.JAssignStmt
import sootup.core.jimple.basic.Local

import java.util.Collections

import br.unb.cic.StmtSVFA.*
import br.unb.cic.EdgeSVFA.*

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
      case (p: Local, q: Local) => ruleCopy(p, q, assignmentStmt, method, stmtGraph)
      case (_, _) =>
  }

  private def ruleCopy(p: Local, q: Local, assignmentStmt: AssignmentStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    q.getDefsForLocalUse(stmtGraph, assignmentStmt.stmt).forEach(d => {
        val to = NodeSVFA.SimpleNode(method, d)
        val from = NodeSVFA.SimpleNode(method, assignmentStmt.stmt)
        graphSFVA.add(SimpleEdge(to, from))
    })
  }
}



