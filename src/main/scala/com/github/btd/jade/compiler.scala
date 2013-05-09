package com.github.btd.jade

import nodes._

class Compiler(nodes: Seq[Node]) {
  implicit def es2s(es: EvalString) = es.str

  var prettyPrint = false

  val builder = new collection.mutable.StringBuilder

  builder ++= "val builder = new collection.mutable.StringBuilder\n"

  def compile() = {
    for (node <- nodes)
      visit(node)

    builder += '\n'
    builder ++= "builder.toString\n"
    builder.toString
  }

  def visit(node: Node) {
    node match {
      case Doctype(value) =>
        buf(Jade.doctypes(empty(value).getOrElse(Jade.defaultDoctype))); nl

      case Code(value, escaped, buffered, blockOpt) =>
        if (buffered) {
          buf(value); nl //TODO escape
        } else {
          builder ++= (value + "\n")
          for {
            block <- blockOpt
            n <- block
          } {
            visit(n)
          }
        }

      case Tag(name, attributes, block, selfClose, textOnly, codeOpt, buffered) =>
        val tagName = (if (buffered) i(" + (name) + ") else q(name))
        val attrs = attributes.map { attr =>
          q(attr._1) + attr._2.map(a => q("=") + (if (isQuoted(a.value)) i(attrInterpolated(a.value)) else qi(a.value))).getOrElse(nothing)
        } //TODO escape

        buf(q("<") + tagName + (if (!attrs.isEmpty) space + i(attrs.reduce(_ + space + _)) else nothing) + (if (selfClose) "/>" else ">")); nl

        if (!selfClose) {
          if (codeOpt.isDefined) {
            visit(codeOpt.get)
          } else if (!block.isEmpty) {
            for {
              n <- block
            } {
              visit(n)
            }
          }
          //close tag
          buf(q("</") + tagName + ">"); nl
        }

      case Text(value) => buf(quote(value)); nl

      case Empty => //ignore it
    }
  }

  val attrInterpolationRE = """(\\?)#\{([\w_-]+)\}""".r

  def attrInterpolated(attr: String) = {
    tQuote(attrInterpolationRE.replaceAllIn(attr, m => {
      if (m.group(1) != "\\") {
        "\"\"\" + " + m.group(2) + " + \"\"\""
      } else {
        "#{" + m.group(2) + "}"
      }
    }))
  }

  val nothing = q("")

  val space = q(" ")

  def tQuote(str: String) = "\"\"\"" + str + "\"\"\""
  def quote(str: String) = "\"" + str + "\"" // """to do not worry about " and ' inside quoted string """

  def buf(str: String) {
    builder ++= ("builder ++= (" + str + ")")
  }

  def nl {
    builder ++= "\n"
  }

  def isQuoted(str: String) = (str.startsWith("\"") && str.endsWith("\"")) || (str.startsWith("'") && str.endsWith("'"))

  def qi(s: String) = i("\"'\"") + i(s) + i("\"'\"")

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

/*
def print(args...,writer: java.io.Writer) = {
	writer.write()
}
def print(args...) = {
	val writer = new java.io.StringWriter
	print(args..., writer)
	writer.flush
	writer.toString
}
*/ 