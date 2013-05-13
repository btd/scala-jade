
package com.github.btd.jade.cases

import org.specs2.mutable._

class doctype_default_jadeSpec extends Specification {
  "doctype.default.jade" should {

    object doctype_default_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("""<!DOCTYPE html>""")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "html" + "" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "body" + "" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "h1" + "" + ">")
        builder ++= ("""Title""")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "doctype.default.html")).getLines.mkString("\n")
      doctype_default_html() === testCaseHtml
    }
  }
}
