
package com.github.btd.jade.cases

import org.specs2.mutable._

class mixins_jadeSpec extends Specification {
  "mixins.jade" should {

    object mixins_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_comment(title: String, str: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "div" + " " + "class" + "=" + """'comment'""" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "h2" + "" + ">")
          buf(falsy(title).map(v => escape(v)).getOrElse(""))
          buf("</" + "h2" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "p" + " " + "class" + "=" + """'body'""" + ">")
          buf(falsy(str).map(v => escape(v)).getOrElse(""))
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "div" + ">")
        }
        nl()
        buf("")
        buf("<" + "div" + " " + "id" + "=" + """'user'""" + ">")
        nl()
        buf("  ")
        buf("<" + "h1" + "" + ">")
        buf("""Tobi""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "class" + "=" + """'comments'""" + ">")
        jade_mixin_comment("This", "is regular, javascript")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "div" + ">")
        def jade_mixin_list()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "ul" + "" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "li" + "" + ">")
          buf("""foo""")
          buf("</" + "li" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "li" + "" + ">")
          buf("""bar""")
          buf("</" + "li" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "li" + "" + ">")
          buf("""baz""")
          buf("</" + "li" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "ul" + ">")
        }
        nl()
        buf("")
        buf("<" + "body" + "" + ">")
        jade_mixin_list()(1, indentLevel => {
        })
        nl()
        buf("")
        buf("</" + "body" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "mixins.html")).getLines.mkString("\n")
      mixins_html() === testCaseHtml
    }
  }
}
