
package com.github.btd.jade.cases

import org.specs2.mutable._

class mixin_block_tag_behaviour_jadeSpec extends Specification {
  "mixin.block-tag-behaviour.jade" should {

    object mixin_block_tag_behaviour_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_article1(name: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "section" + " " + "class" + "=" + """'article'""" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "h1" + "" + ">")
          buf(falsy(name).map(v => escape(v)).getOrElse(""))
          buf("</" + "h1" + ">")
          block(indentLevel + 1)
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "section" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_article1("Foo")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""I'm article foo""")
          buf("</" + "p" + ">")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        def jade_mixin_article2(name: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "section" + " " + "class" + "=" + """'article'""" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "h1" + "" + ">")
          buf(falsy(name).map(v => escape(v)).getOrElse(""))
          buf("</" + "h1" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          block(indentLevel + 2)
          nl()
          buf("  " + ("  " * indentLevel))
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "section" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_article2("Something")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("""I'm a much longer""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""text-only article,""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""but you can still""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""inline html tags""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""in me if you want.""")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "mixin.block-tag-behaviour.html")).getLines.mkString("\n")
      mixin_block_tag_behaviour_html() === testCaseHtml
    }
  }
}
