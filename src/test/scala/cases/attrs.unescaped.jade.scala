
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_unescaped_jadeSpec extends Specification {
  "attrs.unescaped.jade" should {

    object attrs_unescaped_html {
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
        buf("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "id" + "=" + """'user-<%= user.id %>'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""<%= user.title %>""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.unescaped.html")).getLines.mkString("\n")
      attrs_unescaped_html() === testCaseHtml
    }
  }
}
