
package com.github.btd.jade.cases

import org.specs2.mutable._

class code_conditionals_jadeSpec extends Specification {
  "code.conditionals.jade" should {

    object code_conditionals_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
if (true) {
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""foo""")
builder ++= ("</" + "p" + ">")
}
else {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "p" + ">")
}
if (true) { {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""foo""")
builder ++= ("</" + "p" + ">")
}
} else { {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "p" + ">")
}
}

if( true) {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""foo""")
builder ++= ("</" + "p" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "p" + ">")
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""baz""")
builder ++= ("</" + "p" + ">")
}
else {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "p" + ">")
}
if(!( true)) {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""foo""")
builder ++= ("</" + "p" + ">")
}
else {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""bar""")
builder ++= ("</" + "p" + ">")
}
if(!( "nested".isEmpty)) {
if(!( "works".isEmpty)) {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("""yay""")
builder ++= ("</" + "p" + ">")
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
    