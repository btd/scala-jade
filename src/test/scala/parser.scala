package com.github.btd.jade

import org.specs2.mutable._

import nodes._

class ParserSpec extends Specification {

  "Parser" should {
    "parse tags" in {
      val parser = new Parser("div.class1#id.class2(data-id = 'some' checked123) Text")
      parser.parse().filter(_ != Empty) === List(
        Tag("div",
          Map(
            "class" -> Some(AttributeValue("'class1 class2'", true)),
            "id" -> Some(AttributeValue("'id'", true)),
            "data-id" -> Some(AttributeValue("'some'", true)),
            "checked123" -> None
          ),
          List(Text("Text"))
        )
      )
    }

    "parse block expansion without indentation" in {
      val parser = new Parser("li: a(href='#') foo")
      parser.parse().filter(_ != Empty) === List(tok("li", Map(), tok("a", Map("href" -> "'#'"), Text("foo"))))
    }

    "parse block expansion with indentation" in {
      val parser = new Parser("ul\n  li: a(href='#') foo")
      parser.parse().filter(_ != Empty) === List(
        tok("ul", Map(), tok("li", Map(), tok("a", Map("href" -> "'#'"), Text("foo"))))
      )
    }

    "options is not inline tags" in {}
  }

  def tok(name: String, attrs: Map[String, String], tags: Node*) = {
    Tag(name = name, attributes = attrs.mapValues(v => Some(AttributeValue(v, true))), block = tags.toSeq)
  }
}