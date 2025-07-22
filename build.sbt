name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.7.2-RC2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "24.0.0-R35",
    "com.lihaoyi" %% "ujson" % "4.2.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.18"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all",
  // Silences 3.7.0+ implicit using warnings:
  "-Wconf:msg=Implicit parameters should be provided with a `using` clause:s"
)
