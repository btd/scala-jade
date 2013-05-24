
package com.github.btd.jade.cases

import org.specs2.mutable._

class block_expansion_shorthands_jadeSpec extends Specification {
  "block-expansion.shorthands.jade" should {

    object block_expansion_shorthands_html {
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
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + " " + "class" + "=" + """'list-item'""" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "class" + "=" + """'foo'""" + ">")
        nl()
        buf("      ")
        buf("<" + "div" + " " + "id" + "=" + """'bar'""" + ">")
        buf("""baz""")
        buf("</" + "div" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
        nl()
        buf("  ")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "block-expansion.shorthands.html")).getLines.mkString("\n")
      block_expansion_shorthands_html() === testCaseHtml
    }
  }
}
