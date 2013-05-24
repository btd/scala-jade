
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_with_text_jadeSpec extends Specification {
  "include-with-text.jade" should {

    object include_with_text_html {
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
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        buf("""alert('hello world');""")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-with-text.html")).getLines.mkString("\n")
      include_with_text_html() === testCaseHtml
    }
  }
}
