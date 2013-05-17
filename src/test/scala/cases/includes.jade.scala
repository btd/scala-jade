
package com.github.btd.jade.cases

import org.specs2.mutable._

class includes_jadeSpec extends Specification {
  "includes.jade" should {

    object includes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
def jade_mixin_foo() {
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "p" + ">")
}
jade_mixin_foo()
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "body" + "" + ">")
builder ++= ("""<p>:)</p>""")
builder ++= ("""<script>
  console.log("foo\nbar")
</script>""")
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "body" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "includes.html")).getLines.mkString("\n")
      includes_html() === testCaseHtml
    }
  }
}
    