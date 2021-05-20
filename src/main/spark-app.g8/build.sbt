name := "SparkApp"

version := "1.8"

scalaVersion := "2.12.10"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  artifact.name + "_" + module.revision + "." + artifact.extension,
}

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.1" % "provided"
libraryDependencies += "com.databricks" % "dbutils-api_2.12" % "0.0.5" % "provided"
