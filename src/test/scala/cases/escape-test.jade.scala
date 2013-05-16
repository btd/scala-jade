
package com.github.btd.jade.cases

import org.specs2.mutable._

class escape_test_jadeSpec extends Specification {
  "escape-test.jade" should {

    object escape_test_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("""<!DOCTYPE html>""")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "html" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "head" + "" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "title" + "" + ">")
builder ++= ("""escape-test""")
builder ++= ("</" + "title" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "head" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "body" + "" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "textarea" + "" + ">")
var txt = """<param name="flashvars" value="a=&quot;value_a&quot;&b=&quot;value_b&quot;&c=3"/>"""

builder ++= ("""""" + escape((txt).toString) + """""")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("</" + "textarea" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "body" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "html" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "escape-test.html")).getLines.mkString("\n")
      escape_test_html() === testCaseHtml
    }
  }
}
    