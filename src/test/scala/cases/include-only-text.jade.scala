
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_only_text_jadeSpec extends Specification {
  "include-only-text.jade" should {

    object include_only_text_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "html" + "" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "body" + "" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= ("""The message is """")
        builder ++= ("<" + "em" + "" + ">")
        builder ++= ("""hello world""")
        builder ++= ("</" + "em" + ">")
        builder ++= (""""""")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("</" + "body" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-only-text.html")).getLines.mkString("\n")
      include_only_text_html() === testCaseHtml
    }
  }
}
