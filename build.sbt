name := """specs2-mockito-test"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

PlayKeys.playOmnidoc := false

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.h2database" % "h2" % "1.4.191",
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"