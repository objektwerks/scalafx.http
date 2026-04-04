name := "scalafx.http"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.8.3-RC3"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "26.0.0-R38",
    "com.lihaoyi" %% "ujson" % "4.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.32"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
