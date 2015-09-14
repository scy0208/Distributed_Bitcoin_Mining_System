


name := "BitcoinServer"

version := "1.0"

scalaVersion := "2.11.5"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.3.6"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-remote" % "2.3.6"

libraryDependencies +=
  "com.timesprint" % "hashids-scala_2.11" % "1.0.0"

libraryDependencies +=
  "org.apache.commons" % "commons-lang3" % "3.4"
