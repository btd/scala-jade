
package com.github.btd.jade.cases

import org.specs2.mutable._

class block_expansion_jadeSpec extends Specification {
  "block-expansion.jade" should {

    object block_expansion_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
builder ++= ("foo")
builder ++= ("</" + "a" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
builder ++= ("bar")
builder ++= ("</" + "a" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("</" + "ul" + ">")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("baz")
builder ++= ("</" + "p" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "block-expansion.html")).getLines.mkString("")
      block_expansion_html() === testCaseHtml
    }
  }
}
    