
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_data_jadeSpec extends Specification {
  "attrs-data.jade" should {

    object attrs_data_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
val user = ("Tobi")

builder ++= ("<" + "foo" + " " + falsy(user).map(v => "data-user" + "=" + "'" + v + "'").getOrElse("") + ">")
builder ++= ("</" + "foo" + ">")
builder ++= ("<" + "bar" + " " + "data-username" + "=" + """'tobi'""" + ">")
builder ++= ("</" + "bar" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs-data.html")).getLines.mkString("")
      attrs_data_html() === testCaseHtml
    }
  }
}
    