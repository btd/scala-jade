
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_extend_jadeSpec extends Specification {
  "inheritance.extend.jade" should {

    object inheritance_extend_html {
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
builder ++= ("    ")
builder ++= ("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
builder ++= ("</" + "script" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "head" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "body" + "" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "h2" + "" + ">")
builder ++= ("""Page""")
builder ++= ("</" + "h2" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""Some content""")
builder ++= ("</" + "p" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.extend.html")).getLines.mkString("\n")
      inheritance_extend_html() === testCaseHtml
    }
  }
}
    