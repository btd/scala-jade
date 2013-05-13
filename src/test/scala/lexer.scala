package com.github.btd.jade

import org.specs2.mutable._

class LexerSpec extends Specification {

  import Tokens._

  //args(skipAll = true)

  "Lexer" should {
    "find comments" in {
      val lexer1 = new Lexer("   //buffered comment")
      lexer1.comment === Some(Comment("buffered comment", true))

      val lexer2 = new Lexer("//-not buffered comment")
      lexer2.comment === Some(Comment("not buffered comment", false))
    }
    "find tags" in {
      val lexer1 = new Lexer("some")
      lexer1.tag === Some(Tag("some", false))

      val lexer2 = new Lexer("xml:some")
      lexer2.tag === Some(Tag("xml:some", false))

      val lexer3 = new Lexer("xml:some/")
      lexer3.tag === Some(Tag("xml:some", true))

      val lexer4 = new Lexer("xml:some:")
      lexer4.tag === Some(Tag("xml:some", false))
    }

    "find attributes" in {
      val lexer = new Lexer("""(attr1='value/#{something}'
        attr2!="Value" attr3  attr4  = var )""")

      lexer.attrs === Some(Attrs(Seq(
        ("attr1", Some("'value/#{something}'"), true),
        ("attr2", Some("\"Value\""), false),
        ("attr3", None, false),
        ("attr4", Some("var"), true)
      ), false))

      val lexer2 = new Lexer("""(data-id = 'some') Text""")
      lexer2.attrs === Some(Attrs(Seq(
        ("data-id", Some("'some'"), true)
      ), false))
    }
  }
}