name := "OpenBidder"

version := "1.0"

scalaVersion := "2.11.6"

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= {
  val scalazV = "7.1.2"
  val openrtbV = "2.3.1"
  val specs2V = "3.6"
  val nictaRngV = "1.3.0"
  val akkaV = "2.3.11"
  val akkaStreamsHttpV = "1.0-RC2"
  Seq(
    "org.scalaz"                  %% "scalaz-core"                        % scalazV,
    "org.scalaz"                  %% "scalaz-effect"                      % scalazV,
    "org.openrtb"                  % "openrtb-validator"                  % openrtbV,
    "com.nicta"                   %% "rng"                                % nictaRngV,
    "org.scalaz"                  %% "scalaz-scalacheck-binding"          % scalazV     % "test",
    "org.specs2"                  %% "specs2-core"                        % specs2V     % "test",
    "org.specs2"                  %% "specs2-mock"                        % specs2V     % "test",
    "com.typesafe.akka"            % "akka-actor_2.11"                    % akkaV,
    "com.typesafe.akka"            % "akka-stream-experimental_2.11"      % akkaStreamsHttpV,
    "com.typesafe.akka"            % "akka-http-core-experimental_2.11"   % akkaStreamsHttpV,
    "com.typesafe.akka"            % "akka-http-scala-experimental_2.11"  % akkaStreamsHttpV
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")
