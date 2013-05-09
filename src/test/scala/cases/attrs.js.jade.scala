
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_js_jadeSpec extends Specification {
  "attrs.js.jade" should {

    object attrs_js_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
var id = 5

def answer() = 42

builder ++= ("<" + "a" + " " + "href" + "=" + """'/user/""" + 42 + """'""" + " " + "class" + "=" + """'button'""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'/user/""" + id + """'""" + " " + "class" + "=" + """'button'""" + ">")
builder ++= ("</" + "a" + ">")
val a = answer()

builder ++= ("<" + "meta" + " " + "key" + "=" + """'answer'""" + " " + falsy(a).map(v => "value" + "=" + "'" + v + "'").getOrElse("") + ">")
builder ++= ("</" + "meta" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.js.html")).getLines.mkString("")
      attrs_js_html() === testCaseHtml
    }
  }
}
    