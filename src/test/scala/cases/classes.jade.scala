
package com.github.btd.jade.cases

import org.specs2.mutable._

class classes_jadeSpec extends Specification {
  "classes.jade" should {

    object classes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "a" + " " + "class" + "=" + """'foo bar'""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "class" + "=" + """'foo-bar_baz'""" + ">")
builder ++= ("</" + "a" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "classes.html")).getLines.mkString("\n")
      classes_html() === testCaseHtml
    }
  }
}
    