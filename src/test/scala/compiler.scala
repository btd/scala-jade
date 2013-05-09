package com.github.btd.jade

import org.specs2.mutable._

import nodes._

class CompilerSpec extends Specification {

  val tpl = new Template {}

  "Compiler" should {
    "recognize false values" in {
      tpl.falsy(null) === None
      tpl.falsy(Nil) === None
      tpl.falsy(Seq()) === None
      tpl.falsy(Seq("a")) !== None
      tpl.falsy(Map("a" -> "a")) !== None
      tpl.falsy(Some("a")) !== None
      tpl.falsy(None) === None
    }
  }
}

