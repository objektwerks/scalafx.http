name := "scalafx.http"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.2.2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "19.0.0-R30",
    "com.lihaoyi" %% "ujson" % "2.0.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.4.5"
  )
}