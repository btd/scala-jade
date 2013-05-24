
package com.github.btd.jade.cases

import org.specs2.mutable._

class block_expansion_jadeSpec extends Specification {
  "block-expansion.jade" should {

    object block_expansion_html {
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
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
        buf("""foo""")
        buf("</" + "a" + ">")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
        buf("""bar""")
        buf("</" + "a" + ">")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf("""baz""")
        buf("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "block-expansion.html")).getLines.mkString("\n")
      block_expansion_html() === testCaseHtml
    }
  }
}
