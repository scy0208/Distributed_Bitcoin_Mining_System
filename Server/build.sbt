


name := "BitcoinServer"

version := "1.0"

scalaVersion := "2.11.5"

resolvers += "Secured Central Repository" at "https://repo1.maven.org/maven2"

externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.3.6"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-remote" % "2.3.6"

libraryDependencies +=
  "com.timesprint" % "hasher_2.10" % "0.3.0"

libraryDependencies +=
  "org.apache.commons" % "commons-lang3" % "3.4"
