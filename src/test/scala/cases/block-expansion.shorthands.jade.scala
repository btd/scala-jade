
package com.github.btd.jade.cases

import org.specs2.mutable._

class block_expansion_shorthands_jadeSpec extends Specification {
  "block-expansion.shorthands.jade" should {

    object block_expansion_shorthands_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("<" + "li" + " " + "class" + "=" + """'list-item'""" + ">")
builder ++= ("<" + "div" + " " + "id" + "=" + """'foo'""" + ">")
builder ++= ("<" + "div" + " " + "id" + "=" + """'bar'""" + ">")
builder ++= ("baz")
builder ++= ("</" + "div" + ">")
builder ++= ("</" + "div" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("</" + "ul" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "block-expansion.shorthands.html")).getLines.mkString("")
      block_expansion_shorthands_html() === testCaseHtml
    }
  }
}
    