
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_alert_dialog_jadeSpec extends Specification {
  "inheritance.alert-dialog.jade" should {

    object inheritance_alert_dialog_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "div" + " " + "class" + "=" + """'window'""" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + " " + "class" + "=" + """'close'""" + ">")
builder ++= ("""Close""")
builder ++= ("</" + "a" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "div" + " " + "class" + "=" + """'dialog'""" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "h1" + "" + ">")
builder ++= ("""Alert!""")
builder ++= ("</" + "h1" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""I'm an alert!""")
builder ++= ("</" + "p" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "div" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "div" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.alert-dialog.html")).getLines.mkString("\n")
      inheritance_alert_dialog_html() === testCaseHtml
    }
  }
}
    