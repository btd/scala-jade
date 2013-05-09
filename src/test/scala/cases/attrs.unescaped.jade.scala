
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_unescaped_jadeSpec extends Specification {
  "attrs.unescaped.jade" should {

    object attrs_unescaped_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
builder ++= ("<" + "div" + " " + "id" + "=" + """'user-<%= user.id %>'""" + ">")
builder ++= ("<" + "h1" + "" + ">")
builder ++= ("<%= user.title %>")
builder ++= ("</" + "h1" + ">")
builder ++= ("</" + "div" + ">")
builder ++= ("</" + "script" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.unescaped.html")).getLines.mkString("")
      attrs_unescaped_html() === testCaseHtml
    }
  }
}
    