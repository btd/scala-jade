
package com.github.btd.jade.cases

import org.specs2.mutable._

class styles_jadeSpec extends Specification {
  "styles.jade" should {

    object styles_html {
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
        buf("<" + "style" + "" + ">")
        nl()
        buf("      ")
        buf("""body {""")
        nl()
        buf("")
        buf("      ")
        buf("""  padding: 50px;""")
        nl()
        buf("")
        buf("      ")
        buf("""}""")
        nl()
        buf("    ")
        buf("</" + "style" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "styles.html")).getLines.mkString("\n")
      styles_html() === testCaseHtml
    }
  }
}
