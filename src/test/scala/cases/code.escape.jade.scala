
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_escape_jadeSpec extends Specification {
  "code.escape.jade" should {

    object code_escape_html {
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
        buf(falsy("<script>").map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy("<script>").map(v => v).getOrElse(""))
        buf("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.escape.html")).getLines.mkString("\n")
      code_escape_html() === testCaseHtml
    }
  }
}
