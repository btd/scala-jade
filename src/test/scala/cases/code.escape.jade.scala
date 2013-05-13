
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_escape_jadeSpec extends Specification {
  "code.escape.jade" should {

    object code_escape_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy("<script>").map(v => escape(v)).getOrElse(""))
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy("<script>").map(v => v).getOrElse(""))
        builder ++= ("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.escape.html")).getLines.mkString("\n")
      code_escape_html() === testCaseHtml
    }
  }
}
