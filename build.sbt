name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.4.1-RC2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "21.0.0-R32",
    "com.lihaoyi" %% "ujson" % "3.2.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.3"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
