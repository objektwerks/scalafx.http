name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.9.0-RC4"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "26.0.0-R38",
    "com.lihaoyi" %% "ujson" % "4.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.38"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
