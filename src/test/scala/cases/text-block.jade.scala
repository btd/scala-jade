
package com.github.btd.jade.cases

import org.specs2.mutable._

class text_block_jadeSpec extends Specification {
  "text-block.jade" should {

    object text_block_html {
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
        buf("<" + "label" + "" + ">")
        buf("""Username:""")
        nl()
        buf("  ")
        buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'user[name]'""" + "/>")
        nl()
        buf("")
        buf("</" + "label" + ">")
        nl()
        buf("")
        buf("<" + "label" + "" + ">")
        buf("""Password:""")
        nl()
        buf("  ")
        buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'user[pass]'""" + "/>")
        nl()
        buf("")
        buf("</" + "label" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "text-block.html")).getLines.mkString("\n")
      text_block_html() === testCaseHtml
    }
  }
}
