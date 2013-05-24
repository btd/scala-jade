
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_yield_nested_jadeSpec extends Specification {
  "include.yield.nested.jade" should {

    object include_yield_nested_html {
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
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Page""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "id" + "=" + """'content'""" + ">")
        nl()
        buf("      ")
        buf("<" + "div" + " " + "id" + "=" + """'content-wrapper'""" + ">")
        nl()
        buf("        ")
        buf("<" + "p" + "" + ">")
        buf("""some content""")
        buf("</" + "p" + ">")
        nl()
        buf("        ")
        buf("<" + "p" + "" + ">")
        buf("""and some more""")
        buf("</" + "p" + ">")
        nl()
        buf("      ")
        buf("</" + "div" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "id" + "=" + """'footer'""" + ">")
        nl()
        buf("      ")
        buf("<" + "stuff" + "" + ">")
        buf("</" + "stuff" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include.yield.nested.html")).getLines.mkString("\n")
      include_yield_nested_html() === testCaseHtml
    }
  }
}
