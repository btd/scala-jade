package com.github.btd.jade
package nodes

sealed trait Node

/**
 * just a static text
 */
case class Text(value: String) extends Node

trait Attributes // ?

case class AttributeValue(value: String, escape: Boolean = true)

case class Tag(
    name: String,
    attributes: Map[String, ?[AttributeValue]] = Map(),
    block: Seq[Node] = Seq(),
    selfClose: Boolean = false,
    textOnly: Boolean = false,
    code: ?[Code] = None,
    buffered: Boolean = false) extends Node {
  def isInline = Jade.inlineTags.contains(name)

  def canInline = ???
}

case class Block(name: String, mode: String, block: Seq[Node]) extends Node

case class NodeSeq(nodes: Seq[Node]) extends Node with collection.SeqProxy[Node] {
  val self = nodes
}

case class Mixin(name: String, args: Seq[String], call: Boolean = false, block: ?[Seq[Node]] = None) extends Node

case class Literal(value: String) extends Node

case class Filter(name: String, block: Seq[Node], attributes: Map[String, AttributeValue] = Map()) extends Node

case class Each(key: String, coll: String, block: Seq[Node], alternative: ?[Seq[Node]]) extends Node

case class Doctype(value: String) extends Node

case class Comment(value: String, buffer: Boolean) extends Node
case class BlockComment(value: String, block: Seq[Node], buffer: Boolean) extends Node

case class Code(value: String, escape: Boolean, buffer: Boolean, block: ?[Seq[Node]]) extends Node

case class Case(expr: String, block: Seq[Node]) extends Node
case class When(expr: String, block: Seq[Node]) extends Node

case object Yield extends Node

case object Empty extends Node