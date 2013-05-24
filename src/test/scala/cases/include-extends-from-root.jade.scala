
package com.github.btd.jade.cases

import org.specs2.mutable._

class include_extends_from_root_jadeSpec extends Specification {
  "include-extends-from-root.jade" should {

    object include_extends_from_root_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
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
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""hello""")
        buf("</" + "h1" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "include-extends-from-root.html")).getLines.mkString("\n")
      include_extends_from_root_html() === testCaseHtml
    }
  }
}
