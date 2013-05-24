
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_extend_include_jadeSpec extends Specification {
  "inheritance.extend.include.jade" should {

    object inheritance_extend_include_html {
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
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""Page""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""Some content""")
        buf("</" + "p" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "class" + "=" + """'window'""" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + " " + "class" + "=" + """'close'""" + ">")
        buf("""Close""")
        buf("</" + "a" + ">")
        nl()
        buf("      ")
        buf("<" + "h2" + "" + ">")
        buf("""Awesome""")
        buf("</" + "h2" + ">")
        nl()
        buf("      ")
        buf("<" + "p" + "" + ">")
        buf("""Now we can extend included blocks!""")
        buf("</" + "p" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.extend.include.html")).getLines.mkString("\n")
      inheritance_extend_include_html() === testCaseHtml
    }
  }
}
