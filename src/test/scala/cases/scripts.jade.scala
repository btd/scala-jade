
package com.github.btd.jade.cases

import org.specs2.mutable._

class scripts_jadeSpec extends Specification {
  "scripts.jade" should {

    object scripts_html {
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
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "scripts.html")).getLines.mkString("\n")
      scripts_html() === testCaseHtml
    }
  }
}
