
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_yield_nested_jadeSpec extends Specification {
  "include.yield.nested.jade" should {

    object include_yield_nested_html {
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
        builder ++= ("<" + "title" + "" + ">")
        builder ++= ("</" + "title" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("</" + "head" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "body" + "" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "h1" + "" + ">")
        builder ++= ("""Page""")
        builder ++= ("</" + "h1" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "div" + " " + "id" + "=" + """'content'""" + ">")
        builder ++= ("\n")
        builder ++= ("      ")
        builder ++= ("<" + "div" + " " + "id" + "=" + """'content-wrapper'""" + ">")
        builder ++= ("\n")
        builder ++= ("        ")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= ("""some content""")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("        ")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= ("""and some more""")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("      ")
        builder ++= ("</" + "div" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("</" + "div" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "div" + " " + "id" + "=" + """'footer'""" + ">")
        builder ++= ("\n")
        builder ++= ("      ")
        builder ++= ("<" + "stuff" + "" + ">")
        builder ++= ("</" + "stuff" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("</" + "div" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("</" + "body" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include.yield.nested.html")).getLines.mkString("\n")
      include_yield_nested_html() === testCaseHtml
    }
  }
}
