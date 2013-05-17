
package com.github.btd.jade.cases

import org.specs2.mutable._

class html5_jadeSpec extends Specification {
  "html5.jade" should {

    object html5_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("""<!DOCTYPE html>""")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "input" + " " + "type" + "=" + """'checkbox'""" + " " + "checked" + "/>")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "input" + " " + "type" + "=" + """'checkbox'""" + boolAttr(true).map(v => if(v) {" " + "checked"} else "").getOrElse(falsy(true).map(v => " " + "checked" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "input" + " " + "type" + "=" + """'checkbox'""" + boolAttr(false).map(v => if(v) {" " + "checked"} else "").getOrElse(falsy(false).map(v => " " + "checked" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "html5.html")).getLines.mkString("\n")
      html5_html() === testCaseHtml
    }
  }
}
    