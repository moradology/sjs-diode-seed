package geotrellis.demo.client.circuit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSON
import scala.scalajs.js

import cats.data.Xor
import diode._
import diode.data._
import diode.react.ReactConnector
import io.circe._
import io.circe.generic.semiauto._
import io.circe.scalajs._
import scala.util.Try
import org.leaflet._

import geotrellis.demo.client._


class LMapHandler[M](modelRW: ModelRW[M, LMapModel]) extends ActionHandler(modelRW) {
  override def handle = {
    case InitLMap(elemID: String, mapOpts: LMapOptions) =>
      val map = Try(LMap(elemID, mapOpts)).toOption
      println("map", map)
      updated(value.copy(map = map))
    case UpdateZoomLevel(zl) =>
      updated(value.copy(zoomLevel = zl))

  }
}


object CircuitRoot extends Circuit[RootModel] with ReactConnector[RootModel] {
  // provides initial model to the Circuit
  override def initialModel = RootModel()
  // combine all handlers into one
  override protected val actionHandler = composeHandlers(
    new LMapHandler(zoomRW(_.mapModel)((root, newVal) => root.copy(mapModel = newVal)))
  )
}


