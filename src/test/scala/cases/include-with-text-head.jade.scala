
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_with_text_head_jadeSpec extends Specification {
  "include-with-text-head.jade" should {

    object include_with_text_head_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "head" + "" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        builder ++= ("""alert('hello world');""")
        builder ++= ("</" + "script" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "head" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-with-text-head.html")).getLines.mkString("\n")
      include_with_text_head_html() === testCaseHtml
    }
  }
}
