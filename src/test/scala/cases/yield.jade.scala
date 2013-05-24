
package com.github.btd.jade.cases

import org.specs2.mutable._

class yield_jadeSpec extends Specification {
  "yield.jade" should {

    object yield_html {
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
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "yield.html")).getLines.mkString("\n")
      yield_html() === testCaseHtml
    }
  }
}
