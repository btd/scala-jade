
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_iteration_jadeSpec extends Specification {
  "code.iteration.jade" should {

    object code_iteration_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var items = 1 :: 2 :: 3 :: Nil

        builder ++= ("")
        builder ++= ("<" + "ul" + "" + ">")
        items.foreach(item => {
          builder ++= ("\n")
          builder ++= ("  ")
          builder ++= ("<" + "li" + "" + ">")
          builder ++= (falsy(item).map(v => escape(v)).getOrElse(""))
          builder ++= ("</" + "li" + ">")
        }
        )

        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "ul" + ">")
        val items2 = (1 :: 2 :: 3 :: Nil)

        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "ul" + "" + ">")
        for ((item, i) <- items2.zipWithIndex) {
          builder ++= ("\n")
          builder ++= ("  ")
          builder ++= ("<" + "li" + " " + "class" + "=" + """'item-""" + escape((i).toString) + """'""" + ">")
          builder ++= (falsy(item).map(v => escape(v)).getOrElse(""))
          builder ++= ("</" + "li" + ">")
        }
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "ul" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "ul" + "" + ">")
        for (item <- items2) {
          builder ++= ("\n")
          builder ++= ("  ")
          builder ++= ("<" + "li" + "" + ">")
          builder ++= (falsy(item).map(v => escape(v)).getOrElse(""))
          builder ++= ("</" + "li" + ">")
        }
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "ul" + ">")
        val nums = (1 to 3)

        val letters = ("a" :: "b" :: "c" :: Nil)

        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "ul" + "" + ">")
        for (l <- letters) {
          for (n <- nums) {
            builder ++= ("\n")
            builder ++= ("  ")
            builder ++= ("<" + "li" + "" + ">")
            builder ++= ("""""" + escape((n).toString) + """: """ + escape((l).toString) + """""")
            builder ++= ("</" + "li" + ">")
          }
        }
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "ul" + ">")
        val count = (0)

        val counter = (() => (1 to 3).map(_ + count))

        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "ul" + "" + ">")
        for (n <- counter()) {
          builder ++= ("\n")
          builder ++= ("  ")
          builder ++= ("<" + "li" + "" + ">")
          builder ++= ("""""" + escape((n).toString) + """""")
          builder ++= ("</" + "li" + ">")
        }
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("</" + "ul" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.iteration.html")).getLines.mkString("\n")
      code_iteration_html() === testCaseHtml
    }
  }
}
