package br.unb.cic


import sootup.java.core.JavaProject
import sootup.java.core.language.JavaLanguage
import sootup.java.core.views.JavaView
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm
import sootup.core.graph.StmtGraph
import sootup.core.jimple.basic.Local
import sootup.core.jimple.common.expr.{AbstractInstanceInvokeExpr, AbstractInvokeExpr, Expr}
import sootup.core.jimple.common.stmt.{JAssignStmt, JIdentityStmt, JInvokeStmt, JReturnStmt, Stmt}
import sootup.core.model.{SootClass, SootMethod}
import sootup.core.signatures.MethodSignature
import sootup.core.model.Body
import br.unb.cic.syntax.StmtSVFA.*
import br.unb.cic.syntax.EdgeSVFA.*
import br.unb.cic.syntax.{NodeSVFA, StmtSVFA, isSourceOrSinkStatement}
import br.unb.cic.GraphSFVA

import java.util.Collections

class JSVFA {

  var graphSFVA = new GraphSFVA()

  private var view: JavaView = null

  private var methodsVisited: Set[String] = Set()

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

//    println(methodName + "\n" + method.getBody())

    body.getStmts().forEach(stmt => {
      analyzer(stmt, method, stmtGraph)
    })
  }

  private def analyzer(stmt: Stmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {

      if (isSourceOrSinkStatement(stmt)) {
        return
      }

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
      case (_: Local, r: AbstractInvokeExpr) =>  AnalyzerInvokes(r, assignmentStmt.stmt, method, stmtGraph)
      case (_: Local, r: Local) => ruleCopy(r, assignmentStmt, method, stmtGraph) // a = b
      case (_: Local, _) => ruleCopyExpression(assignmentStmt, method, stmtGraph) // a = b + c
      case (_, _) =>
  }

  private def AnalyzerInvokes(invokeStmt: InvokeStmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
    AnalyzerInvokes(invokeStmt.stmt.getInvokeExpr, invokeStmt.stmt, method, stmtGraph)
  }

  private def AnalyzerInvokes(invokeExp: AbstractInvokeExpr, invokeStmt: Stmt, method: SootMethod, stmtGraph: StmtGraph[?]): Unit = {
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
    defsToParameters(invokeStmt, method, calleeMethod)

    // 3. create edge to "return"
    defsToReturn(invokeStmt, method, calleeMethod)

    traverse(calleeMethod)
  }

  /**
   * This method create two edges for:
   *
   * CASE: 1
   * This create an edge for each return statement:
   *  - from where the return value is defined
   *  - to the return stmt
   *
   * Case               |     Rule
   *  - x = .....       | - x@s' -> x@s
   *    ...
   *    return x
   *
   *
   * CASE: 2
   * This create an edge:
   *  - from the return statement
   *  - to where the method was called
   *
   * Case                     |     Rule
   *  - a = myFunction(...)   | - callee:x@s -> caller:a@s'
   *    ...
   *
   *    myFunction(...) {
   *    ...
   *    return x
   *    }
   */
  private def defsToReturn(invokeStmt: Stmt, callerMethod: SootMethod, calleeMethod: SootMethod): Unit = {

    getReturnStatements(calleeMethod.getBody).foreach(r => {

      val rValue = r.asInstanceOf[JReturnStmt].getOp

      // Case: 1
      if (rValue.isInstanceOf[Local]) {
        val opLocal = rValue.asInstanceOf[Local]

        opLocal.getDefsForLocalUse(calleeMethod.getBody.getStmtGraph, r).forEach(d => {
          val from = NodeSVFA.SimpleNode(calleeMethod, d)
          val to = NodeSVFA.SimpleNode(calleeMethod, r)
//          graphSFVA.addEdge(SimpleEdge(from, to))
          graphSFVA.addEdge(from, to)
        })
      }

      /**
       * Case: 2
       *
       * this edge is created just when the STMT is an Assignment
       * but I am not sure if creating it when it is an Invoke
       */
      if (invokeStmt.isInstanceOf[JAssignStmt[?, ?]]) {
        val from = NodeSVFA.SimpleNode(calleeMethod, r)
        val to = NodeSVFA.SimpleNode(callerMethod, invokeStmt)
//        graphSFVA.add(SimpleEdge(from, to))
        graphSFVA.addEdge(from, to)
      }
    })
  }

  /**
   * This create an edge for each parameter:
   *  - from caller object definition
   *  - to callee object definition
   *
   * Case                       |     Rule
   *  - this.myFunction(a, b)   | - caller:a@s' -> callee:a@s
   *                            | - caller:b@s' -> callee:b@s
   */
  private def defsToParameters(invokeStmt: Stmt, callerMethod: SootMethod, calleeMethod: SootMethod): Unit = {
    val callerStmt = invokeStmt
    val callerStmtGraph = callerMethod.getBody.getStmtGraph

    (0 until (callerStmt.getInvokeExpr.getArgCount)).foreach(index => {
      val parameterLocal = callerStmt.getInvokeExpr.getArg(index).asInstanceOf[Local]
      val parameterDeclarationStmt = getIdentityStatement(calleeMethod.getBody, calleeMethod.getBody.getParameterLocal(index))

      if (! parameterDeclarationStmt.isDefined) {
        return
      }

      parameterLocal.getDefsForLocalUse(callerStmtGraph, callerStmt).forEach(d => {
        val from = NodeSVFA.SimpleNode(callerMethod, d)
        val to = NodeSVFA.SimpleNode(calleeMethod, parameterDeclarationStmt.get)
//        graphSFVA.add(SimpleEdge(from, to))
        graphSFVA.addEdge(from, to)
      })
    })
  }

  /**
   * This create an edge:
   *  - from caller object definition
   *  - to callee "this" declaration
   *
   *  Case                        |     Rule
   *  - this.myFunction(...)      | - caller:this@s' -> callee:this@s
   *  - p.myFunction(...)         | - caller:myObject@s' -> callee:this@s // not sure about it
   */
  private def defsToThis(invokeStmt: Stmt, callerMethod: SootMethod, calleeMethod: SootMethod): Unit = {
    val callerStmt = invokeStmt
    val invokeLocal = callerStmt.getInvokeExpr.asInstanceOf[AbstractInstanceInvokeExpr].getBase // not sure if it will be a local when "objects" are use
    val callerStmtGraph = callerMethod.getBody.getStmtGraph

    invokeLocal.getDefsForLocalUse(callerStmtGraph, callerStmt).forEach(d => {
      val from = NodeSVFA.SimpleNode(callerMethod, d)
      val to = NodeSVFA.SimpleNode(calleeMethod, calleeMethod.getBody.getThisStmt)
//      graphSFVA.add(SimpleEdge(from, to))
      graphSFVA.addEdge(from, to)
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
//        graphSFVA.add(SimpleEdge(from, to))
      graphSFVA.addEdge(from, to)
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

  /**
   * Helper functions
   */

  /**
   * return an specific identity statement
   * if it is found in a method body.
   *
   * Case
   * - $r1 := @parameter0: java.lang.String[];
   */
  private def getIdentityStatement(body: Body, leftLocal: Local): Option[Stmt] = {
    getIdentityStatements(body)
      .find(_.asInstanceOf[JIdentityStmt[?]].getLeftOp == leftLocal)
      .orElse(None)
  }

  /**
   *  return a Set of identity statements from a method body
   *
   * Case
   * - $r1 := @parameter0: java.lang.String[];
   */
  private def getIdentityStatements(body: Body): Set[Stmt] = {
    var identityStmt: Set[Stmt] = Set()
    body.getStmts.forEach(s => {
      s.getClass.getSimpleName match
        case "JIdentityStmt" => identityStmt += s
        case _ =>
    })
    identityStmt
  }

  private def getReturnStatements(body: Body): Set[Stmt] = {
    var returnStmts: Set[Stmt] = Set()
    body.getStmts.forEach(s => {
      s.getClass.getSimpleName match
        case "JReturnStmt" => returnStmts += s
        case _ =>
    })
    returnStmts
  }


  def getMainMethod(className: String, pathTestFile: String, pathPackage: String): Body = {

    val inputLocation = new JavaSourcePathAnalysisInputLocation(pathTestFile)

    // Specify the language of the JavaProject.
    val language = new JavaLanguage(8)

    // Create a new JavaProject based on the input location
    val project = JavaProject.builder(language).addInputLocation(inputLocation).build()

    // Create a signature for the class we want to analyze
    val classType = project.getIdentifierFactory().getClassType(s"$pathPackage.$className")

    // Create a signature for the method we want to analyze// Create a signature for the method we want to analyze
    val methodSignature = project.getIdentifierFactory.getMethodSignature(classType, "main", "void", Collections.singletonList("java.lang.String[]"))

    // Create a view for project, which allows us to retrieve classes
    val view = project.createView()

    // Retrieve class
    val sootClass = view.getClass(classType).get()

    // Retrieve method
    val sootMethod = sootClass.getMethod(methodSignature.getSubSignature()).get()

    sootMethod.getBody
  }
}



