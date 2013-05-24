
package com.github.btd.jade.cases

import org.specs2.mutable._

class yield_before_conditional_head_jadeSpec extends Specification {
  "yield-before-conditional-head.jade" should {

    object yield_before_conditional_head_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "head" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        if (false) {
          nl()
          buf("  ")
          buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
          buf("</" + "script" + ">")
        }
        nl()
        buf("")
        buf("</" + "head" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "yield-before-conditional-head.html")).getLines.mkString("\n")
      yield_before_conditional_head_html() === testCaseHtml
    }
  }
}
