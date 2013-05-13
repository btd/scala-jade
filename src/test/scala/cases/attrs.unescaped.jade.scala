
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_unescaped_jadeSpec extends Specification {
  "attrs.unescaped.jade" should {

    object attrs_unescaped_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "div" + " " + "id" + "=" + """'user-<%= user.id %>'""" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "h1" + "" + ">")
        builder ++= ("""<%= user.title %>""")
        builder ++= ("</" + "h1" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("</" + "div" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "script" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.unescaped.html")).getLines.mkString("\n")
      attrs_unescaped_html() === testCaseHtml
    }
  }
}
