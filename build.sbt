name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.8.0-RC1"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "24.0.2-R36",
    "com.lihaoyi" %% "ujson" % "4.4.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.21"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
