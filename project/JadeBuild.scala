import com.typesafe.sbt.SbtScalariform.scalariformSettings

import sbt._
import sbt.Keys._

object JadeBuild extends Build {

  lazy val jade = Project(
    id = "jade",
    base = file("."),
    settings = Project.defaultSettings ++ scalariformSettings
  )
}
