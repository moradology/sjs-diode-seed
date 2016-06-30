package geotrellis.demo.client

import japgolly.scalajs.react.vdom._


package object components {
  implicit class StrAttr(s: String) {
    def attr = new ReactAttr.Generic(s)
  }
}
