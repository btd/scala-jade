
package com.github.btd.jade.cases

import org.specs2.mutable._

class case_jadeSpec extends Specification {
  "case.jade" should {

    object case_html extends Template {
      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "html" + "" + ">")
builder ++= ("<" + "body" + "" + ">")
val friends = (1)

(friends) match {
case 0 => {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("you have no friends")
builder ++= ("</" + "p" + ">")
}
case 1 => {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("you have a friend")
builder ++= ("</" + "p" + ">")
}
case _ => {
builder ++= ("<" + "p" + "" + ">")
builder ++= ("you have " + friends + " friends")
builder ++= ("</" + "p" + ">")
}
}
builder ++= ("</" + "body" + ">")
builder ++= ("</" + "html" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "case.html")).getLines.mkString("")
      case_html() === testCaseHtml
    }
  }
}
    