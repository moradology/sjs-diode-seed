import com.lihaoyi.workbench.Plugin._

name := "geotrellis-demo"

scalaVersion := "2.11.8"

organization := "Azavea"

licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

// To avoid manually launching the app in HTML
persistLauncher in Compile := true
persistLauncher in Test := false

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "utest" % "0.4.3" % "test",
  "com.github.japgolly.scalajs-react" %%% "core" % "0.11.1",
  "com.github.japgolly.scalajs-react" %%% "extra" % "0.11.1",
  "com.github.japgolly.scalacss" %% "core" % "0.4.1",
  "com.github.japgolly.scalacss" %%% "ext-react" % "0.4.1",
  "io.circe" %% "circe-core" % "0.4.1",
  "io.circe" %% "circe-generic" % "0.4.1",
  "io.circe" %% "circe-parser" % "0.4.1",
  "io.circe" %% "circe-scalajs_sjs0.6" % "0.4.1",
  "me.chrons" %%% "diode" % "1.0.0",
  "me.chrons" %%% "diode-devtools" % "1.0.0",
  "me.chrons" %%% "diode-react" % "1.0.0",
  "org.scala-js" %%% "scalajs-dom" % "0.9.0",
  "Azavea" %%% "leaflet-sjs" % "0.1.0"
)

parallelExecution in Test := false

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-Yinline-warnings",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:existentials",
  "-feature"
  )

shellPrompt := { s => Project.extract(s).currentProject.id + " > " }

// ScalaJS setup
enablePlugins(ScalaJSPlugin)

workbenchSettings

bootSnippet := "geotrellis.demo.client.Main().main();"

mainClass in (Compile, run) := Some("geotrellis.demo.client.Main")

//refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile)
updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)

jsDependencies ++= Seq(
  "org.webjars" % "jquery" % "2.2.1"
  / "jquery.js"
  minified "jquery.min.js"
  commonJSName "jQuery",

  "org.webjars" % "bootstrap" % "3.3.2"
  / "bootstrap.js"
  minified "bootstrap.min.js"
  dependsOn "jquery.js",

  "org.webjars.bower" % "react" % "15.1.0"
    /        "react-with-addons.js"
    minified "react-with-addons.min.js"
    commonJSName "React",

  "org.webjars.bower" % "react" % "15.1.0"
    /         "react-dom.js"
    minified  "react-dom.min.js"
    dependsOn "react-with-addons.js"
    commonJSName "ReactDOM",

  "org.webjars.bower" % "react" % "15.1.0"
    /         "react-dom-server.js"
    minified  "react-dom-server.min.js"
    dependsOn "react-dom.js"
    commonJSName "ReactDOMServer",

  "org.webjars.bower" % "leaflet" % "1.0.0-rc.1"
    /         "leaflet.js"
    commonJSName "L"
)

