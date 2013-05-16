
package com.github.btd.jade.cases

import org.specs2.mutable._

class block_expansion_jadeSpec extends Specification {
  "block-expansion.jade" should {

    object block_expansion_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
builder ++= ("""foo""")
builder ++= ("</" + "a" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "a" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""baz""")
builder ++= ("</" + "p" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "block-expansion.html")).getLines.mkString("\n")
      block_expansion_html() === testCaseHtml
    }
  }
}
    