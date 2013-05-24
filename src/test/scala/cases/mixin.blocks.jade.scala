
package com.github.btd.jade.cases

import org.specs2.mutable._

class mixin_blocks_jadeSpec extends Specification {
  "mixin.blocks.jade" should {

    object mixin_blocks_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_form(method: String, action: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "form" + boolAttr(method).map(v => if (v) { " " + "method" } else "").getOrElse(falsy(method).map(v => " " + "method" + "=" + "'" + escape(v) + "'").getOrElse("")) + boolAttr(action).map(v => if (v) { " " + "action" } else "").getOrElse(falsy(action).map(v => " " + "action" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
          val csrf_token_from_somewhere = ("hey")

          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'hidden'""" + " " + "name" + "=" + """'_csrf'""" + boolAttr(csrf_token_from_somewhere).map(v => if (v) { " " + "value" } else "").getOrElse(falsy(csrf_token_from_somewhere).map(v => " " + "value" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")
          block(indentLevel + 1)
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "form" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_form("GET", "/search")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'query'""" + " " + "placeholder" + "=" + """'Search'""" + "/>")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'submit'""" + " " + "value" + "=" + """'Search'""" + "/>")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_form("POST", "/search")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'query'""" + " " + "placeholder" + "=" + """'Search'""" + "/>")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'submit'""" + " " + "value" + "=" + """'Search'""" + "/>")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_form("POST", "/search")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        def jade_mixin_bar()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "div" + " " + "id" + "=" + """'bar'""" + ">")
          block(indentLevel + 1)
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "div" + ">")
        }
        def jade_mixin_foo()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "div" + " " + "id" + "=" + """'foo'""" + ">")
          jade_mixin_bar()(1, indentLevel => {
            block(indentLevel + 0)
          })
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "div" + ">")
        }
        jade_mixin_foo()(0, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""one""")
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""two""")
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""three""")
          buf("</" + "p" + ">")
        })

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "mixin.blocks.html")).getLines.mkString("\n")
      mixin_blocks_html() === testCaseHtml
    }
  }
}
