
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_conditionals_jadeSpec extends Specification {
  "code.conditionals.jade" should {

    object code_conditionals_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        if (true) {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""foo""")
          buf("</" + "p" + ">")
        } else {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        if (true) {
          {
            nl()
            buf("")
            buf("<" + "p" + "" + ">")
            buf("""foo""")
            buf("</" + "p" + ">")
          }
        } else {
          {
            nl()
            buf("")
            buf("<" + "p" + "" + ">")
            buf("""bar""")
            buf("</" + "p" + ">")
          }
        }

        if (true) {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""foo""")
          buf("</" + "p" + ">")
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""baz""")
          buf("</" + "p" + ">")
        } else {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        if (!(true)) {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""foo""")
          buf("</" + "p" + ">")
        } else {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        if (!("nested".isEmpty)) {
          if (!("works".isEmpty)) {
            nl()
            buf("")
            buf("<" + "p" + "" + ">")
            buf("""yay""")
            buf("</" + "p" + ">")
          }
        }

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "code.conditionals.html")).getLines.mkString("\n")
      code_conditionals_html() === testCaseHtml
    }
  }
}
