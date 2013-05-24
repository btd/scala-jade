
package com.github.btd.jade.cases

import org.specs2.mutable._

class script_whitespace_jadeSpec extends Specification {
  "script.whitespace.jade" should {

    object script_whitespace_html {
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
        buf("""""")
        nl()
        buf("")
        buf("  ")
        buf("""  bar();""")
        nl()
        buf("")
        buf("  ")
        buf("""  """)
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "script.whitespace.html")).getLines.mkString("\n")
      script_whitespace_html() === testCaseHtml
    }
  }
}
