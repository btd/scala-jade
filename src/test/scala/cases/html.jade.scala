
package com.github.btd.jade.cases

import org.specs2.mutable._

class html_jadeSpec extends Specification {
  "html.jade" should {

    object html_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("")
        builder ++= ("""<ul>""")
        builder ++= ("")
        builder ++= ("""<li>foo</li>""")
        builder ++= ("")
        builder ++= ("""<li>bar</li>""")
        builder ++= ("")
        builder ++= ("""<li>baz</li>""")
        builder ++= ("""</ul>""")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= ("""You can <em>embed</em> html as well.""")
        builder ++= ("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "html.html")).getLines.mkString("\n")
      html_html() === testCaseHtml
    }
  }
}
