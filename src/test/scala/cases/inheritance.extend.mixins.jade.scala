
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_extend_mixins_jadeSpec extends Specification {
  "inheritance.extend.mixins.jade" should {

    object inheritance_extend_mixins_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_article(title: String)(indentLevel: Int, block: Int => Unit) = {
          if (title != null && title != "") {
            nl()
            buf("" + ("  " * indentLevel))
            buf("<" + "h1" + "" + ">")
            buf(falsy(title).map(v => escape(v)).getOrElse(""))
            buf("</" + "h1" + ">")
          }
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_article("The meaning of life")(2, indentLevel => {
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.extend.mixins.html")).getLines.mkString("\n")
      inheritance_extend_mixins_html() === testCaseHtml
    }
  }
}
