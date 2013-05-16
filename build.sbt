
name := "jade"

organization := "com.github.btd"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
	"com.typesafe" 		%% "scalalogging-slf4j" % "1.0.1",
	"ch.qos.logback" 	% "logback-classic" 		% "1.0.12"
)

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "1.14" % "test"
)