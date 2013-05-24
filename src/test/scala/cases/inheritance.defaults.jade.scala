
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_defaults_jadeSpec extends Specification {
  "inheritance.defaults.jade" should {

    object inheritance_defaults_html {
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
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'keymaster.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.defaults.html")).getLines.mkString("\n")
      inheritance_defaults_html() === testCaseHtml
    }
  }
}
