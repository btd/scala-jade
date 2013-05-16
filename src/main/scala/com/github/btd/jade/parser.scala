package com.github.btd.jade

import com.typesafe.scalalogging.slf4j._
import nodes._

class Parser(var input: String, filename: String) extends Logging {
  val lexer = new Lexer(input)

  var contexts = this :: Nil

  var extending: ?[Parser] = None

  var mixins: List[Mixin] = Nil

  // evaluated block values
  var parsedBlocks = Map[String, (String, Seq[Node])]()

  def context(parser: ?[Parser] = None) = {
    parser.map { p =>
      contexts = p :: contexts
      p
    } orElse (contexts.headOption)
  }

  def advance() = {
    logger.debug(filename + "|> " + "advance")
    lexer.advance
  }

  def skip(n: Int) = {
    (1 to n) foreach (_ => advance)
  }

  def peek() = lookahead(1)

  def line = lexer.lineno

  def lookahead(n: Int) = lexer.lookahead(n)

  def parse(): Seq[Node] = {
    import Tokens._

    logger.debug("Begin parsing file " + filename + " with such blocks " + parsedBlocks)

    var blocks = Seq[Node]()

    var next: Token = Eos
    do {
      next = peek()
      logger.debug(filename + "|> " + "Next: " + next)
      if (next == NewLine) {
        advance()
      } else {
        blocks = blocks :+ parseExpr()
      }
    } while (next != Eos)

    logger.debug(filename + "|> " + "Find eos")
    logger.debug(filename + "|> " + "Blocks: " + parsedBlocks)

    extending.map { parser =>

      parser.parsedBlocks = parsedBlocks

      //this one is executing with thought that we already evaluate all blocks
      context(extending)
      val ast = mixins ++ parser.parse()
      context()

      ast
    } getOrElse {
      blocks
    }
  }

  def parseExpr(): Node = {
    import Tokens._

    peek() match {
      case Tag(name, selfClose) => parseTag(name, selfClose)
      case Text(value) => parseText(value)
      case Case(value) => parseCase(value)
      case When(value) => parseWhen(value)
      case Default => parseDefault()
      case Code(value, escaped, buffered) => parseCode(value, escaped, buffered)
      case Comment(value, buffered) => parseComment(value, buffered)
      case Doctype(value) => parseDoctype(value)
      case Filter(value) => parseFilter(value)
      case Each(key, coll) => parseEach(key, coll)
      case Extends(value) => parseExtends(value)
      case Block(name, mode) => parseBlock(name, mode)
      case Include(value) => parseInclude(value)
      case Call(value, args) => parseCall(value, args)
      case Mixin(value, args) => parseMixin(value, args)
      case Interpolation(value) => parseInterpolation(value)
      case Yield =>
        advance()
        nodes.Yield
      case Id(name) =>
        advance()
        lexer.defer(Tag("div", false))
        lexer.defer(Id(name))
        parseExpr()
      case Class(name) =>
        advance()
        lexer.defer(Tag("div", false))
        lexer.defer(Class(name))
        parseExpr()

      case Eos => Empty
    }

  }

  def parseTag(name: String, close: Boolean) = {
    advance()

    logger.debug(filename + "|> " + "parse tag")

    tag(Tag(name = name, selfClose = close))
  }

  def quote(str: String) = {
    val quote = Jade.quote
    quote + str + quote
  }

  def unquote(str: String) = {
    if ((str.startsWith("'") && str.endsWith("'")) || (str.startsWith("\"") && str.endsWith("\"")))
      str.substring(1, str.length - 1)
    else str
  }

  def tag(_tag: Tag) = {
    var tag = _tag

    var textOnly = false

    var tagTok = true
    while (tagTok)
      peek() match {
        case Tokens.Id(name) =>
          advance()
          tag = tag.copy(attributes = tag.attributes + ("id" -> Some(AttributeValue(quote(name)))))

        case Tokens.Class(name) =>
          advance()
          val classes = (for {
            clazzVal <- tag.attributes.get("class")
            attrValue <- clazzVal
          } yield unquote(attrValue.value)).getOrElse("")
          tag = tag.copy(attributes = tag.attributes + ("class" -> Some(AttributeValue(quote((classes + " " + name).trim)))))

        case Tokens.Attrs(values, selfClose) =>
          advance()

          val attrs = values.map(t => t._1 -> t._2.map(attrValue => AttributeValue(attrValue, t._3))).toMap
          tag = tag.copy(selfClose = selfClose, attributes = tag.attributes ++ attrs)

        case other =>
          tagTok = false
      }

    if (peek().value == ".") {
      textOnly = true
      advance()
    }

    peek() match {
      case Tokens.Code(value, escaped, buffered) =>
        tag = tag.copy(code = Some(parseCode(value, escaped, buffered)))

      case Tokens.Text(value) =>
        tag = tag.copy(block = tag.block :+ parseText(value))

      case Tokens.Colon =>
        advance()
        tag = tag.copy(block = Seq(parseExpr()))

      case Tokens.Indent(_) | Tokens.NewLine | Tokens.Outdent | Tokens.Eos => //do nothing to process a bit later
    }

    for {
      textTag <- Jade.textOnlyTags
      if textTag._1 == tag.name
    } {
      if (textTag._2.isDefined)
        for {
          attr <- textTag._2
          tagAttr <- tag.attributes.get(attr._1).flatMap(v => v.map(_.value))
          if unquote(tagAttr) == attr._2
        } textOnly = true
      else
        textOnly = true
    }

    tag = tag.copy(selfClose = tag.selfClose || Jade.selfCloseTags.contains(tag.name))

    logger.debug(filename + "|> " + "Filled tag: " + tag)

    while (peek() == Tokens.NewLine) advance()

    peek() match {
      case Tokens.Indent(indent) =>
        if (textOnly) {
          lexer.pipeless = true
          tag = tag.copy(block = parseTextBlock())
          lexer.pipeless = false
        } else {
          tag = tag.copy(block = tag.block ++: block())
        }
      case _ =>
    }

    tag
  }

  def parseCode(value: String, escaped: Boolean, buffered: Boolean) = {
    advance()

    var i = 1
    while (lookahead(i) == Tokens.NewLine) i += 1
    val blocks = (lookahead(i) match {
      case Tokens.Indent(_) =>
        skip(i - 1)
        Some(block())
      case _ => None
    })
    Code(value, escaped, buffered, blocks)
  }

  def parseText(value: String) = {
    this.advance()

    Text(value)
  }

  def parseBlockExpansion() = {
    peek() match {
      case Tokens.Colon =>
        advance()
        parseExpr() :: Nil
      case _ => block()
    }
  }

  def parseCase(value: String) = {
    advance()

    Case(value, block())
  }

  def parseWhen(value: String) = {
    advance()

    When(value, parseBlockExpansion())
  }

  def parseDefault() = {
    advance()

    When("default", parseBlockExpansion())
  }

  def parseComment(value: String, buffered: Boolean) = {
    advance()

    peek() match {
      case Tokens.Indent(_) => BlockComment(value, block(), buffered)
      case _ => Comment(value, buffered)
    }
  }

  def parseDoctype(value: String) = {
    advance()

    Doctype(value)
  }

  def parseFilter(value: String) = {
    advance()

    //TODO How in filters can be attributes??? O_o

    Filter(value, parseTextBlock())
  }

  def parseEach(key: String, coll: String) = {
    advance()

    Each(key, coll, block(), (peek() match {
      case Tokens.Code(value, escaped, buffered) =>
        advance()
        Some(block())
      case _ => None
    }))
  }

  def fileName(path: String) = {
    if (Path.isAbsolute(path)) path
    else Path.join(Path.dirname(filename), path)
  }

  def parseExtends(value: String) = {
    advance()

    val (filename, input) = Jade.getInput(fileName(value))
    this.extending = Some(new Parser(input, filename))

    Empty
  }

  def parseBlock(value: String, mode: String) = {
    advance()

    logger.debug(filename + "|> " + "Parse block " + value + " mode " + mode)

    val nodes = peek() match {
      case Tokens.Indent(_) =>
        block()
      case _ =>
        Nil
    }

    parsedBlocks = parsedBlocks + (value -> parsedBlocks.get(value).map { pb =>
      logger.debug("Found previous block with this name: " + pb)
      mode -> (pb._1 match {
        case "append" => nodes ++ pb._2
        case "prepend" => pb._2 ++ nodes
        case "replace" => pb._2
      })
    }.getOrElse(mode -> nodes))

    NodeSeq(parsedBlocks(value)._2)
  }

  def parseInclude(value: String) = {
    advance()

    val (filename, input) = Jade.getInput(fileName(value))

    logger.debug(filename + "|> " + "Parse include: " + value + " founded " + filename)
    logger.debug(filename + "|> " + "Content " + input)

    if (filename.endsWith(Jade.fileExt)) {

      val parser = new Parser(input, filename)
      parser.parsedBlocks = parsedBlocks
      parser.mixins = this.mixins

      context(Some(parser))
      val ast = parser.parse()
      logger.debug(filename + "|> " + "Parsed such ast: " + ast)
      context()

      peek() match {
        case Tokens.Indent(_) =>
        //find last block or first yield
        case _ =>
      }
      NodeSeq(ast)
    } else {
      val ext = Path.extname(value)
      Literal(Jade.filters.get(ext).map(_(Jade.getInput(fileName(value))._1)).getOrElse(value))
    }
  }

  def parseCall(value: String, args: Seq[String]) = {
    advance()

    var node = Mixin(value, args, true)

    //TODO not sure what happen there ?? if it is a call why i need to check indent ???

    peek() match {
      case Tokens.Indent(indent) =>
        val b = block()
        node = node.copy(block = (if (b.isEmpty) None else Some(b)))
      case _ =>
    }
    node
  }

  def parseMixin(value: String, args: Seq[String]) = {
    advance()

    var node = Mixin(value, args, false)

    peek() match {
      case Tokens.Indent(indent) =>
        val b = block()
        node = node.copy(block = (if (b.isEmpty) None else Some(b)), call = false)
      case _ =>
        node = node.copy(call = true)
    }

    node
  }

  def parseTextBlock(): Seq[Text] = {
    var b = Seq[Text]()

    peek() match {
      case Tokens.Indent(spaces) =>
        advance()

        var wasOutdent = false
        while (!wasOutdent) {
          peek() match {
            case Tokens.NewLine =>
              advance()
            case Tokens.Outdent =>
              wasOutdent = true
              advance()
            case Tokens.Text(value) =>
              advance()
              b = b :+ Text(value)
            case Tokens.Indent(_) =>
              b = b ++ parseTextBlock()
          }
        }

    }
    b
  }

  def block(): NodeSeq = {
    var b = Seq[Node]()
    peek() match {
      //case Tokens.NewLine => advance()
      case Tokens.Indent(_) =>
        advance()

        var wasOutdent = false
        while (!wasOutdent) {
          peek() match {
            case Tokens.Outdent =>
              wasOutdent = true
              advance()
            case Tokens.NewLine => advance()
            case _ => b = b :+ parseExpr()
          }
        }
    }

    NodeSeq(b)
  }

  def parseInterpolation(value: String) = {
    advance()

    tag(Tag(name = value, buffered = true))
  }
}