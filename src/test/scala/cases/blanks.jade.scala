
package com.github.btd.jade.cases

import org.specs2.mutable._

class blanks_jadeSpec extends Specification {
  "blanks.jade" should {

    object blanks_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "ul" + "" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "li" + "" + ">")
        builder ++= ("""foo""")
        builder ++= ("</" + "li" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "li" + "" + ">")
        builder ++= ("""bar""")
        builder ++= ("</" + "li" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "li" + "" + ">")
        builder ++= ("""baz""")
        builder ++= ("</" + "li" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "ul" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "blanks.html")).getLines.mkString("\n")
      blanks_html() === testCaseHtml
    }
  }
}
