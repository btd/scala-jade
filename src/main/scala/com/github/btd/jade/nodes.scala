/*
 *    Copyright 2013 Denis Bardadym
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
    code: ?[Code] = None, //the single purpose to separate it is pretty print, in any other case it is ok to put it to block
    selfClose: Boolean = false,
    buffered: Boolean = false) extends Node {
  def isInline = Jade.inlineTags.contains(name)

  def canInline = {
    import Node._

    def isInline(node: Node): Boolean = {
      node match {
        case t @ Tag(_, _, block, _, _, _) => t.isInline && block.forall(isInline)
        case Text(_) | Empty => true
        case NodeSeq(nodes) => nodes.forall(isInline)
        case other => false
      }
    }

    if (block.isEmpty) true
    else {
      if (block.size == 1) isInline(block.head)
      else {
        if (block.forall(isInline)) {
          !block.filter(_ != Empty).zip(block.tail).exists((p: (Node, Node)) => isText(p._1) && isText(p._2))
        } else false
      }
    }
  }
}

case class NodeSeq(nodes: Seq[Node]) extends Node with collection.SeqProxy[Node] {
  val self = nodes
}

case class Mixin(name: String, args: Seq[String], call: Boolean = false, block: Seq[Node] = Seq()) extends Node
case object MixinBlock extends Node

case class Literal(value: String) extends Node

case class Filter(name: String, block: Seq[Text]) extends Node

case class Each(key: String, coll: String, block: Seq[Node], alternative: ?[Seq[Node]]) extends Node

case class Doctype(value: String) extends Node

case class Comment(value: String, buffer: Boolean) extends Node
case class BlockComment(value: String, block: Seq[Node], buffer: Boolean) extends Node

case class Code(value: String, escape: Boolean, buffer: Boolean, block: ?[Seq[Node]]) extends Node

case class Case(expr: String, block: Seq[Node]) extends Node
case class When(expr: String, block: Seq[Node]) extends Node

case object Empty extends Node // this is  'do nothing node'

object Node {
  def isText(node: Node) = {
    node match {
      case Text(_) => true
      case _ => false
    }
  }
}