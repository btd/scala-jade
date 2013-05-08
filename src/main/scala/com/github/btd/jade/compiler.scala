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
        val tagName = (if (buffered) i(" + (name) + ") else e(name))
        val attrs = attributes.map { attr =>
          e(attr._1) + attr._2.map(a => e("=") + (if (isQuoted(a.value)) e(a.value) else qi(a.value))).getOrElse(i(""))
        }.mkString(" ", " ", "") //TODO escape

        buf(e("<") + tagName + i(attrs) + (if (selfClose) "/>" else ">")); nl

        if (!selfClose) {
          if (!codeOpt.isEmpty) {
            visit(codeOpt.get)
          } else if (!block.isEmpty) {
            for {
              n <- block
            } {
              visit(n)
            }
          }
          //close tag
          buf(e("</") + tagName + ">"); nl
        }

      case Empty => //ignore it
    }
  }

  def quote(str: String) = "\"" + str + "\""

  def buf(str: String) {
    builder ++= ("builder += (" + str + ")")
  }

  def nl {
    builder ++= "\n"
  }

  def isQuoted(str: String) = (str.startsWith("\"") && str.endsWith("\"")) || (str.startsWith("'") && str.endsWith("'"))

  def qi(s: String) = e("'") + i(s) + e("'")

  //construct evaluated string
  def e(s: String) = new EvalString(quote(s))

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