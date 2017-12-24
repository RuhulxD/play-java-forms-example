name := """play-java-forms-example"""

version := "2.6.x"

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava)


scalaVersion := "2.12.4"

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += guice


libraryDependencies += javaJdbc

// https://mvnrepository.com/artifact/org.yamj/api-common
libraryDependencies += "org.yamj" % "api-common" % "2.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"


libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" // replace by your jpa implementation
)

// https://mvnrepository.com/artifact/com.google.apis/google-api-services-youtube
libraryDependencies += "com.google.apis" % "google-api-services-youtube" % "v3-rev156-1.20.0"

// https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client
libraryDependencies += "com.google.oauth-client" % "google-oauth-client" % "1.23.0"


//PlayKeys.externalizeResources := false