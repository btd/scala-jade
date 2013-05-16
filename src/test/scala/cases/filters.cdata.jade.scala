
package com.github.btd.jade.cases

import org.specs2.mutable._

class filters_cdata_jadeSpec extends Specification {
  "filters.cdata.jade" should {

    object filters_cdata_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
case class User(name: String, age: Int)

val users = (User("tobi",  2 ) :: Nil)

builder ++= ("")
builder ++= ("<" + "fb:users" + "" + ">")
for(user <- users) {
val age = (user.age)

builder ++= ("\n")
builder ++= ("  ")
builder ++= ("<" + "fb:user" + boolAttr(age).map(v => if(v) {" " + "age"} else "").getOrElse(falsy(age).map(v => " " + "age" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
val name = (user.name)

builder ++= ("<![CDATA[\n" + """""" + escape((name).toString) + """"""+ "\n]]>")
builder ++= ("\n")
builder ++= ("  ")
builder ++= ("</" + "fb:user" + ">")
}
builder ++= ("\n")
builder ++= ("")
builder ++= ("</" + "fb:users" + ">")

builder.toString

      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("./jade/test/cases", "filters.cdata.html")).getLines.mkString("\n")
      filters_cdata_html() === testCaseHtml
    }
  }
}
    