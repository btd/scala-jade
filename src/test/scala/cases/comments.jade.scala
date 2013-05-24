
package com.github.btd.jade.cases

import org.specs2.mutable._

class comments_jadeSpec extends Specification {
  "comments.jade" should {

    object comments_html {
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
        buf("<!-- foo-->")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<!-- bar-->")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""one""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")
        buf("<!-- asdf")
        nl()
        buf("")
        buf("<!-- baz-->")
        nl()
        buf("")
        buf("<" + "li" + "" + ">")
        buf("""two""")
        buf("</" + "li" + ">")
        buf("-->")
        buf("<!--")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""foo""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")
        buf("-->")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "comments.html")).getLines.mkString("\n")
      comments_html() === testCaseHtml
    }
  }
}
