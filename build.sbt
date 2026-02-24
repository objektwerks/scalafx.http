name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.8.2-RC3"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "25.0.2-R37",
    "com.lihaoyi" %% "ujson" % "4.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.25"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
