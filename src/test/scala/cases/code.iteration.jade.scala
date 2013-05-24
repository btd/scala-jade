
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_iteration_jadeSpec extends Specification {
  "code.iteration.jade" should {

    object code_iteration_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var items = 1 :: 2 :: 3 :: Nil

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        items.foreach(item => {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf(falsy(item).map(v => escape(v)).getOrElse(""))
          buf("</" + "li" + ">")
        }
        )

        nl()
        buf("")
        buf("</" + "ul" + ">")
        val items2 = (1 :: 2 :: 3 :: Nil)

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for ((item, i) <- items2.zipWithIndex) {
          nl()
          buf("  ")
          buf("<" + "li" + " " + "class" + "=" + """'item-""" + escape((i).toString) + """'""" + ">")
          buf(falsy(item).map(v => escape(v)).getOrElse(""))
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for (item <- items2) {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf(falsy(item).map(v => escape(v)).getOrElse(""))
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        val nums = (1 to 3)

        val letters = ("a" :: "b" :: "c" :: Nil)

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for (l <- letters) {
          for (n <- nums) {
            nl()
            buf("  ")
            buf("<" + "li" + "" + ">")
            buf("""""" + escape((n).toString) + """: """ + escape((l).toString) + """""")
            buf("</" + "li" + ">")
          }
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        val count = (0)

        val counter = (() => (1 to 3).map(_ + count))

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for (n <- counter()) {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf("""""" + escape((n).toString) + """""")
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.iteration.html")).getLines.mkString("\n")
      code_iteration_html() === testCaseHtml
    }
  }
}
