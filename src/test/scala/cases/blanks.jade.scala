
package com.github.btd.jade.cases

import org.specs2.mutable._

class blanks_jadeSpec extends Specification {
  "blanks.jade" should {

    object blanks_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("foo")
builder ++= ("</" + "li" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "li" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("baz")
builder ++= ("</" + "li" + ">")
builder ++= ("</" + "ul" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "blanks.html")).getLines.mkString("")
      blanks_html() === testCaseHtml
    }
  }
}
    