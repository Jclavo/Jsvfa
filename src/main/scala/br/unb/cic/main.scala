
import sootup.java.core.JavaProject
import sootup.java.core.language.JavaLanguage
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation

import java.nio.file.Paths
import java.util.Collections;

@main
def main(): Unit = {

  val inputLocation = new JavaSourcePathAnalysisInputLocation("src/test/scala/br/unb/cic/sootup/resources");

  // Specify the language of the JavaProject.
  val language = new JavaLanguage(8);

  // Create a new JavaProject based on the input location
  val project = JavaProject.builder(language).addInputLocation(inputLocation).build();

  // Create a signature for the class we want to analyze
  val classType = project.getIdentifierFactory().getClassType("br.unb.cic.sootup.resources.HelloWorld");

  // Create a signature for the method we want to analyze// Create a signature for the method we want to analyze
  val methodSignature = project.getIdentifierFactory.getMethodSignature(classType, "main", "void", Collections.singletonList("java.lang.String[]"))

  // Create a view for project, which allows us to retrieve classes
  val view = project.createView();

  // Retrieve class
  val sootClass = view.getClass(classType).get();

  // Retrieve method
  val sootMethod = sootClass.getMethod(methodSignature.getSubSignature()).get();

  println(sootMethod.getBody());
}