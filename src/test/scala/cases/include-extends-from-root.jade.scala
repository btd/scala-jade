
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_extends_from_root_jadeSpec extends Specification {
  "include-extends-from-root.jade" should {

    object include_extends_from_root_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "html" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "head" + "" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "title" + "" + ">")
builder ++= ("""My Application""")
builder ++= ("</" + "title" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "head" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "body" + "" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "h1" + "" + ">")
builder ++= ("""hello""")
builder ++= ("</" + "h1" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-extends-from-root.html")).getLines.mkString("\n")
      include_extends_from_root_html() === testCaseHtml
    }
  }
}
    