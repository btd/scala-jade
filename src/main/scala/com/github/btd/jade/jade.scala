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

object Jade {
  val inlineTags = Set("a", "abbr", "acronym", "b", "br", "code", "em", "font", "i", "img", "ins", "kbd", "map", "samp", "small", "span", "strong", "sub", "sup")

  var textOnlyTags = Set("style" -> None, "script" -> Some("type" -> "text/javascript"))

  var sourceLoader: SourceLoader = new NoopSourceLoader

  //TODO separate filters and transformers
  val filters: Map[String, String => String] = Map(
    "cdata" -> ((str: String) => "\"<![CDATA[\\n\" + " + str + "+ \"\\n]]>\""),
    "js" -> ((str: String) => "\"<script type='text/javascript'>\\n\" + \"\"\"" + str + "\"\"\" + \"\\n</script>\""),
    "css" -> ((str: String) => "\"<style type='text/css'>\\n\" + \"\"\"" + str + "\"\"\" + \"\\n</style>\"")
  ).withDefaultValue { (str: String) => "\"\"\"" + str + "\"\"\"" }

  var fileExt = ".jade"

  val doctypes = Map(
    "5" -> """<!DOCTYPE html>""",
    "xml" -> """<?xml version="1.0" encoding="utf-8" ?>""",
    "transitional" -> """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">""",
    "strict" -> """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">""",
    "frameset" -> """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">""",
    "1.1" -> """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">""",
    "basic" -> """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML Basic 1.1//EN" "http://www.w3.org/TR/xhtml-basic/xhtml-basic11.dtd">""",
    "mobile" -> """<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN" "http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">"""
  )

  val selfCloseTags = Set("meta", "img", "link", "input", "source", "area", "base", "col", "br", "hr")

  var defaultDoctype = "5"

  var tab = "  "

  var quote = "'"

  var neverPrettyPrint = Set("pre")

}

trait SourceLoader {
  def getInput(name: String): (String, String)
}

class NoopSourceLoader extends SourceLoader {
  def getInput(ignore: String): (String, String) = throw new Exception("I don't know how to load")
}

import com.typesafe.scalalogging.slf4j._

class FileSourceLoader(baseDirs: Seq[String]) extends SourceLoader with Logging {
  def stripBOM(value: java.io.File) = {
    val BOM_SIZE = 4
    val EF = 0xEF.toByte
    val BB = 0xBB.toByte
    val BF = 0xBF.toByte
    val FE = 0xFE.toByte
    val FF = 0xFF.toByte
    val bom = Array.ofDim[Byte](BOM_SIZE)
    val in = new java.io.PushbackInputStream(new java.io.FileInputStream(value), BOM_SIZE)
    val readSize = in.read(bom, 0, bom.length)
    val (bomSize, encoding) = bom.toList match {
      case EF :: BB :: BF :: xs => (3, "UTF-8")
      case FE :: FF :: xs => (2, "UTF-16BE")
      case FF :: FE :: xs => (2, "UTF-16LE")
      case _ => (0, "UTF-8")
    }
    in.unread(bom, bomSize, readSize - bomSize)
    val s = io.Source.fromInputStream(new java.io.BufferedInputStream(in), encoding)
    val content = s.getLines.mkString("\n")
    s.close
    content
  }

  def tryFoundFileByName(baseDir: String, name: String) = {
    logger.debug(s"Try to find file $name in $baseDir")
    val path = Path.join(baseDir, name)
    val file = new java.io.File(path)
    logger.debug(s"Computed path $path")
    if (!file.exists) None
    else {
      Some((name, stripBOM(file)))
    }
  }

  def getInput(name: String) = {
    baseDirs
      .toStream
      .map(bd =>
        tryFoundFileByName(bd, name)
          .orElse(
            tryFoundFileByName(bd, name + Jade.fileExt)
          )
      ).find(_.isDefined)
      .map(_.get).getOrElse(throw new Exception(s"File $name not found"))
  }
}