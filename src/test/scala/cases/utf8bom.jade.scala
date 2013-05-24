
package com.github.btd.jade.cases

import org.specs2.mutable._

class utf8bom_jadeSpec extends Specification {
  "utf8bom.jade" should {

    object utf8bom_html {
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

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "utf8bom.html")).getLines.mkString("\n")
      utf8bom_html() === testCaseHtml
    }
  }
}
