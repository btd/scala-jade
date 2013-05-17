
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_jadeSpec extends Specification {
  "attrs.jade" should {

    object attrs_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "a" + " " + "href" + "=" + """'/contact'""" + ">")
builder ++= ("""contact""")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "class" + "=" + """'button'""" + " " + "href" + "=" + """'/save'""" + ">")
builder ++= ("""save""")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "foo" + " " + "bar" + " " + "baz" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "foo" + "=" + """'foo, bar, baz'""" + boolAttr(1).map(v => if(v) {" " + "bar"} else "").getOrElse(falsy(1).map(v => " " + "bar" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
builder ++= ("</" + "a" + ">")
val bar = (if(true) "1" else "0")

builder ++= ("<" + "a" + " " + "foo" + "=" + """'((foo))'""" + boolAttr(bar).map(v => if(v) {" " + "bar"} else "").getOrElse(falsy(bar).map(v => " " + "bar" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "select" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "option" + " " + "value" + "=" + """'foo'""" + " " + "selected" + ">")
builder ++= ("""Foo""")
builder ++= ("</" + "option" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "option" + " " + "selected" + " " + "value" + "=" + """'bar'""" + ">")
builder ++= ("""Bar""")
builder ++= ("</" + "option" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "select" + ">")
builder ++= ("<" + "a" + " " + "foo" + "=" + """"class:"""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "input" + " " + "pattern" + "=" + """'\S+'""" + "/>")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.html")).getLines.mkString("\n")
      attrs_html() === testCaseHtml
    }
  }
}
    