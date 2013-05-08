package com.github.btd.jade

import org.specs2.mutable._

import nodes._

class ParserSpec extends Specification {

  "Parser" should {
    "parse tags" in {
      val parser = new Parser("div.class1#id.class2(data-id = 'some' checked123) Text", "none")
      parser.parse() === List(
        Tag("div",
          Map(
            "class" -> Some(AttributeValue("'class1 class2'", true)),
            "id" -> Some(AttributeValue("'id'", true)),
            "data-id" -> Some(AttributeValue("'some'", true)),
            "checked123" -> None
          ),
          List(Text("Text")),
          false,
          false,
          None
        )
      )
    }
  }
}