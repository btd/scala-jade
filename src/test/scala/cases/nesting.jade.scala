
package com.github.btd.jade.cases

import org.specs2.mutable._

class nesting_jadeSpec extends Specification {
  "nesting.jade" should {

    object nesting_html {
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
        buf("<" + "li" + "" + ">")
        buf("""a""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""b""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("      ")
        buf("<" + "li" + "" + ">")
        buf("""c""")
        buf("</" + "li" + ">")
        nl()
        buf("      ")
        buf("<" + "li" + "" + ">")
        buf("""d""")
        buf("</" + "li" + ">")
        nl()
        buf("    ")
        buf("</" + "ul" + ">")
        nl()
        buf("  ")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""e""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "nesting.html")).getLines.mkString("\n")
      nesting_html() === testCaseHtml
    }
  }
}
