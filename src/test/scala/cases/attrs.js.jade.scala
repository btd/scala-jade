
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_js_jadeSpec extends Specification {
  "attrs.js.jade" should {

    object attrs_js_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var id = 5

        def answer() = 42

        buf("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((42).toString) + """'""" + " " + "class" + "=" + """'button'""" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((id).toString) + """'""" + " " + "class" + "=" + """'button'""" + ">")
        buf("</" + "a" + ">")
        val a = answer()

        nl()
        buf("")
        buf("<" + "meta" + " " + "key" + "=" + """'answer'""" + boolAttr(a).map(v => if (v) { " " + "value" } else "").getOrElse(falsy(a).map(v => " " + "value" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.js.html")).getLines.mkString("\n")
      attrs_js_html() === testCaseHtml
    }
  }
}
