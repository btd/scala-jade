
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_jadeSpec extends Specification {
  "attrs.jade" should {

    object attrs_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<" + "a" + " " + "href" + "=" + """'/contact'""" + ">")
        buf("""contact""")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "class" + "=" + """'button'""" + " " + "href" + "=" + """'/save'""" + ">")
        buf("""save""")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "foo" + " " + "bar" + " " + "baz" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "foo" + "=" + """'foo, bar, baz'""" + boolAttr(1).map(v => if (v) { " " + "bar" } else "").getOrElse(falsy(1).map(v => " " + "bar" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")
        val bar = (if (true) "1" else "0")

        buf("<" + "a" + " " + "foo" + "=" + """'((foo))'""" + boolAttr(bar).map(v => if (v) { " " + "bar" } else "").getOrElse(falsy(bar).map(v => " " + "bar" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")
        nl()
        buf("")
        buf("<" + "select" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "option" + " " + "value" + "=" + """'foo'""" + " " + "selected" + ">")
        buf("""Foo""")
        buf("</" + "option" + ">")
        nl()
        buf("  ")
        buf("<" + "option" + " " + "selected" + " " + "value" + "=" + """'bar'""" + ">")
        buf("""Bar""")
        buf("</" + "option" + ">")
        nl()
        buf("")
        buf("</" + "select" + ">")
        buf("<" + "a" + " " + "foo" + "=" + """"class:"""" + ">")
        buf("</" + "a" + ">")
        nl()
        buf("")
        buf("<" + "input" + " " + "pattern" + "=" + """'\S+'""" + "/>")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.html")).getLines.mkString("\n")
      attrs_html() === testCaseHtml
    }
  }
}
