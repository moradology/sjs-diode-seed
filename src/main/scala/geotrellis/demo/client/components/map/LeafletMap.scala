package geotrellis.demo.client.components.map

import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.{JSName, JSExport}

import org.leaflet._
import diode._
import diode.react._
import diode.react.ReactPot._
import geotrellis.demo.client._
import geotrellis.demo.client.circuit._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.Event


object LeafletMap {

  val baseLayers = Map(
    "toner_lite" -> LTileLayer("http://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png", stamenLayerOpts),
    "terrain" -> LTileLayer("http://{s}.tile.stamen.com/terrain/{z}/{x}/{y}.png", stamenLayerOpts),
    "watercolor" -> LTileLayer("http://{s}.tile.stamen.com/watercolor/{z}/{x}/{y}.png", stamenLayerOpts),
    "azavea" -> LTileLayer("http://{s}.tiles.mapbox.com/v3/azavea.map-zbompf85/{z}/{x}/{y}.png", mapboxLayerOpts),
    "worldGlass" -> LTileLayer("http://{s}.tiles.mapbox.com/v3/mapbox.world-glass/{z}/{x}/{y}.png", mapboxLayerOpts),
    "worldBlank" -> LTileLayer("http://{s}.tiles.mapbox.com/v3/mapbox.world-blank-light/{z}/{x}/{y}.png", mapboxLayerOpts),
    "worldLight" -> LTileLayer("http://{s}.tiles.mapbox.com/v3/mapbox.world-light/{z}/{x}/{y}.png", mapboxLayerOpts)
  )

  val defaultMapOptions =
    LMapOptions
      .center(LLatLng(41.850033, -87.6500523))
      .zoom(5)
      .layers(js.Array(baseLayers("toner_lite")))
      .result

  def mapboxLayerOpts =
    LTileLayerOptions
      .attribution("Map data &copy; <a href=\"http://openstreetmap.org\">OpenStreetMap</a> contributors, <a href=\"http://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, Imagery &copy; <a href=\"http://mapbox.com\">MapBox</a>")
      .result

  def stamenLayerOpts =
    LTileLayerOptions
      .attribution("Map tiles by <a href=\"http://stamen.com\">Stamen Design</a>, <a href=\"http://creativecommons.org/licenses/by/3.0\">CC BY 3.0</a> &mdash; Map data &copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>")
      .result

  class Backend($: BackendScope[ModelProxy[LMapModel], Unit]) {

    def init = {
      ($.props >>= { props: ModelProxy[LMapModel] =>
        props.dispatch(InitLMap("map", defaultMapOptions))
      }) >>
        ($.props >>= { props: ModelProxy[LMapModel] =>
          props().map match {
            case None => Callback.empty
            case Some(lmap) => Callback {
              lmap.onZoomend({ e: LDragEndEvent =>
                val zl = lmap.getZoom().toInt
                props.dispatch(UpdateZoomLevel(Some(zl))).runNow()
              })
            }
          }
        })
    }

    def render() = {
      <.div(^.id := "map")
    }
  }

  private val leafletMap = ReactComponentB[ModelProxy[LMapModel]]("LeafletMap")
    .renderBackend[Backend]
    .componentDidMount(_.backend.init)
    .build

  def apply(props: ModelProxy[LMapModel]) = leafletMap(props)
}
