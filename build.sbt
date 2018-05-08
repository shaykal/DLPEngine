name := "DLPEngine"

version := "1.0"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.2.1",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "ch.qos.logback" % "logback-core" % "1.1.3",
  "commons-io" % "commons-io" % "2.6",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  //"org.specs2" %% "specs2" % "3.8.9" % Test,
  "org.mockito" % "mockito-core" % "2.10.0" % "test"
)

mainClass in Compile := Some("kalmanovich.shai.MainApp")