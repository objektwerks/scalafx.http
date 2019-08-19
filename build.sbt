name := "scalafx"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.9"
libraryDependencies ++= {
  val json4sVersion = "3.5.3"
  Seq(
    "org.scalafx" %% "scalafx" % "8.0.144-R12",
    "net.databinder.dispatch" %% "dispatch-core" % "0.13.2",
    "org.json4s" %% "json4s-jackson" % json4sVersion,
    "org.json4s" %% "json4s-native" % json4sVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3"
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
