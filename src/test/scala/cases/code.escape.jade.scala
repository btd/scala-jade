
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_escape_jadeSpec extends Specification {
  "code.escape.jade" should {

    object code_escape_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "p" + "" + ">")
builder ++= ('<script>')
builder ++= ("</" + "p" + ">")
builder ++= ("<" + "p" + "" + ">")
builder ++= ('<script>')
builder ++= ("</" + "p" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.escape.html")).getLines.mkString("")
      code_escape_html() === testCaseHtml
    }
  }
}
    