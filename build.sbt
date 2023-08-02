ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .enablePlugins(ScalaTsiPlugin)
  .settings(
    Compile / scalacOptions += "-Xmax-inlines:60",
    typescriptExports := Seq("BackendRequests","BackendResponses","BackendAuthRequests","BackendAuthResponses","BackendError","WSRequest","WSResponse"),
    typescriptOutputFile := new File("/Users/bogdan.vakulenko/Documents/backend-model-gen.ts"),
    typescriptGenerationImports := Seq("test.Http._")
  )

libraryDependencies += "com.scalatsi" %% "scala-tsi" % "0.8.2"
