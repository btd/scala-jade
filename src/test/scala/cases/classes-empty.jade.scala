
package com.github.btd.jade.cases

import org.specs2.mutable._

class classes_empty_jadeSpec extends Specification {
  "classes-empty.jade" should {

    object classes_empty_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<" + "a" + " " + "class" + "=" + """""""" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + boolAttr(null).map(v => if (v) { " " + "class" } else "").getOrElse(falsy(null).map(v => " " + "class" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")
        val n = (None)

        buf("<" + "a" + boolAttr(n).map(v => if (v) { " " + "class" } else "").getOrElse(falsy(n).map(v => " " + "class" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "classes-empty.html")).getLines.mkString("\n")
      classes_empty_html() === testCaseHtml
    }
  }
}
