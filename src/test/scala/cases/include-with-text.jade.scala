
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_with_text_jadeSpec extends Specification {
  "include-with-text.jade" should {

    object include_with_text_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "html" + "" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "head" + "" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        builder ++= ("""alert('hello world');""")
        builder ++= ("</" + "script" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "script" + " " + "src" + "=" + """'/caustic.js'""" + ">")
        builder ++= ("</" + "script" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        builder ++= ("</" + "script" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("</" + "head" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-with-text.html")).getLines.mkString("\n")
      include_with_text_html() === testCaseHtml
    }
  }
}
