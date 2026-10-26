name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.10.0"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "27.0.0-R39",
    "com.lihaoyi" %% "ujson" % "4.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.6.5"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
