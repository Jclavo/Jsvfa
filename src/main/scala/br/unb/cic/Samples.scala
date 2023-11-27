package br.unb.cic

import sootup.callgraph.ClassHierarchyAnalysisAlgorithm
import sootup.core.jimple.common.stmt.{JAssignStmt, Stmt}
import sootup.core.model.{SootClass, SootMethod}
import sootup.java.core.JavaProject
import sootup.java.core.language.JavaLanguage
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation

import java.nio.file.Paths
import java.util.Collections

//val view = null;
@main
def main(): Unit = {

//  showMethodMain("Calculator")

  showMethodsFromClass("HelloWorld")

//  showGraphByClassHierarchyAnalysis("Calculator")
}


def showMethodMain(className: String, sourcePath: String = null): Unit = {

  val inputLocation = new JavaSourcePathAnalysisInputLocation("src/test/java/samples")

  // Specify the language of the JavaProject.
  val language = new JavaLanguage(8)

  // Create a new JavaProject based on the input location
  val project = JavaProject.builder(language).addInputLocation(inputLocation).build()

  // Create a signature for the class we want to analyze
  val classType = project.getIdentifierFactory().getClassType(s"samples.$className")

  // Create a signature for the method we want to analyze// Create a signature for the method we want to analyze
  val methodSignature = project.getIdentifierFactory.getMethodSignature(classType, "main", "void", Collections.singletonList("java.lang.String[]"))

  // Create a view for project, which allows us to retrieve classes
  val view = project.createView()

  // Retrieve class
  val sootClass = view.getClass(classType).get()

  // Retrieve method
  val sootMethod = sootClass.getMethod(methodSignature.getSubSignature()).get()

  traverse(sootMethod)
}

//SootClass[Any]
def showMethodsFromClass(className: String, sourcePath: String = null): Any = {

  val inputLocation = new JavaSourcePathAnalysisInputLocation("src/test/java/samples")

  // Specify the language of the JavaProject.
  val language = new JavaLanguage(8)

  // Create a new JavaProject based on the input location
  val project = JavaProject.builder(language).addInputLocation(inputLocation).build()

  // Create a signature for the class we want to analyze
  val classType = project.getIdentifierFactory().getClassType(s"samples.$className")

  // Create a view for project, which allows us to retrieve classes
  val view = project.createView()

  // Retrieve class
  val sootClass = view.getClass(classType).get()

  sootClass.getMethods().forEach(method => {
    println(s"analysing method: ${method.getName}")
    traverse(method)
  })
}

def traverse(method: SootMethod): Unit = {
    method.getBody.getStmts().forEach(stmt => {
//      println(StmtSVFA(stmt).getType())
//      println(stmt.getClass.getSimpleName)
//      println(stmt)
    })
}

// CHA
def showGraphByClassHierarchyAnalysis(className: String, sourcePath: String = null): Any = {

  val inputLocation = new JavaSourcePathAnalysisInputLocation("src/test/java/samples")

  // Specify the language of the JavaProject.
  val language = new JavaLanguage(8)

  // Create a new JavaProject based on the input location
  val project = JavaProject.builder(language)
                .addInputLocation(inputLocation)
                .build()

  // Create a signature for the class we want to analyze
  val classType = project.getIdentifierFactory().getClassType(s"samples.$className")

  // Create a signature for the method we want to analyze// Create a signature for the method we want to analyze
  val methodSignature = project.getIdentifierFactory.getMethodSignature(classType, "main", "void", Collections.singletonList("java.lang.String[]"))

  // Create a view for project, which allows us to retrieve classes
  val view = project.createView()

  // Retrieve class
  val sootClass = view.getClass(classType).get()

  // Retrieve method
  val sootMethod = sootClass.getMethod(methodSignature.getSubSignature()).get()

  // Create CHA
  val cha = new ClassHierarchyAnalysisAlgorithm(view)

  // Create CG by initializing CHA with entry method(s)
  val cg = cha.initialize(Collections.singletonList(methodSignature))

  //  println(cg.exportAsDot())
//  cg.callsFrom(methodSignature).forEach(callee => {
//      println(callee)
//  });
}

