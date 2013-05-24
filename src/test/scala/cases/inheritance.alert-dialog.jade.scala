
package com.github.btd.jade.cases

import org.specs2.mutable._

class inheritance_alert_dialog_jadeSpec extends Specification {
  "inheritance.alert-dialog.jade" should {

    object inheritance_alert_dialog_html {
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
        buf("<" + "div" + " " + "class" + "=" + """'window'""" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + " " + "class" + "=" + """'close'""" + ">")
        buf("""Close""")
        buf("</" + "a" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "class" + "=" + """'dialog'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Alert!""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""I'm an alert!""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "div" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "inheritance.alert-dialog.html")).getLines.mkString("\n")
      inheritance_alert_dialog_html() === testCaseHtml
    }
  }
}
