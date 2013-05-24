
package com.github.btd.jade.cases

import org.specs2.mutable._

class blanks_jadeSpec extends Specification {
  "blanks.jade" should {

    object blanks_html {
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
        buf("""foo""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""bar""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""baz""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "blanks.html")).getLines.mkString("\n")
      blanks_html() === testCaseHtml
    }
  }
}
