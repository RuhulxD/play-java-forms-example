name := """play-java-forms-example"""

version := "2.6.x"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += guice

// https://mvnrepository.com/artifact/org.yamj/api-common
libraryDependencies += "org.yamj" % "api-common" % "2.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"