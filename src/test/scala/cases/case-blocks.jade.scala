
package com.github.btd.jade.cases

import org.specs2.mutable._

class case_blocks_jadeSpec extends Specification {
  "case-blocks.jade" should {

    object case_blocks_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "html" + "" + ">")
        builder ++= ("\n")
        builder ++= ("  ")
        builder ++= ("<" + "body" + "" + ">")
        val friends = (1)

        (friends) match {
          case 0 => {
            builder ++= ("\n")
            builder ++= ("    ")
            builder ++= ("<" + "p" + "" + ">")
            builder ++= ("""you have no friends""")
            builder ++= ("</" + "p" + ">")
          }
          case 1 => {
            builder ++= ("\n")
            builder ++= ("    ")
            builder ++= ("<" + "p" + "" + ">")
            builder ++= ("""you have a friend""")
            builder ++= ("</" + "p" + ">")
          }
          case _ => {
            builder ++= ("\n")
            builder ++= ("    ")
            builder ++= ("<" + "p" + "" + ">")
            builder ++= ("""you have """ + escape((friends).toString) + """ friends""")
            builder ++= ("</" + "p" + ">")
          }
        }
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "case-blocks.html")).getLines.mkString("\n")
      case_blocks_html() === testCaseHtml
    }
  }
}
