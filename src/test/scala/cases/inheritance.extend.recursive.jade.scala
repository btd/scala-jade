
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_extend_recursive_jadeSpec extends Specification {
  "inheritance.extend.recursive.jade" should {

    object inheritance_extend_recursive_html {
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
        buf("<" + "h1" + "" + ">")
        buf("""grand-grandparent""")
        buf("</" + "h1" + ">")
        nl()
        buf("")
        buf("<" + "h2" + "" + ">")
        buf("""grandparent""")
        buf("</" + "h2" + ">")
        nl()
        buf("")
        buf("<" + "h3" + "" + ">")
        buf("""parent""")
        buf("</" + "h3" + ">")
        nl()
        buf("")
        buf("<" + "h4" + "" + ">")
        buf("""child""")
        buf("</" + "h4" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.extend.recursive.html")).getLines.mkString("\n")
      inheritance_extend_recursive_html() === testCaseHtml
    }
  }
}
