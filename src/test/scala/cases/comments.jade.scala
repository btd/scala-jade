
package com.github.btd.jade.cases

import org.specs2.mutable._

class comments_jadeSpec extends Specification {
  "comments.jade" should {

    object comments_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<!-- foo-->")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<!-- bar-->")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("""one""")
builder ++= ("</" + "li" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")
builder ++= ("<!-- asdf")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<!-- baz-->")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("""two""")
builder ++= ("</" + "li" + ">")
builder ++= ("-->")
builder ++= ("<!--")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("""foo""")
builder ++= ("</" + "li" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")
builder ++= ("-->")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "comments.html")).getLines.mkString("\n")
      comments_html() === testCaseHtml
    }
  }
}
    