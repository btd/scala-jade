
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_interpolation_jadeSpec extends Specification {
  "attrs.interpolation.jade" should {

    object attrs_interpolation_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
var id = 5

builder ++= ("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((id).toString) + """'""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "foo" + " " + "bar" + "=" + """'stuff #{here} yup'""" + ">")
builder ++= ("</" + "foo" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.interpolation.html")).getLines.mkString("\n")
      attrs_interpolation_html() === testCaseHtml
    }
  }
}
    