
package com.github.btd.jade.cases

import org.specs2.mutable._

class tags_self_closing_jadeSpec extends Specification {
  "tags.self-closing.jade" should {

    object tags_self_closing_html {
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
        buf("<" + "body" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + "" + ">")
        buf("</" + "foo" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + " " + "bar" + "=" + """'baz'""" + ">")
        buf("</" + "foo" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + "" + "/>")
        nl()
        buf("  ")
        buf("<" + "foo" + " " + "bar" + "=" + """'baz'""" + "/>")
        nl()
        buf("  ")
        buf("<" + "foo" + "" + ">")
        buf("""/""")
        buf("</" + "foo" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + " " + "bar" + "=" + """'baz'""" + ">")
        buf("""/""")
        buf("</" + "foo" + ">")
        nl()
        buf("")
        buf("</" + "body" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "tags.self-closing.html")).getLines.mkString("\n")
      tags_self_closing_html() === testCaseHtml
    }
  }
}
