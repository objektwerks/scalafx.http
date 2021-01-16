enablePlugins(JlinkPlugin)

name := "scalafx.akka.http"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.4"
libraryDependencies ++= {
  val akkaVersion = "2.6.11"
  val json4sVersion = "3.6.10"
  Seq(
    "org.scalafx" %% "scalafx" % "14-R19",
    "com.typesafe.akka" %% "akka-http" % "10.2.2",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "org.json4s" %% "json4s-jackson" % json4sVersion,
    "org.json4s" %% "json4s-native" % json4sVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m => "org.openjfx" % s"javafx-$m" % "14.0.1" classifier osName )
jlinkModules := {
  jlinkModules.value :+ "jdk.unsupported"
}
jlinkIgnoreMissingDependency := JlinkIgnore.everything
