name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.5.0-RC6"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "22.0.0-R33",
    "com.lihaoyi" %% "ujson" % "4.0.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.6"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
