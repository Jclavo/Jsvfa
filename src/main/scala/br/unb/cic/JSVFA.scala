package br.unb.cic


import sootup.java.core.JavaProject
import sootup.java.core.language.JavaLanguage
import sootup.java.core.views.JavaView
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm
import sootup.core.graph.StmtGraph
import sootup.core.jimple.basic.Local
import sootup.core.jimple.common.expr.{AbstractInvokeExpr, Expr}
import sootup.core.jimple.common.stmt.{JAssignStmt, Stmt}
import sootup.core.model.{SootClass, SootMethod}
import sootup.core.signatures.MethodSignature
import sootup.core.model.Body
import br.unb.cic.syntax.StmtSVFA.*
import br.unb.cic.syntax.EdgeSVFA.*
import br.unb.cic.syntax.{NodeSVFA, StmtSVFA}
import br.unb.cic.GraphSFVA

import java.util.Collections

class JSVFA {

  var graphSFVA = new GraphSFVA()

  var view: JavaView = null

  var methodsVisited: Set[String] = Set()

  def run(className: String, pathTestFile: String, pathPackage: String): Unit = {

    val inputLocation = new JavaSourcePathAnalysisInputLocation(pathTestFile)

    // Specify the language of the JavaProject.
    val language = new JavaLanguage(8)

    // Create a new JavaProject based on the input location
    val project = JavaProject.builder(language).addInputLocation(inputLocation).build()

    // Create a signature for the class we want to analyze
    val classType = project.getIdentifierFactory().getClassType(s"$pathPackage.$className")

    // Create a view for project, which allows us to retrieve classes
    view = project.createView()

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
    val methodName = method.getName

    if (methodsVisited.contains(methodName)) {
      return
    }

    methodsVisited += methodName

    body.getStmts().forEach(stmt => {
      analyzer(stmt, method, stmtGraph)
    })
  }

  private def analyzer(stmt: Stmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
      val stmtSVFA = StmtSVFA.convert(stmt)

      stmtSVFA match
        case AssignmentStmt(s) => AnalyzerAssignments(AssignmentStmt(s), method, stmtGraph) // a = *
        case InvokeStmt(s) => AnalyzerInvokes(InvokeStmt(s), method, stmtGraph) // *.myFunction(...)
        case _ =>
  }

  private def AnalyzerAssignments(assignmentStmt: AssignmentStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    val leftOp = assignmentStmt.stmt.getLeftOp
    val rightOp = assignmentStmt.stmt.getRightOp

    (leftOp, rightOp) match
      case (_: Local, r: Local) => ruleCopy(r, assignmentStmt, method, stmtGraph) // a = b
      case (_: Local, _) => ruleCopyExpression(assignmentStmt, method, stmtGraph) // a = b + c
      case (_, _) =>
  }

  private def AnalyzerInvokes(invokeStmt: InvokeStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    AnalyzerInvokes(invokeStmt.stmt.getInvokeExpr, invokeStmt, method, stmtGraph)
  }

  private def AnalyzerInvokes(invokeExp: AbstractInvokeExpr, invokeStmt: InvokeStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    //    println(invokeStmt.stmt.getInvokeExpr.getMethodSignature.getDeclClassType)

    if (! view.getMethod(invokeExp.getMethodSignature).isPresent) {
      return
    }

    val calleeMethod = view.getMethod(invokeExp.getMethodSignature).get()
    val calleeBody = calleeMethod.getBody

    /**
     * creating nodes from caller to callee
     */

    // 1. create edge to "This"
    defsToThis(invokeStmt, method, calleeMethod)

    // 2. create edge to "parameters"

    traverse(calleeMethod)
  }

  /**
   * This create an edge:
   *  - from caller object definition
   *  - to callee "this" declaration
   *
   *  Case                        |     Rule
   *  - this.myFunction(...)      | - this@s' -> caller:this@s
   *  - myObject.myFunction(...)  | - myObject@s' -> callee:this@s
   */
  private def defsToThis(invokeStmt: InvokeStmt, callerMethod: SootMethod, calleeMethod: SootMethod): Unit = {
    val callerStmt = invokeStmt.stmt
    val invokeLocal = callerStmt.getInvokeExpr.getUses.get(0).asInstanceOf[Local];
    val callerStmtGraph = callerMethod.getBody.getStmtGraph

    invokeLocal.getDefsForLocalUse(callerStmtGraph, callerStmt).forEach(d => {
      val from = NodeSVFA.SimpleNode(callerMethod, d)
      val to = NodeSVFA.SimpleNode(calleeMethod, calleeMethod.getBody.getThisStmt)
      graphSFVA.add(SimpleEdge(from, to))
    })
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

  /**
   * This is a copy operation for an expression that contents variables and pointers.
   *
   * Case               |     Rule
   *  - s: a = b + c    | - {b@s' -> a@s, c@s' -> a@s}
   *  - s: p = q        | - q@s'-> p@s
   */
  private def ruleCopyExpression(assignmentStmt: AssignmentStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    assignmentStmt.stmt.getUses.forEach(u => {
      if (u.isInstanceOf[Local]) {
        val uLocal = u.asInstanceOf[Local]
        ruleCopy(uLocal, assignmentStmt, method, stmtGraph)
      }
    })
  }


}



