
package com.github.btd.jade.cases

import org.specs2.mutable._

class layout_multi_append_prepend_block_jadeSpec extends Specification {
  "layout.multi.append.prepend.block.jade" should {

    object layout_multi_append_prepend_block_html {
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
        buf("<" + "p" + " " + "class" + "=" + """'first prepend'""" + ">")
        buf("""Last prepend must appear at top""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'first prepend'""" + ">")
        buf("""Something prepended to content""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'first append'""" + ">")
        buf("""Something appended to content""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'last append'""" + ">")
        buf("""Last append must be most last""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "div" + " " + "class" + "=" + """'content'""" + ">")
        buf("""Defined content""")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "layout.multi.append.prepend.block.html")).getLines.mkString("\n")
      layout_multi_append_prepend_block_html() === testCaseHtml
    }
  }
}
