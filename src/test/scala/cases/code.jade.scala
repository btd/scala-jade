
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_jadeSpec extends Specification {
  "code.jade" should {

    object code_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy(null).map(v => escape(v)).getOrElse(""))
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy(None).map(v => escape(v)).getOrElse(""))
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy("").map(v => escape(v)).getOrElse(""))
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy(0).map(v => escape(v)).getOrElse(""))
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + "" + ">")
        builder ++= (falsy(false).map(v => escape(v)).getOrElse(""))
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + boolAttr(null).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(null).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + boolAttr(None).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(None).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + " " + "foo" + "=" + """""""" + ">")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + boolAttr(0).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(0).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        builder ++= ("</" + "p" + ">")
        builder ++= ("\n")
        builder ++= ("")
        builder ++= ("<" + "p" + boolAttr(false).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(false).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        builder ++= ("</" + "p" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.html")).getLines.mkString("\n")
      code_html() === testCaseHtml
    }
  }
}
