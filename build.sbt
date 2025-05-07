name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.6.4" // Scala 3.7.1-RC1 breaks ScalaFx!
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "24.0.0-R35",
    "com.lihaoyi" %% "ujson" % "4.1.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.18"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
