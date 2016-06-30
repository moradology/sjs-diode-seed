package geotrellis.demo.client.routes

import diode.react._
import diode.react.ReactPot._
import geotrellis.demo.client.circuit._
import geotrellis.demo.client.components._
import geotrellis.demo.client.components.map._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import scalacss.ScalaCssReact._


object GeotrellisDemo {

  case class State(showModal: Boolean = true, showJSON: Boolean = false)

  class Backend($: BackendScope[Unit, State]) {

    def render(state: State) = {
      <.div(
        CircuitRoot.wrap(_.mapModel)(LeafletMap(_))
      )
    }
  }

  private val component = ReactComponentB[Unit]("GeotrellisAdminClient")
    .initialState(State())
    .renderBackend[Backend]
    .build

  def apply() = component()

}
