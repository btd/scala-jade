
package com.github.btd.jade.cases

import org.specs2.mutable._

class vars_jadeSpec extends Specification {
  "vars.jade" should {

    object vars_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        val foo = ("bar")

        val list = ((1 :: 2 :: 3 :: Nil).mkString(" "))

        buf("<" + "a" + boolAttr(list).map(v => if (v) { " " + "class" } else "").getOrElse(falsy(list).map(v => " " + "class" + "=" + "'" + escape(v) + "'").getOrElse("")) + boolAttr(foo).map(v => if (v) { " " + "id" } else "").getOrElse(falsy(foo).map(v => " " + "id" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "vars.html")).getLines.mkString("\n")
      vars_html() === testCaseHtml
    }
  }
}
