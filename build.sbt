ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
        name := "sfva",
        libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test,
        libraryDependencies += "org.soot-oss" % "sootup.core" % "1.1.2",
        libraryDependencies += "org.soot-oss" % "sootup.java.core" % "1.1.2",
        libraryDependencies += "org.soot-oss" % "sootup.java.sourcecode" % "1.1.2",
    //    libraryDependencies += "org.soot-oss" % "sootup.java.bytecode" % "1.1.2",
        libraryDependencies += "org.soot-oss" % "sootup.jimple.parser" % "1.1.2",
        libraryDependencies += "org.soot-oss" % "sootup.callgraph" % "1.1.2",
        libraryDependencies += "org.soot-oss" % "sootup.analysis" % "1.1.2",

        libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.3.0",
        libraryDependencies += "org.typelevel" %% "paiges-core" % "0.4.2" ,
        libraryDependencies += "org.scala-graph" % "graph-core_2.13" % "2.0.0"
  )