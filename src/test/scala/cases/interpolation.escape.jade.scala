
package com.github.btd.jade.cases

import org.specs2.mutable._

class interpolation_escape_jadeSpec extends Specification {
  "interpolation.escape.jade" should {

    object interpolation_escape_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var id = 42;

        nl()
        buf("")
        buf("<" + "foo" + "" + ">")
        nl()
        buf("  ")
        buf("""some""")
        nl()
        buf("")
        buf("  ")
        buf("""#{text}""")
        nl()
        buf("")
        buf("  ")
        buf("""here""")
        nl()
        buf("")
        buf("  ")
        buf("""My ID is {""" + escape((id).toString) + """}""")
        nl()
        buf("")
        buf("</" + "foo" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "interpolation.escape.html")).getLines.mkString("\n")
      interpolation_escape_html() === testCaseHtml
    }
  }
}
