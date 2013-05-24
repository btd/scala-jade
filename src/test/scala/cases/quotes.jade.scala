
package com.github.btd.jade.cases

import org.specs2.mutable._

class quotes_jadeSpec extends Specification {
  "quotes.jade" should {

    object quotes_html {
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
        buf("<" + "p" + "" + ">")
        buf(""""foo"""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf("""'foo'""")
        buf("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "quotes.html")).getLines.mkString("\n")
      quotes_html() === testCaseHtml
    }
  }
}
