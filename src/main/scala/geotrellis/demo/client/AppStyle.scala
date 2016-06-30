package geotrellis.demo.client

import geotrellis.demo.client.components._
import scalacss.Defaults._
import scalacss.ScalaCssReact._
import scalacss.mutable.GlobalRegistry

object AppCSS {
  val registry = GlobalRegistry

  def apply = registry

  def load() = {
    registry.register(
    )
    registry.addToDocumentOnRegistration()
  }
}
