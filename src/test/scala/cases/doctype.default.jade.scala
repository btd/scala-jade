
package com.github.btd.jade.cases

import org.specs2.mutable._

class doctype_default_jadeSpec extends Specification {
  "doctype.default.jade" should {

    object doctype_default_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("""<!DOCTYPE html>""")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Title""")
        buf("</" + "h1" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "doctype.default.html")).getLines.mkString("\n")
      doctype_default_html() === testCaseHtml
    }
  }
}
