
package com.github.btd.jade.cases

import org.specs2.mutable._

class each_else_jadeSpec extends Specification {
  "each.else.jade" should {

    object each_else_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
case class User(name: String, friends: List[String] = Nil, age: Int = 30)

val users = (List[User]())

builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
val temp$$0 = (users)
if(!(temp$$0).isEmpty) {
for(user <- temp$$0) {
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= (falsy(user.name).map(v => escape(v)).getOrElse(""))
builder ++= ("</" + "li" + ">")
}
} else {
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("""no users!""")
builder ++= ("</" + "li" + ">")
}
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")
val users2 = (User("tobi", "loki" :: Nil) :: User("loki") :: Nil)

if(!( users2.isEmpty)) {
builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
val temp$$1 = (users2)
if(!(temp$$1).isEmpty) {
for(user <- temp$$1) {
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= (falsy(user.name).map(v => escape(v)).getOrElse(""))
builder ++= ("</" + "li" + ">")
}
} else {
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("""no users!""")
builder ++= ("</" + "li" + ">")
}
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")
}
val users3 = (List[User]())

builder ++= ("\n")
builder ++= ("")
builder ++= ("<" + "ul" + "" + ">")
val temp$$2 = (users3)
if(!(temp$$2).isEmpty) {
for(user <- temp$$2) {
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= (falsy(user.name).map(v => escape(v)).getOrElse(""))
builder ++= ("</" + "li" + ">")
}
} else {
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("""no users!""")
builder ++= ("</" + "li" + ">")
}
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "ul" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "each.else.html")).getLines.mkString("\n")
      each_else_html() === testCaseHtml
    }
  }
}
    