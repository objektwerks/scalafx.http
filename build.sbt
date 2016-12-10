name := "scalafx"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.11.8"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
libraryDependencies ++= {
  val json4sVersion = "3.5.0"
  Seq(
    "org.scalafx" % "scalafx_2.11" % "8.0.102-R11",
    "com.typesafe.play" % "play-ws_2.11" % "2.5.10",
    "org.json4s" % "json4s-jackson_2.11" % json4sVersion,
    "org.json4s" % "json4s-native_2.11" % json4sVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.7"
  )
}
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))
scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:reflectiveCalls",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-feature",
  "-Ywarn-unused-import",
  "-Ywarn-unused",
  "-Ywarn-dead-code",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings",
  "-Xlint:missing-interpolator",
  "-Xlint"
)
fork in test := true
javaOptions += "-server -Xss1m -Xmx2g"
