name := "OpenBidder"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.5" % "test")
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.1.2"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies +=  "org.openrtb" % "openrtb-validator" % "2.3.1"