name := "scalafx.akka.http"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.2.2"
libraryDependencies ++= {
  val akkaVersion = "2.7.0"
  val akkaHttpVersion = "10.4.0"
  val json4sVersion = "4.0.6"
  Seq(
    "org.scalafx" %% "scalafx" % "19.0.0-R30",
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "org.json4s" %% "json4s-jackson" % json4sVersion,
    "org.json4s" %% "json4s-native" % json4sVersion,
    "ch.qos.logback" % "logback-classic" % "1.4.5"
  )
}
