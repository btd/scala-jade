
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_only_text_body_jadeSpec extends Specification {
  "include-only-text-body.jade" should {

    object include_only_text_body_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("""The message is """")
        builder ++= (""""""")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-only-text-body.html")).getLines.mkString("\n")
      include_only_text_body_html() === testCaseHtml
    }
  }
}
