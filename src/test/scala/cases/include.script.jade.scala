
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_script_jadeSpec extends Specification {
  "include.script.jade" should {

    object include_script_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "script" + " " + "id" + "=" + """'pet-template'""" + " " + "type" + "=" + """'text/x-template'""" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "div" + " " + "class" + "=" + """'pet'""" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "h1" + "" + ">")
        builder ++= ("""{{name}}""")
        builder ++= ("</" + "h1" + ">")
        builder ++= ("\n")
        builder ++= ("    ")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= ("""{{name}} is a {{species}} that is {{age}} old""")
        builder ++= ("</" + "p" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include.script.html")).getLines.mkString("\n")
      include_script_html() === testCaseHtml
    }
  }
}
