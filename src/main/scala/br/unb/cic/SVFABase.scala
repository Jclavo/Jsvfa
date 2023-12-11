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
//import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation

abstract class SVFABase {
    
    def getClassName(): String

    def getMainMethodName(): String

    def getMainMethodReturnType(): String

    def rootFilePath: String

    def getFilePath(): String

    def getJavaVersion(): Int

    def run(): Unit

    def getPathAnalysisInputLocation(): AnalysisInputLocation[JavaSootClass]
    
    def createProject(): Project[?, ?] = {
        val inputLocation = this.getPathAnalysisInputLocation()

        // Specify the language of the JavaProject.
        val language = new JavaLanguage(getJavaVersion())

        // Create a new JavaProject based on the input location
        JavaProject.builder(language)
          .addInputLocation(inputLocation)
//          .addInputLocation(
//              new JavaClassPathAnalysisInputLocation(
//                  System.getProperty("java.home") + "/lib/rt.jar"))
          .build()
    }

    def createView(project: Project[?, ?]): View[?] = project.createView()

    def getEntryPoint(project: Project[?, ?], view: View[?]): SootMethod = {

        // Create a signature for the class we want to analyze
        val classType = project.getIdentifierFactory().getClassType(getClassName())

        // Create a signature for the method we want to analyze
        val methodSignature = project.getIdentifierFactory.getMethodSignature(
            classType,
            getMainMethodName(),
            getMainMethodReturnType(),
            Collections.singletonList("java.lang.String[]") // TO-DO: I need to check if it needs to // Collections.emptyList()
        )

        getMethodByName(view, methodSignature)
    }

    private def getMethodByName(view: View[?], methodSignature: MethodSignature): SootMethod = view.getMethod(methodSignature).get()
}
