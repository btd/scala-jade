
package com.github.btd.jade.cases

import org.specs2.mutable._

class block_expansion_shorthands_jadeSpec extends Specification {
  "block-expansion.shorthands.jade" should {

    object block_expansion_shorthands_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + " " + "class" + "=" + """'list-item'""" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "div" + " " + "class" + "=" + """'foo'""" + ">")
builder ++= ("\n")
builder ++= ("      ")
builder ++= ("<" + "div" + " " + "id" + "=" + """'bar'""" + ">")
builder ++= ("""baz""")
builder ++= ("</" + "div" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("</" + "div" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "li" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "block-expansion.shorthands.html")).getLines.mkString("\n")
      block_expansion_shorthands_html() === testCaseHtml
    }
  }
}
    