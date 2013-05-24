
package com.github.btd.jade.cases

import org.specs2.mutable._

class includes_jadeSpec extends Specification {
  "includes.jade" should {

    object includes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_foo()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        jade_mixin_foo()(0, indentLevel => {
        })
        nl()
        buf("")
        buf("<" + "body" + "" + ">")
        buf("""<p>:)</p>""")
        buf("""<script>
  console.log("foo\nbar")
</script>""")
        nl()
        buf("")
        buf("</" + "body" + ">")

        builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "includes.html")).getLines.mkString("\n")
      includes_html() === testCaseHtml
    }
  }
}
