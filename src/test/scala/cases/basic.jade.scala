
package com.github.btd.jade.cases

import org.specs2.mutable._

class basic_jadeSpec extends Specification {
  "basic.jade" should {

    object basic_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "html" + "" + ">")
builder ++= ("<" + "body" + "" + ">")
builder ++= ("<" + "h1" + "" + ">")
builder ++= ("Title")
builder ++= ("</" + "h1" + ">")
builder ++= ("</" + "body" + ">")
builder ++= ("</" + "html" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "basic.html")).getLines.mkString("")
      basic_html() === testCaseHtml
    }
  }
}
    