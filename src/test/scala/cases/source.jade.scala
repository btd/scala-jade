
package com.github.btd.jade.cases

import org.specs2.mutable._

class source_jadeSpec extends Specification {
  "source.jade" should {

    object source_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "audio" + " " + "preload" + "=" + """'auto'""" + " " + "autobuffer" + "=" + """'autobuffer'""" + " " + "controls" + "=" + """'controls'""" + ">")
        nl()
        buf("    ")
        buf("<" + "source" + " " + "src" + "=" + """'foo'""" + "/>")
        nl()
        buf("    ")
        buf("<" + "source" + " " + "src" + "=" + """'bar'""" + "/>")
        nl()
        buf("  ")
        buf("</" + "audio" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "source.html")).getLines.mkString("\n")
      source_html() === testCaseHtml
    }
  }
}
