package com.github.btd.jade

import nodes._

class Parser(var input: String, filename: String) {
  val lexer = new Lexer(input)

  var contexts = this :: Nil

  var extending: ?[Parser] = None

  var mixins: List[Mixin] = Nil

  var blocks: List[Node] = Nil //TODO List[List[Node]] ?

  def context(parser: ?[Parser] = None) = {
    parser.map { p =>
      contexts = p :: contexts
      p
    } orElse (contexts.headOption)
  }

  def advance() = {
    println("advance")
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

    println("<<<<< PARSING >>>>>")

    var blocks = Seq[Node]()

    var next: Token = Eos
    do {
      next = peek()
      println("Next: " + next)
      if (next == NewLine) {
        advance()
      } else {
        blocks = blocks :+ parseExpr()
      }
    } while (next != Eos)

    println("Find eos")

    extending.map { parser =>
      context(extending)
      val ast = mixins ++ parser.parse()
      context()

      ast
    } getOrElse {
      handleBlocks()
      blocks
    }
  }

  /*
  handleBlocks: function() {
    this.blocks.reverse();
    var blocksHash = {}; // blockName: block object
    for (var i in this.blocks) {
      if (!( ({}).hasOwnProperty.call(blocksHash, [this.blocks[i].name]) )) { // just safe call to blocksHash.hasOwnProperty
        blocksHash[this.blocks[i].name] = this.blocks[i];
      } else {
        switch (this.blocks[i].mode) {
          case 'append':
            blocksHash[this.blocks[i].name].nodes = blocksHash[this.blocks[i].name].nodes.concat(this.blocks[i].nodes);
            break;
          case 'prepend':
            blocksHash[this.blocks[i].name].nodes = this.blocks[i].nodes.concat(blocksHash[this.blocks[i].name].nodes);
            break;
          default:
            blocksHash[this.blocks[i].name].nodes = this.blocks[i].nodes;
        }
        this.blocks[i] = blocksHash[this.blocks[i].name];
      }
    }
  },
  */

  def handleBlocks() = {}

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
      case Yield => nodes.Yield
      case Id(name) =>
        advance()
        lexer.defer(Tag("div", false))
        lexer.defer(Id(name))
        parseExpr()
      case Class(name) =>
        advance()
        lexer.defer(Tag("div", false))
        lexer.defer(Id(name))
        parseExpr()

      case Eos => Empty
    }

  }

  def parseTag(name: String, close: Boolean) = {
    println(lexer.stashedTokens)
    advance()

    println("parse tag")

    tag(Tag(name = name, selfClose = close))
  }

  def quote(str: String, quote: String = "'") = quote + str + quote

  def unquote(str: String) = {
    if (str.startsWith("'") && str.endsWith("'") || str.startsWith("\"") && str.endsWith("\""))
      str.substring(1, str.length - 1)
    else str
  }

  def tag(_tag: Tag) = {
    var tag = _tag

    println("Fill tag: " + tag)
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
          println("Class: " + classes)
          tag = tag.copy(attributes = tag.attributes + ("class" -> Some(AttributeValue(quote((classes + " " + name).trim)))))

        case Tokens.Attrs(values, selfClose) =>
          advance()

          val attrs = values.map(t => t._1 -> t._2.map(attrValue => AttributeValue(attrValue, t._3))).toMap
          tag = tag.copy(selfClose = selfClose, attributes = tag.attributes ++ attrs)

        case other =>
          println("parse tag: found " + other)
          tagTok = false
      }

    if (peek().value == ".") {
      tag = tag.copy(textOnly = true)
      advance()
    }

    println("Tag itself: " + tag)

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
          tagAttrOpt <- tag.attributes.get(attr._1)
          tagAttr <- tagAttrOpt
          if tagAttr.value == attr._2
        } tag = tag.copy(textOnly = true)
      else
        tag = tag.copy(textOnly = true)
    }

    println("Filled tag: " + tag)

    while (peek() == Tokens.NewLine) advance()

    peek() match {
      case Tokens.Indent(indent) =>
        if (tag.textOnly) {
          lexer.pipeless = true
          tag = tag.copy(block = parseTextBlock().nodes)
          lexer.pipeless = false
        } else {
          tag = tag.copy(block = tag.block ++: block())
        }
      case _ =>
    }

    tag
  }

  def parseCode(value: String, escaped: Boolean, buffered: Boolean) = {
    println("code1: " + lexer.stashedTokens)
    advance()

    var i = 1
    while (lookahead(i) == Tokens.NewLine) i += 1
    val blocks = (lookahead(i) match {
      case Tokens.Indent(_) =>
        skip(i - 1)
        Some(block())
      case _ => None
    })
    println("code2: " + lexer.stashedTokens)
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
      case Tokens.Indent(_) => println("block comment"); BlockComment(value, block(), buffered)
      case _ => println("simple comment"); Comment(value, buffered)
    }
  }

  def parseDoctype(value: String) = {
    advance()

    println("Parse doctype")

    Doctype(value)
  }

  def parseFilter(value: String) = {
    advance()

    //TODO How in filters can be attributes??? O_o

    Filter(value, parseTextBlock().nodes)
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

  def parseExtends(value: String) = {
    val (input, filename) = Jade.getInput(value)
    val parser = new Parser(input, filename)
    parser.blocks = this.blocks.reverse
    parser.contexts = this.contexts

    this.extending = Some(parser)

    Empty
  }

  def parseBlock(value: String, mode: String) = {
    advance()

    Block(value, mode, (peek() match {
      case Tokens.Indent(_) =>
        block()
      case _ =>
        Nil
    }))
  }

  def parseInclude(value: String) = {
    advance()

    if (value.endsWith(Jade.fileExt)) {
      val (input, filename) = Jade.getInput(value)
      val parser = new Parser(input, filename)
      parser.blocks = this.blocks
      parser.mixins = this.mixins

      context(Some(parser))
      val ast = parser.parse()
      context()

      peek() match {
        case Tokens.Indent(_) =>
        //find last block or first yield
        case _ =>
      }
      NodeSeq(ast)
    } else {
      val ext = value.substring(value.lastIndexOf(".") + 1)
      Literal(Jade.filters.get(ext).map(_(Jade.getInput(value)._1)).getOrElse(value))
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

  def parseTextBlock(): NodeSeq = {
    var b = Seq[Node]()

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

    NodeSeq(b)
  }

  def block(): NodeSeq = {
    var b = Seq[Node]()
    peek() match {
      case Tokens.Indent(_) =>
        advance()

        var wasOutdent = false
        while (!wasOutdent) {
          println("block: " + peek())
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