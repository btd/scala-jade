
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_conditionals_jadeSpec extends Specification {
  "code.conditionals.jade" should {

    object code_conditionals_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
if (true) {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("foo")
builder ++= ("</" + "p" + ">")
}
else {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "p" + ">")
}
if (true) { {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("foo")
builder ++= ("</" + "p" + ">")
}
} else { {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "p" + ">")
}
}

if( true) {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("foo")
builder ++= ("</" + "p" + ">")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "p" + ">")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("baz")
builder ++= ("</" + "p" + ">")
}
else {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "p" + ">")
}
if(!( true) {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("foo")
builder ++= ("</" + "p" + ">")
}
else {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "p" + ">")
}
if( 'nested') {
if( 'works') {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("yay")
builder ++= ("</" + "p" + ">")
}
}

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.conditionals.html")).getLines.mkString("")
      code_conditionals_html() === testCaseHtml
    }
  }
}
    