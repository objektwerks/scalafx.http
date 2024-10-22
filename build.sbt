name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.5.2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "23.0.1-R34",
    "com.lihaoyi" %% "ujson" % "4.0.2",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.11"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
