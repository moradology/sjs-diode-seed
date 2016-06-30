resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.bintrayRepo("moradology", "maven"),
  Classpaths.sbtPluginReleases,
  Opts.resolver.sonatypeReleases
)


