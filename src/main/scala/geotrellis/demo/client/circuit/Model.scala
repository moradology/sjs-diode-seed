package geotrellis.demo.client.circuit

import io.circe.Json
import scala.scalajs.js

import diode._
import geotrellis.demo.client._
import io.circe.generic.semiauto._
import org.leaflet._

//[> The Models, i.e. our global Diode state <]
case class RootModel(mapModel: LMapModel = LMapModel())

case class LMapModel(map: Option[LMap] = None,
                     zoomLevel: Option[Int] = None)

// Actions (implicit for type guarantee of action being dispatched)
trait Action

object Action {
  implicit object actionType extends ActionType[Action]
}

case class InitLMap(elemID: String, opts: LMapOptions) extends Action
case class UpdateZoomLevel(z: Option[Int]) extends Action
