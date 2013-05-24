
package com.github.btd.jade.cases

import org.specs2.mutable._

class namespaces_jadeSpec extends Specification {
  "namespaces.jade" should {

    object namespaces_html {
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
        buf("<" + "fb:user:role" + "" + ">")
        buf("""Something""")
        buf("</" + "fb:user:role" + ">")
        nl()
        buf("")
        buf("<" + "foo" + " " + "fb:foo" + "=" + """'bar'""" + ">")
        buf("</" + "foo" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "namespaces.html")).getLines.mkString("\n")
      namespaces_html() === testCaseHtml
    }
  }
}
