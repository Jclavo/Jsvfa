package br.unb.cic

import org.eclipse.jdt.internal.codeassist.complete.CompletionOnMethodReturnType
import sootup.core.Project
import sootup.core.inputlocation.AnalysisInputLocation
import sootup.core.model.SootMethod
import sootup.core.signatures.MethodSignature
import sootup.core.views.View
import sootup.java.core.{JavaProject, JavaSootClass}
import sootup.java.core.language.JavaLanguage
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation

import java.util.Collections

abstract class SVFA {

    var project: Project[?, ?] = null
    var view: View[?] = null
  
    def getClassName: String

    def getMainMethodName: String

    def getMainMethodReturnType: String

    def getPathTest: String

    def getJavaVersionForAnalysis: Int

    def run(): Unit

    def setUpSoot(): Unit = {
        createProject
        createView
    }

    def getPathAnalysisInputLocation(): AnalysisInputLocation[JavaSootClass]
    
    private def createProject: Unit = {
        val inputLocation = getPathAnalysisInputLocation()

        // Specify the language of the JavaProject.
        val language = new JavaLanguage(getJavaVersionForAnalysis)

        // Create a new JavaProject based on the input location
        project = JavaProject.builder(language).addInputLocation(inputLocation).build()
    }

    private def createView: Unit = {
        view = project.createView()
    }

    def getEntryPoint: SootMethod = {
        // Create a signature for the class we want to analyze
        val classType = project.getIdentifierFactory().getClassType(s"$getClassName")

        // Create a signature for the method we want to analyze
        val methodSignature = project.getIdentifierFactory.getMethodSignature(
            classType,
            getMainMethodName,
            getMainMethodReturnType,
            Collections.singletonList("java.lang.String[]") // TO-DO: I need to check if it needs to
        )

        getMethodByName(methodSignature)
    }

    def getMethodByName(methodSignature: MethodSignature): SootMethod = {
        view.getMethod(methodSignature).get()
    }
}
