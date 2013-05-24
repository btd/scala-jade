
package com.github.btd.jade.cases

import org.specs2.mutable._

class case_blocks_jadeSpec extends Specification {
  "case-blocks.jade" should {

    object case_blocks_html {
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
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        val friends = (1)

        (friends) match {
          case 0 => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have no friends""")
            buf("</" + "p" + ">")
          }
          case 1 => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have a friend""")
            buf("</" + "p" + ">")
          }
          case _ => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have """ + escape((friends).toString) + """ friends""")
            buf("</" + "p" + ">")
          }
        }
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "case-blocks.html")).getLines.mkString("\n")
      case_blocks_html() === testCaseHtml
    }
  }
}
