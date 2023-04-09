name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.2.2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "20.0.0-R31",
    "com.lihaoyi" %% "ujson" % "3.1.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.4.6"
  )
}
