
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_defaults_jadeSpec extends Specification {
  "inheritance.defaults.jade" should {

    object inheritance_defaults_html {
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
builder ++= ("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
builder ++= ("</" + "script" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "script" + " " + "src" + "=" + """'keymaster.js'""" + ">")
builder ++= ("</" + "script" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "script" + " " + "src" + "=" + """'caustic.js'""" + ">")
builder ++= ("</" + "script" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "head" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "html" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.defaults.html")).getLines.mkString("\n")
      inheritance_defaults_html() === testCaseHtml
    }
  }
}
    