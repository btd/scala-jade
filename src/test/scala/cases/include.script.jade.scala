
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_script_jadeSpec extends Specification {
  "include.script.jade" should {

    object include_script_html {
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
        buf("<" + "script" + " " + "id" + "=" + """'pet-template'""" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "class" + "=" + """'pet'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""{{name}}""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""{{name}} is a {{species}} that is {{age}} old""")
        buf("</" + "p" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include.script.html")).getLines.mkString("\n")
      include_script_html() === testCaseHtml
    }
  }
}
