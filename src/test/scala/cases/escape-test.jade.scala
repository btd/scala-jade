
package com.github.btd.jade.cases

import org.specs2.mutable._

class escape_test_jadeSpec extends Specification {
  "escape-test.jade" should {

    object escape_test_html {
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
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""escape-test""")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "textarea" + "" + ">")
        var txt = """<param name="flashvars" value="a=&quot;value_a&quot;&b=&quot;value_b&quot;&c=3"/>"""

        buf("""""" + escape((txt).toString) + """""")
        nl()
        buf("    ")
        buf("</" + "textarea" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "escape-test.html")).getLines.mkString("\n")
      escape_test_html() === testCaseHtml
    }
  }
}
