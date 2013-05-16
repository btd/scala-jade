
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_extends_of_common_template_jadeSpec extends Specification {
  "include-extends-of-common-template.jade" should {

    object include_extends_of_common_template_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "div" + "" + ">")
builder ++= ("""test1""")
builder ++= ("</" + "div" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "div" + "" + ">")
builder ++= ("""test2""")
builder ++= ("</" + "div" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-extends-of-common-template.html")).getLines.mkString("\n")
      include_extends_of_common_template_html() === testCaseHtml
    }
  }
}
    