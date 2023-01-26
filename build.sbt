name := "scalafx.akka.http"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.2.2"
libraryDependencies ++= {
  val akkaVersion = "2.8.0-M4"
  val akkaHttpVersion = "10.5.0-M1"
  Seq(
    "org.scalafx" %% "scalafx" % "19.0.0-R30",
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.lihaoyi" %% "ujson" % "2.0.0",
    "ch.qos.logback" % "logback-classic" % "1.4.5"
  )
}