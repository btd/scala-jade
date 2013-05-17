
package com.github.btd.jade.cases

import org.specs2.mutable._

class escape_chars_jadeSpec extends Specification {
  "escape-chars.jade" should {

    object escape_chars_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
builder ++= ("""var re = /\d+/;""")
builder ++= ("</" + "script" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "escape-chars.html")).getLines.mkString("\n")
      escape_chars_html() === testCaseHtml
    }
  }
}
    