
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_extend_mixins_jadeSpec extends Specification {
  "inheritance.extend.mixins.jade" should {

    object inheritance_extend_mixins_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
builder ++= ("")
builder ++= ("<" + "html" + "" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "head" + "" + ">")
builder ++= ("\n")
builder ++= ("    ")
builder ++= ("<" + "title" + "" + ">")
builder ++= ("""My Application""")
builder ++= ("</" + "title" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "head" + ">")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "body" + "" + ">")
jade_mixin_article("The meaning of life")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "body" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "html" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.extend.mixins.html")).getLines.mkString("\n")
      inheritance_extend_mixins_html() === testCaseHtml
    }
  }
}
    