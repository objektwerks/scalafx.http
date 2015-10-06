name := "objektwerks.scalafx"

version := "1.0"

scalaVersion := "2.11.7"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

libraryDependencies ++= {
  val json4sVersion = "3.2.11"
  Seq(
    "org.scalafx" % "scalafx_2.11" % "8.0.60-R9",
    "net.databinder.dispatch" % "dispatch-core_2.11" % "0.11.3",
    "org.json4s" % "json4s-jackson_2.11" % json4sVersion,
    "org.json4s" % "json4s-native_2.11" % json4sVersion,
    "org.slf4j" % "slf4j-api" % "1.7.12",
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test"
  )
}

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-language:higherKinds",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Xfatal-warnings"
)

fork in test := true

javaOptions += "-server -Xss1m -Xmx2g"

logLevel := Level.Info
