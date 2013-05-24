package com.github.btd.jade

import nodes._

class Compiler(nodes: Seq[Node], prettyPrint: Boolean = false) {
  implicit def es2s(es: EvalString) = es.str

  var firstLine = true

  val builder = new collection.mutable.StringBuilder

  builder ++= "val builder = new collection.mutable.StringBuilder\n"
  if (prettyPrint) {
    builder ++= """var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if(!firstLine) buf("\n")
        """
  }

  def compile() = {
    visitBlock(nodes, -1)

    builder += '\n'
    builder ++= "builder.toString\n"
    builder.toString
  }

  def visit(node: Node, indentLevel: Int = 0, insideMixin: Boolean) {

    node match {
      case Doctype(value) =>
        buf(tQuote(Jade.doctypes(empty(value).getOrElse(Jade.defaultDoctype))))
      //indent(0, true)

      case Code(value, escaped, buffered, blockOpt) =>
        if (buffered) {
          val newValue = "falsy(" + value + ").map(v => " + (if (escaped) "escape(v)" else "v") + ").getOrElse(\"\")"
          buf(newValue)
        } else {
          builder ++= (value + blockOpt.map(_ => " {\n").getOrElse("\n"))
          for {
            block <- blockOpt
            n <- block
          } {
            visit(n, indentLevel, insideMixin)
          }
          builder ++= (blockOpt.map(_ => "}\n").getOrElse("\n"))
        }

      case t @ Tag(name, attributes, block, code, selfClose, buffered) =>
        val tagName = (if (buffered) i(" + (name) + ") else q(name))
        val attrs = attributes.map { attr =>
          attr._2.map { a =>
            if (isQuoted(a.value))
              space + q(attr._1) + q("=") + i(interpolated(a.value, a.escape))
            else
              i("boolAttr(" + a.value + ").map(v => if(v) {" + (space + q(attr._1)) + "} else \"\").getOrElse(falsy(" + a.value + ").map(v => " + (space + q(attr._1) + q("=") + qi((if (a.escape) "escape(v)" else "v"))) + ").getOrElse(\"\"))")
          }.getOrElse(space + q(attr._1))
        }

        val neverPrettyPrint = Jade.neverPrettyPrint.contains(name)

        if (prettyPrint && !t.isInline) indent(indentLevel, true, insideMixin)

        buf(q("<") + tagName + (if (!attrs.isEmpty) i(attrs.reduce(_ + _)) else nothing) + (if (selfClose) "/>" else ">"))

        if (!selfClose) {
          code.foreach(visit(_, indentLevel, insideMixin))
          visitBlock(block, indentLevel, insideMixin, neverPrettyPrint)
          if (prettyPrint && !neverPrettyPrint && !t.isInline && !t.canInline) indent(indentLevel, true, insideMixin)
          buf(q("</") + tagName + ">")
        }

      case t @ Text(value) =>
        buf(textTokenValue(t))

      case Case(value, block) =>
        builder ++= ("(" + value + ") match {\n")
        visitBlock(block, indentLevel - 1)
        builder ++= ("}\n")

      case When(value, block) =>
        builder ++= ("case " + (if (value == "default") "_" else value) + " => {\n")
        visitBlock(block, indentLevel - 1)
        builder ++= ("}\n")

      case Each(key, coll, block, alt) =>
        alt match {
          case Some(a) =>
            val name = uniqueName
            builder ++= ("val " + name + " = (" + coll + ")\n")
            builder ++= ("if(!(" + name + ").isEmpty) {\n")
            builder ++= ("for(" + key + " <- " + name + ") {\n")
            visitBlock(block, indentLevel - 1)
            builder ++= ("}\n")
            builder ++= ("} else {\n")
            visitBlock(a, indentLevel - 1)
            builder ++= ("}\n")
          case _ =>
            builder ++= ("for(" + key + " <- " + coll + ") {\n")
            visitBlock(block, indentLevel - 1)
            builder ++= ("}\n")
        }

      case BlockComment(value, block, buffered) =>
        if (buffered) {
          val ieCond = value.startsWith("if ")
          buf(quote("<!--" + (if (ieCond) "[" else "") + value + (if (ieCond) "]>" else "")))
          visitBlock(block, indentLevel - 1)
          buf(quote((if (ieCond) "<![endif]" else "") + "-->"))
        }

      case Comment(value, buffered) =>
        if (buffered) {
          if (prettyPrint) indent(indentLevel, true, insideMixin)
          buf(quote("<!--" + value + "-->"))
        }

      case Filter(name, block) =>
        buf(Jade.filters(name)(block.map(textTokenValue).mkString("\n")))

      case NodeSeq(nodes) =>
        visitBlock(nodes, indentLevel - 1)

      case Literal(text) =>
        buf(text)

      case Mixin(name, args, isCall, block) =>
        if (isCall) {
          builder ++= ("jade_mixin_" + name + args.mkString("(", ", ", ") (" + indentLevel + ", indentLevel => {\n"))
          visitBlock(block, -1, true)
          builder ++= ("})\n")
        } else {
          builder ++= ("def jade_mixin_" + name + args.mkString("(", ", ", ")(indentLevel: Int, block: Int => Unit) = {\n"))
          visitBlock(block, indentLevel - 1, true)
          builder ++= ("}\n")
        }

      case MixinBlock =>
        builder ++= ("block(indentLevel + " + indentLevel + ")\n")

      case Empty => //ignore it
    }
  }

  var nameGen = -1

  def uniqueName = {
    nameGen += 1
    "temp$$" + nameGen
  }

  def textTokenValue(tok: Text) = interpolated(tok.value, false)

  def visitBlock(nodes: Seq[Node], indentLevel: Int = 0, insideMixin: Boolean = false, escape: Boolean = false) {
    val nodesCount = nodes.size

    if (prettyPrint && nodesCount > 1 && !escape && Node.isText(nodes(0)) && Node.isText(nodes(1))) {
      indent(indentLevel + 1, true, insideMixin)
    }

    for (i <- 0 until nodesCount) {
      if (prettyPrint && i > 0 && !escape && Node.isText(nodes(i - 1)) && Node.isText(nodes(i)))
        indent(indentLevel + 1, false, insideMixin)

      visit(nodes(i), indentLevel + 1, insideMixin)

      if (prettyPrint && i + 1 < nodesCount && Node.isText(nodes(i)) && Node.isText(nodes(i + 1)))
        indent(0, true, false) //to force do not add indentation but just add new line
    }

  }

  val interpolationRE = """(\\?)#\{([\w_-]+)\}""".r

  def interpolated(_text: String, textEscaped: Boolean = true) = {
    import Template._

    var q = ""
    val text = (if (isQuoted(_text)) { q = _text(0).toString; _text.substring(1, _text.length - 1) } else _text)

    val buffer = new collection.mutable.StringBuilder(_text.length)

    def bufText(str: String) {
      buffer ++= (if (textEscaped) escape(str) else str)
    }

    var lastEnd = 0
    for (m <- interpolationRE.findAllMatchIn(text)) {
      bufText(text.substring(lastEnd, m.start))
      val in = m.group(2)
      if (m.group(1) != "\\") {
        buffer ++= tQuote(" + escape((" + in + ").toString) + ")
      } else {
        bufText("#{" + in + "}")
      }
      lastEnd = m.end
    }
    bufText(text.substring(lastEnd))
    tQuote(q + buffer.toString + q)
  }

  val nothing = q("")

  val space = q(" ")

  def tQuote(str: String) = "\"\"\"" + str + "\"\"\""
  def quote(str: String) = "\"" + str + "\"" // """to do not worry about " and ' inside quoted string """

  def buf(str: String) {
    if (prettyPrint) {
      builder ++= ("buf(" + str + ")\n")
    } else {
      builder ++= ("builder ++= (" + str + ")\n")
    }
  }

  def indent(offset: Int, newline: Boolean = false, insideMixin: Boolean = false) = {

    if (newline)
      if (prettyPrint)
        builder ++= "nl()\n"
      else
        buf("\n")

    val tab = if (insideMixin) quote(Jade.tab * offset) + " + (" + quote(Jade.tab) + " * indentLevel)" else quote(Jade.tab * offset)
    buf(tab)
  }

  def isQuoted(str: String) = str.length > 1 && ((str.startsWith("\"") && str.endsWith("\"")) || (str.startsWith("'") && str.endsWith("'")))

  def qi(s: String) = {
    val quote = Jade.quote
    i("\"" + quote + "\"") + i(s) + i("\"" + quote + "\"")
  }

  //construct evaluated string
  def q(s: String) = new EvalString(quote(s))

  //construct evaluated string with unquoted value
  def i(s: String) = new EvalString(s)

  class EvalString(val str: String) {

    def +(s: String) = new EvalString(str + " + " + quote(s))

    def +(es: EvalString) = new EvalString(str + " + " + es.str)

    override def toString = str
  }
}

object Template {
  def falsy(any: Any): Option[String] = any match {
    case null => None
    case None => None
    case c: collection.GenTraversable[_] if c.isEmpty => None
    case Array() => None
    case other => Some(other.toString)
  }

  def boolAttr(any: Any): Option[Boolean] = any match {
    case b: Boolean => Some(b)
    case _ => None
  }

  def charReplacement(c: Char) = c match {
    case '<' => "&lt;"
    case '>' => "&gt;"
    case '&' => "&amp;"
    //case '/' => "&#47;"
    case '\'' => "&#39;"
    case '"' => "&quot;"
    case _ => c.toString
  }

  def escape(where: String) = {
    var buffer = new collection.mutable.StringBuilder(where.length)
    for (c <- where) {
      buffer ++= charReplacement(c)
    }
    buffer.toString
  }
}