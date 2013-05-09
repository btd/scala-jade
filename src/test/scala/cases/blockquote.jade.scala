
package com.github.btd.jade.cases

import org.specs2.mutable._

class blockquote_jadeSpec extends Specification {
  "blockquote.jade" should {

    object blockquote_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "figure" + "" + ">")
builder ++= ("<" + "blockquote" + "" + ">")
builder ++= ("Try to define yourself by what you do, and you&#8217;ll burnout every time. You are. That is enough. I rest in that.")
builder ++= ("</" + "blockquote" + ">")
builder ++= ("<" + "figcaption" + "" + ">")
builder ++= ("from @thefray at 1:43pm on May 10")
builder ++= ("</" + "figcaption" + ">")
builder ++= ("</" + "figure" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "blockquote.html")).getLines.mkString("")
      blockquote_html() === testCaseHtml
    }
  }
}
    