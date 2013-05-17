
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_data_jadeSpec extends Specification {
  "attrs-data.jade" should {

    object attrs_data_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
val user = ("Tobi")

builder ++= ("")
builder ++= ("<" + "foo" + boolAttr(user).map(v => if(v) {" " + "data-user"} else "").getOrElse(falsy(user).map(v => " " + "data-user" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
builder ++= ("</" + "foo" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "bar" + " " + "data-username" + "=" + """'tobi'""" + ">")
builder ++= ("</" + "bar" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs-data.html")).getLines.mkString("\n")
      attrs_data_html() === testCaseHtml
    }
  }
}
    