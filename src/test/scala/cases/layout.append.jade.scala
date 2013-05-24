
package com.github.btd.jade.cases

import org.specs2.mutable._

class layout_append_jadeSpec extends Specification {
  "layout.append.jade" should {

    object layout_append_html {
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
        buf("<" + "script" + " " + "src" + "=" + """'vendor/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'bar.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "layout.append.html")).getLines.mkString("\n")
      layout_append_html() === testCaseHtml
    }
  }
}
