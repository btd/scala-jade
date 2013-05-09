
package com.github.btd.jade.cases

import org.specs2.mutable._

class classes_empty_jadeSpec extends Specification {
  "classes-empty.jade" should {

    object classes_empty_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "a" + "" + ">")
builder ++= ("(class='')")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + falsy(null).map(v => "class" + "=" + "'" + v + "'").getOrElse("") + ">")
builder ++= ("</" + "a" + ">")
val n = (None)

builder ++= ("<" + "a" + " " + falsy(n).map(v => "class" + "=" + "'" + v + "'").getOrElse("") + ">")
builder ++= ("</" + "a" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "classes-empty.html")).getLines.mkString("")
      classes_empty_html() === testCaseHtml
    }
  }
}
    