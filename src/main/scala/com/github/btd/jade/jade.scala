package com.github.btd.jade

object Jade {
  val inlineTags = Set("a", "abbr", "acronym", "b", "br", "code", "em", "font", "i", "img", "ins", "kbd", "map", "samp", "small", "span", "strong", "sub", "sup")

  var textOnlyTags = Set("style" -> None, "script" -> Some("type" -> "text/javascript"))

  var getInput: String => (String, String) = blockName => ("test.jade", "")

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

}