
package com.github.btd.jade.cases

import org.specs2.mutable._

class comments_conditional_jadeSpec extends Specification {
  "comments.conditional.jade" should {

    object comments_conditional_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<!--[if IE lt 9]>")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'/lame.js'""" + ">")
        buf("</" + "script" + ">")
        buf("<![endif]-->")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "comments.conditional.html")).getLines.mkString("\n")
      comments_conditional_html() === testCaseHtml
    }
  }
}
