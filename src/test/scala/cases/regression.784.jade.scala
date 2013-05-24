
package com.github.btd.jade.cases

import org.specs2.mutable._

class regression_784_jadeSpec extends Specification {
  "regression.784.jade" should {

    object regression_784_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        val url = ("http://www.google.com".replaceAll("http://", "").replaceAll("www.", ""))

        nl()
        buf("")
        buf("<" + "div" + " " + "class" + "=" + """'url'""" + ">")
        buf("""""" + escape((url).toString) + """""")
        buf("</" + "div" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "regression.784.html")).getLines.mkString("\n")
      regression_784_html() === testCaseHtml
    }
  }
}
