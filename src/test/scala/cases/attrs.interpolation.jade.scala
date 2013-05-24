
package com.github.btd.jade.cases

import org.specs2.mutable._

class attrs_interpolation_jadeSpec extends Specification {
  "attrs.interpolation.jade" should {

    object attrs_interpolation_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var id = 5

        buf("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((id).toString) + """'""" + ">")
        buf("</" + "a" + ">")
        nl()
        buf("")
        buf("<" + "foo" + " " + "bar" + "=" + """'stuff #{here} yup'""" + ">")
        buf("</" + "foo" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "attrs.interpolation.html")).getLines.mkString("\n")
      attrs_interpolation_html() === testCaseHtml
    }
  }
}
