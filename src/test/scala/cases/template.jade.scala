
package com.github.btd.jade.cases

import org.specs2.mutable._

class template_jadeSpec extends Specification {
  "template.jade" should {

    object template_html {
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
        buf("<" + "article" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""{{title}}""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""{{description}}""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "article" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("""article""")
        nl()
        buf("")
        buf("  ")
        buf("""  h2 {{title}}""")
        nl()
        buf("")
        buf("  ")
        buf("""  p {{description}}""")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "template.html")).getLines.mkString("\n")
      template_html() === testCaseHtml
    }
  }
}
