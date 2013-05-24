
package com.github.btd.jade.cases

import org.specs2.mutable._

class includes_with_ext_js_jadeSpec extends Specification {
  "includes-with-ext-js.jade" should {

    object includes_with_ext_js_html {
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
        buf("<" + "body" + "" + ">")
        buf("<script type='text/javascript'>\n" + """var x = "\n here is some \n new lined text";""" + "\n</script>")
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
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "includes-with-ext-js.html")).getLines.mkString("\n")
      includes_with_ext_js_html() === testCaseHtml
    }
  }
}
