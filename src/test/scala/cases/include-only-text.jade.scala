
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_only_text_jadeSpec extends Specification {
  "include-only-text.jade" should {

    object include_only_text_html {
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
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""The message is """")
        buf("<" + "em" + "" + ">")
        buf("""hello world""")
        buf("</" + "em" + ">")
        buf(""""""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-only-text.html")).getLines.mkString("\n")
      include_only_text_html() === testCaseHtml
    }
  }
}
