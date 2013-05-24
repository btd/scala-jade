
package com.github.btd.jade.cases

import org.specs2.mutable._

class classes_jadeSpec extends Specification {
  "classes.jade" should {

    object classes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<" + "a" + " " + "class" + "=" + """'foo bar'""" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "class" + "=" + """'foo-bar_baz'""" + ">")
        buf("</" + "a" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "classes.html")).getLines.mkString("\n")
      classes_html() === testCaseHtml
    }
  }
}
