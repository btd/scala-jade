package com.github.btd.jade

import org.specs2.mutable._

import nodes._

class CompilerSpec extends Specification {

  import Template._

  "Compiler" should {
    "recognize false values" in {
      falsy(null) === None
      falsy(Nil) === None
      falsy(Seq()) === None
      falsy(Seq("a")) !== None
      falsy(Map("a" -> "a")) !== None
      falsy(Some("a")) !== None
      falsy(None) === None
    }
  }
}

