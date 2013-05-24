
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_jadeSpec extends Specification {
  "code.jade" should {

    object code_html {
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
        buf("<" + "p" + "" + ">")
        buf(falsy(null).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(None).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy("").map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(0).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(false).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(null).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(null).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(None).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(None).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "foo" + "=" + """""""" + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(0).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(0).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(false).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(false).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.html")).getLines.mkString("\n")
      code_html() === testCaseHtml
    }
  }
}
