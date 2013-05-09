
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_jadeSpec extends Specification {
  "attrs.jade" should {

    object attrs_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "a" + " " + "href" + "=" + """'/contact'""" + ">")
builder ++= ("contact")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'/save'""" + " " + "class" + "=" + """'button'""" + ">")
builder ++= ("save")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "foo" + " " + "bar" + " " + "baz" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "foo" + "=" + """'foo, bar, baz'""" + " " + falsy(1).map(v => "bar" + "=" + "'" + v + "'").getOrElse("") + ">")
builder ++= ("</" + "a" + ">")
val bar = (if(true) "1" else "0")

builder ++= ("<" + "a" + " " + "foo" + "=" + """'((foo))'""" + " " + falsy(bar).map(v => "bar" + "=" + "'" + v + "'").getOrElse("") + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "select" + "" + ">")
builder ++= ("<" + "option" + " " + "value" + "=" + """'foo'""" + " " + "selected" + ">")
builder ++= ("Foo")
builder ++= ("</" + "option" + ">")
builder ++= ("<" + "option" + " " + "selected" + " " + "value" + "=" + """'bar'""" + ">")
builder ++= ("Bar")
builder ++= ("</" + "option" + ">")
builder ++= ("</" + "select" + ">")
builder ++= ("<" + "a" + " " + "foo" + "=" + """"class:"""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "input" + " " + "pattern" + "=" + """'\\S+'""" + ">")
builder ++= ("</" + "input" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.html")).getLines.mkString("")
      attrs_html() === testCaseHtml
    }
  }
}
    