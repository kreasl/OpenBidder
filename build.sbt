import NativePackagerHelper._

name := "OpenBidder"

version := "1.0"

scalaVersion := "2.11.6"

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  val scalazV = "7.1.2"
  val openrtbV = "2.3.1"
  val specs2V = "3.6"
  val nictaRngV = "1.3.0"
  val akkaV = "2.3.11"
  val akkaStreamV = "1.0-RC2"
  val playJsonV = "2.4.0-M2" // stable version to fix Play Json import issue
  val scalazSpecs2V = "0.3.0"
  val aerospikeV = "latest.integration"
  Seq(
    "org.scalaz" %% "scalaz-core" % scalazV,
    "org.scalaz" %% "scalaz-effect" % scalazV,
    "org.openrtb" % "openrtb-validator" % openrtbV,
    "com.nicta" %% "rng" % nictaRngV,
    "com.typesafe.play" %% "play-json" % playJsonV,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-kernel" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-scala-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV,
    "org.scalaz" %% "scalaz-scalacheck-binding" % scalazV % "test",
    "org.specs2" %% "specs2-core" % specs2V % "test",
    "org.specs2" %% "specs2-mock" % specs2V % "test",
    "com.typesafe.akka" %% "akka-http-testkit-scala-experimental" % akkaStreamV % "test",
    "org.typelevel" %% "scalaz-specs2" % scalazSpecs2V % "test",
    "com.aerospike" % "aerospike-client" % aerospikeV
  )
}

mainClass in Compile := Some("akka.kernel.Main")

enablePlugins(JavaServerAppPackaging)

scalacOptions in Test ++= Seq("-Yrangepos")
