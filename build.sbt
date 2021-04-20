name := "scalafx.akka.http"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.5"
libraryDependencies ++= {
  val akkaVersion = "2.6.14"
  val json4sVersion = "3.6.10"
  Seq(
    "org.scalafx" %% "scalafx" % "15.0.1-R21",
    "com.typesafe.akka" %% "akka-http" % "10.2.4",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "org.json4s" %% "json4s-jackson" % json4sVersion,
    "org.json4s" %% "json4s-native" % json4sVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}