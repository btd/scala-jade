
package com.github.btd.jade.cases

import org.specs2.mutable._

class html_jadeSpec extends Specification {
  "html.jade" should {

    object html_html {
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
        buf("""<ul>""")
        nl()
        buf("")
        buf("")
        buf("""<li>foo</li>""")
        nl()
        buf("")
        buf("")
        buf("""<li>bar</li>""")
        nl()
        buf("")
        buf("")
        buf("""<li>baz</li>""")
        nl()
        buf("")
        buf("")
        buf("""</ul>""")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf("""You can <em>embed</em> html as well.""")
        buf("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "html.html")).getLines.mkString("\n")
      html_html() === testCaseHtml
    }
  }
}
