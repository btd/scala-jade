
package com.github.btd.jade.cases

import org.specs2.mutable._

class text_jadeSpec extends Specification {
  "text.jade" should {

    object text_html {
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
        buf("<" + "option" + " " + "value" + "=" + """''""" + ">")
        buf("""-- (selected) --""")
        buf("</" + "option" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        nl()
        buf("  ")
        buf("""foo""")
        nl()
        buf("")
        buf("  ")
        buf("""bar""")
        nl()
        buf("")
        buf("  ")
        buf("""baz""")
        nl()
        buf("")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        nl()
        buf("  ")
        buf("""foo""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("  ")
        buf("""bar""")
        nl()
        buf("")
        buf("  ")
        buf("""baz""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "pre" + "" + ">")
        buf("""foo""")
        nl()
        buf("")
        buf("""  bar""")
        nl()
        buf("")
        buf("""    baz""")
        nl()
        buf("")
        buf(""".""")
        buf("</" + "pre" + ">")
        nl()
        buf("")
        buf("<" + "pre" + "" + ">")
        buf("""foo""")
        nl()
        buf("")
        buf("""  bar""")
        nl()
        buf("")
        buf("""    baz""")
        nl()
        buf("")
        buf(""".""")
        buf("</" + "pre" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "text.html")).getLines.mkString("\n")
      text_html() === testCaseHtml
    }
  }
}
