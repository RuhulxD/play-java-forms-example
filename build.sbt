name := """Youtube Player"""

version := "2.6.x"

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, DebianPlugin)

maintainer in Linux := "Ruhul <jacce.ti@gmail.com>"

packageSummary in Linux := "My custom package summary"

packageDescription := "My longer package description"

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

// https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client-jetty
libraryDependencies += "com.google.oauth-client" % "google-oauth-client-jetty" % "1.23.0"

// https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client-java6
libraryDependencies += "com.google.oauth-client" % "google-oauth-client-java6" % "1.23.0"


// https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client
libraryDependencies += "com.google.oauth-client" % "google-oauth-client" % "1.23.0"

// https://mvnrepository.com/artifact/com.google.api-client/google-api-client-extensions
libraryDependencies += "com.google.api-client" % "google-api-client-extensions" % "1.6.0-beta"

// https://mvnrepository.com/artifact/com.google.apis/google-api-services-youtube
libraryDependencies += "com.google.apis" % "google-api-services-youtube" % "v3-rev156-1.20.0"


//PlayKeys.externalizeResources := false

//conflictManager := ConflictManager.strict
