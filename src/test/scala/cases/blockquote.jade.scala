
package com.github.btd.jade.cases

import org.specs2.mutable._

class blockquote_jadeSpec extends Specification {
  "blockquote.jade" should {

    object blockquote_html {
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
        buf("<" + "figure" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "blockquote" + "" + ">")
        buf("""Try to define yourself by what you do, and you&#8217;ll burnout every time. You are. That is enough. I rest in that.""")
        buf("</" + "blockquote" + ">")
        nl()
        buf("  ")
        buf("<" + "figcaption" + "" + ">")
        buf("""from @thefray at 1:43pm on May 10""")
        buf("</" + "figcaption" + ">")
        nl()
        buf("")
        buf("</" + "figure" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "blockquote.html")).getLines.mkString("\n")
      blockquote_html() === testCaseHtml
    }
  }
}
