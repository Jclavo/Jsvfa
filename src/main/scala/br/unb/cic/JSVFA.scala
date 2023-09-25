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

import java.nio.file.Paths
import java.util.Collections

import br.unb.cic.StmtType.*
import br.unb.cic.EdgeSVFA.*



class JSVFA {

  var g: StmtGraph[?] = null
  var graphSFVA = new GraphSFVA()

  def run(className: String, pathTestFile: String, pathPackage: String): Unit = {

    /**
     * OBS: CREATE METHOD "getMainClass"
     */

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
//      println(s"analysing method: ${method.getName}")
//      println(method.getBody())
      traverse(method)
    })
  }

  def traverse(method: SootMethod): Unit = {

    val body = method.getBody
    g = body.getStmtGraph()

    body.getStmts().forEach(stmt => {
      analyzer(stmt, method)
    })
  }

  private def analyzer(stmt: Stmt, method: SootMethod): Unit = {

    val stmtSVFA = StmtSVFA(stmt)

    stmtSVFA.getType() match
      case AssignmentStmt => AnalyzerAssignments(stmt.asInstanceOf[JAssignStmt[?,?]], method)
      case _ =>
  }

  private def AnalyzerAssignments(stmt: JAssignStmt[?,?], method: SootMethod): Unit = {
    val leftOp = stmt.getLeftOp
    val rightOp = stmt.getRightOp

    (leftOp, rightOp) match
      case (p: Local, q: Local) => ruleCopy(p, q, stmt, method)
      case (_, _) =>
  }

  private def ruleCopy(p: Local, q: Local, stmt: JAssignStmt[?,?], method: SootMethod): Unit = {
    q.getDefsForLocalUse(g, stmt).forEach(d => {
        val to = NodeSVFA.SimpleNode(method, d)
        val from = NodeSVFA.SimpleNode(method, stmt.asInstanceOf[Stmt])
        graphSFVA.add(SimpleEdge(to, from))
    })
  }
}



