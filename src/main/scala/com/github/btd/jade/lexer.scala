package com.github.btd.jade

import java.io.Reader
import util.matching.Regex
import collection.mutable.Queue

object Tokens {
  trait Token {
    val value = ""
  }

  case class Text(override val value: String = "") extends Token

  case class Tag(name: String, selfClosing: Boolean) extends Token {
    override val value = name
  }

  case object Colon extends Token {
    override val value = ":"
  }

  case class Interpolation(override val value: String) extends Token

  case class Comment(override val value: String, buffered: Boolean) extends Token

  case class Filter(override val value: String) extends Token

  case class Doctype(override val value: String) extends Token

  case class Id(override val value: String) extends Token

  case class Class(override val value: String) extends Token

  case class Extends(override val value: String) extends Token

  case class Block(name: String, mode: String) extends Token {
    override val value = name
  }

  case object Yield extends Token {
    override val value = "yield"
  }

  case class Include(override val value: String) extends Token

  case class Case(override val value: String) extends Token

  case class When(override val value: String) extends Token

  case object Default extends Token {
    override val value = "default"
  }

  case class Code(override val value: String, escaped: Boolean = false, buffered: Boolean = false) extends Token

  case class Call(override val value: String, args: Seq[String] = Seq()) extends Token

  case class Mixin(override val value: String, args: Seq[String] = Seq()) extends Token

  case class Each(key: String, code: String) extends Token {
    override val value = "each"
  }

  case class Attrs(values: Seq[(String, Option[String], Boolean)], selfClose: Boolean) extends Token {
    override val value = ""
  }

  case object NewLine extends Token {
    override val value = "\n"
  }
  case object Outdent extends Token {
    override val value = ""
  }
  case class Indent(override val value: String) extends Token

  case object Eos extends Token {
    override val value = ""
  }
}

class Lexer(var input: String) {
  import Tokens._

  var pipeless: Boolean = false

  input = input.replaceAll("""\r\n|\r""", "\n")

  var differedTokens = Queue[Token]()
  var stashedTokens = Queue[Token]()

  var lineno = 1

  var indentStack = List[String]()

  def consume(len: Int) {
    //println("Before: " + input)
    input = input.substring(len)
    println("After: " + input)
  }

  def defer(tok: Token) {
    differedTokens.enqueue(tok)
  }

  def lookahead(n: Int) = {
    val fetch = n - stashedTokens.size
    println("lookahead " + fetch)
    for (_ <- 1 to fetch; token <- nextToken) stashedTokens.enqueue(token)
    stashedTokens(n - 1)
  }

  private def dequeue[T](q: Queue[T]): Option[T] = {
    if (q.isEmpty) None
    else Some(q.dequeue)
  }

  private val spacesRE = """^\s*""".r

  def skipSpaces() {
    spacesRE.findFirstIn(input).map { m =>
      consume(m.length)
    }
  }

  def stashed = dequeue(stashedTokens)

  def deferred = dequeue(differedTokens)

  def eos = {
    if (input.length != 0) None
    else {
      Some(indentStack.headOption match {
        case Some(indent) =>
          indentStack = indentStack.tail
          Outdent
        case _ => Eos
      })
    }
  }

  def nextToken: Option[Token] = {
    println("try to find next token: ")
    println(input.split("\n").take(2).mkString("\n"))
    deferred orElse
      blank orElse
      eos orElse
      pipelessText orElse
      _yield orElse
      doctype orElse
      interpolation orElse
      _case orElse
      when orElse
      default orElse
      extend orElse
      append orElse
      prepend orElse
      block orElse
      include orElse
      mixin orElse
      call orElse
      conditional orElse
      each orElse
      _while orElse
      assignment orElse
      tag orElse
      filter orElse
      code orElse
      id orElse
      className orElse
      attrs orElse
      indent orElse
      comment orElse
      colon orElse
      text
  }

  private val blankLineRE = """^\n\s*\n""".r

  def blank = {
    blankLineRE.findFirstIn(input).flatMap { m =>
      consume(m.length - 1) // -1 because last \n should be in input

      println("blank")

      lineno += 1
      if (pipeless) Some(Text())
      else nextToken
    }
  }

  private val commentRE = """^\s*\/\/(-)?([^\n]*)""".r

  def comment = scan2(commentRE, (buffered, value) => Comment(value, buffered != "-"))

  private val interpolationRE = """^#\{([^\n]*)\}""".r

  def interpolation = scan1(interpolationRE, Interpolation(_))

  private val tagRE = """^(\w[-:\w]*)(\/?)?""".r

  def tag = {
    tagRE.findPrefixMatchOf(input).map { m =>
      consume(m.group(0).length)

      var name = m.group(1)
      if (name.endsWith(":")) {
        name = name.substring(0, name.length - 1)

        defer(Colon)
        skipSpaces()
      }
      Tag(name, m.group(2) != "")
    }
  }

  def scan0(regex: Regex, tok: => Token) = {
    regex.findPrefixMatchOf(input).map { m =>
      consume(m.group(0).length)

      tok
    }
  }

  def scan1(regex: Regex, tok: String => Token) = {
    regex.findPrefixMatchOf(input).map { m =>
      consume(m.group(0).length)

      tok(m.group(1))
    }
  }

  def scan2(regex: Regex, tok: (String, String) => Token) = {
    regex.findPrefixMatchOf(input).map { m =>
      consume(m.group(0).length)

      tok(m.group(1), m.group(2))
    }
  }

  private val filterRE = """^:(\w+)""".r

  def filter = scan1(filterRE, Filter(_))

  private val docTypeRE = """^(?:!!!|doctype) *([^\n]+)?""".r

  def doctype = scan1(docTypeRE, Doctype(_))

  private val idRE = """^#([\w-]+)""".r

  def id = scan1(idRE, Id(_))

  private val classNameRE = """^\.([\w-]+)""".r

  def className = scan1(classNameRE, Class(_))

  private val textRE = """^(?:\| ?| ?)?([^\n]+)""".r

  def text = scan1(textRE, Text(_))

  private val extendsRE = """^extends?\s+([^\n]+)""".r

  def extend = scan1(extendsRE, Extends(_))

  private val prependRE = """^prepend +([^\n]+)""".r

  def prepend = scan1(prependRE, Block(_, "prepend"))

  private val appendRE = """^append +([^\n]+)""".r

  def append = scan1(appendRE, Block(_, "append"))

  private val blockRE = """^block\b\s*(?:(prepend|append)\s+)?([^\n]*)""".r

  def block = scan2(blockRE, (mode, value) => Block(value, if (mode == "") "replace" else mode))

  private val yieldRE = """^yield\s*""".r

  def _yield = scan0(yieldRE, Yield)

  private val includeRE = """^include +([^\n]+)""".r

  def include = scan1(includeRE, Include(_))

  private val caseRE = """^case +([^\n]+)""".r

  def _case = scan1(caseRE, Case(_))

  private val whenRE = """^when +([^:\n]+)""".r

  def when = scan1(whenRE, When(_))

  private val defaultRE = """^default *""".r

  def default = scan0(defaultRE, Default)

  private val assignmentRE = """^(\w+) += *([^;\n]+)( *;? *)""".r

  def assignment = scan2(assignmentRE, (name, value) => Code(s"val $name = ($value)"))

  private val mixinCallRE = """^\+([-\w]+) *(?:\(([^\n]+)\))?""".r

  def call = scan2(mixinCallRE, (name, args) => Call(name, (if (args == null) Array() else args.split("""\s*,\s*""")).toSeq))

  private val mixinRE = """^mixin +([-\w]+)(?: *\((.*)\))?""".r

  def mixin = scan2(mixinRE, (name, args) => Mixin(name, (if (args == null) Array() else args.split("""\s*,\s*""")).toSeq))

  private val conditionalRE = """^(if|unless|else if|else)\b([^\n]*)""".r

  def conditional = scan2(conditionalRE, (t, code) => {
    Code(t match {
      case "if" => "if(" + code + ")"
      case "unless" => "if(!(" + code + ")"
      case "else if" => "else if(" + code + ")"
      case "else" => "else"
    })
  })

  private val whileRE = """^while +([^\n]+)""".r

  def _while = scan1(whileRE, exp => Code("while(" + exp + ")"))

  private val eachRE = """^(?:- *)?(?:each|for) +(\w+) +in +([^\n]+)""".r

  def each = scan2(eachRE, (key, collection) => Each(key, collection))

  private val codeRE = """^(!?=|-)[ \t]*([^\n]+)""".r

  def code = scan2(codeRE, (flags, c) => {
    Code(c, flags.charAt(0) == '=', flags.charAt(0) == '=' || (flags.length > 1 && flags.charAt(1) == '='))
  })

  private val attrName = """[\w-_]+"""

  private val attrValue = """'[^'\n]+'|"[^"\n]+"|[\w_$]+"""

  private val attr = """(""" + attrName + """)""" + """(?:[ \t]*(!?=)[ \t]*(""" + attrValue + """))?"""

  private val attrsRE = ("""(?m)^\([ \t]*(?:""" + attr + """[ \t]*,?[ \t]*\n?[ \t]*)*\)""").r

  private val attrRE = ("""(?m)^[ \t]*,?[ \t]*""" + attr + """[ \t]*\n?[ \t]*""").r

  def attrs = {
    attrsRE.findFirstIn(input).map(newInput => {
      consume(newInput.length)

      var _input = newInput.substring(1, newInput.length - 1)

      def scan3(regex: Regex, tok: (String, String, String) => Unit) = {
        regex.findPrefixMatchOf(_input).map { m =>
          _input = _input.substring(m.group(0).length)

          tok(m.group(1), m.group(2), m.group(3))
        }
      }

      var res: Option[_] = None
      val buffer = new collection.mutable.ListBuffer[(String, Option[String], Boolean)]
      do {
        res = scan3(attrRE, (name, escaped, value) => {
          buffer += ((name, Option(value), escaped == "="))
        })
      } while (res.isDefined)

      val closed = input.length > 1 && input.charAt(0) == '/'
      if (closed) consume(1)

      lineno += newInput.filter(_ == '\n').length

      Attrs(buffer.toList, closed)
    })
  }

  private val indentRE = """(?m)^\n( *)""".r

  def indent: Option[Token] = {
    indentRE.findPrefixMatchOf(input).map { m =>
      lineno += 1

      //println("Indent input: " + input.map(c => if (c == '\n') "\\n" else c.toString))
      println("Indent match: " + m.group(0).map(c => if (c == '\n') "\\n" else c.toString))
      consume(m.group(0).length)

      val i = m.group(1)

      println("indent: <" + i + ">")

      println(indentStack)

      if (input.length > 0 && input.charAt(0) == '\n') NewLine
      else {
        //outdent
        if (indentStack.headOption.map(_.length > i.length).getOrElse(false)) {
          while (indentStack.headOption.map(_.length > i.length).getOrElse(false)) {
            println("one outdent")
            stashedTokens.enqueue(Outdent)
            indentStack = indentStack.tail
          }

          stashedTokens.dequeue
          // indent
        } else if (i != "" && indentStack.headOption.map(_.length < i.length).getOrElse(true)) {
          indentStack = i :: indentStack
          println("indent")
          Indent(i)
        } else {
          NewLine
        }
      }
    }
  }

  private var colonRE = """^: *""".r

  def colon = scan0(colonRE, Colon)

  def pipelessText = {
    if (pipeless) {
      println("PIPELESS")
      if (input.length > 0 && input.charAt(0) == '\n') None
      else {
        var i = input.indexOf('\n')
        if (i < 0) i = input.length

        var str = input.substring(0, i)
        consume(str.length)
        Some(Text(str))

      }
    } else None
  }

  def advance = {
    stashed
  }
}

class WrongIndentationException(line: String, lineno: Int) extends Exception(s"Wrong indentation at line $lineno: $line")

