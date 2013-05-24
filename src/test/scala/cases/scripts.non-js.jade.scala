
package com.github.btd.jade.cases

import org.specs2.mutable._

class scripts_non_js_jadeSpec extends Specification {
  "scripts.non-js.jade" should {

    object scripts_non_js_html {
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
        buf("<" + "script" + " " + "id" + "=" + """'user-template'""" + " " + "type" + "=" + """'text/template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "id" + "=" + """'user'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""<%= user.name %>""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""<%= user.description %>""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "id" + "=" + """'user-template'""" + " " + "type" + "=" + """'text/template'""" + ">")
        nl()
        buf("  ")
        buf("""if (foo) {""")
        nl()
        buf("")
        buf("  ")
        buf("""  bar();""")
        nl()
        buf("")
        buf("  ")
        buf("""}""")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "scripts.non-js.html")).getLines.mkString("\n")
      scripts_non_js_html() === testCaseHtml
    }
  }
}
