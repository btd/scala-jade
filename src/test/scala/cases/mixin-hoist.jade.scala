
package com.github.btd.jade.cases

import org.specs2.mutable._

class mixin_hoist_jadeSpec extends Specification {
  "mixin-hoist.jade" should {

    object mixin_hoist_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_foo(title: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "h1" + "" + ">")
          buf(falsy(title).map(v => escape(v)).getOrElse(""))
          buf("</" + "h1" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_foo("Jade")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "mixin-hoist.html")).getLines.mkString("\n")
      mixin_hoist_html() === testCaseHtml
    }
  }
}
