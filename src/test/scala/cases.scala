
package com.github.btd.jade

import org.specs2.mutable._

class CasesSpec extends Specification {

  Jade.sourceLoader = new FileSourceLoader("./jade/test/cases" :: Nil)

  "attrs-data.jade" should {

    object attrs_data_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        val user = ("Tobi")

        nl()
        buf("")
        buf("<" + "foo" + boolAttr(user).map(v => if (v) { " " + "data-user" } else "").getOrElse(falsy(user).map(v => " " + "data-user" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "foo" + ">")
        nl()
        buf("")
        buf("<" + "bar" + " " + "data-username" + "=" + """'tobi'""" + ">")
        buf("</" + "bar" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "attrs-data.html")
      attrs_data_html() === testResult
    }
  }

  "attrs.interpolation.jade" should {

    object attrs_interpolation_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var id = 5

        buf("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((id).toString) + """'""" + ">")
        buf("</" + "a" + ">")
        nl()
        buf("")
        buf("<" + "foo" + " " + "bar" + "=" + """'stuff #{here} yup'""" + ">")
        buf("</" + "foo" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "attrs.interpolation.html")
      attrs_interpolation_html() === testResult
    }
  }

  "attrs.jade" should {

    object attrs_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<" + "a" + " " + "href" + "=" + """'/contact'""" + ">")
        buf("""contact""")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "class" + "=" + """'button'""" + " " + "href" + "=" + """'/save'""" + ">")
        buf("""save""")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "foo" + " " + "bar" + " " + "baz" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "foo" + "=" + """'foo, bar, baz'""" + boolAttr(1).map(v => if (v) { " " + "bar" } else "").getOrElse(falsy(1).map(v => " " + "bar" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")
        val bar = (if (true) "1" else "0")

        buf("<" + "a" + " " + "foo" + "=" + """'((foo))'""" + boolAttr(bar).map(v => if (v) { " " + "bar" } else "").getOrElse(falsy(bar).map(v => " " + "bar" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")
        nl()
        buf("")
        buf("<" + "select" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "option" + " " + "value" + "=" + """'foo'""" + " " + "selected" + ">")
        buf("""Foo""")
        buf("</" + "option" + ">")
        nl()
        buf("  ")
        buf("<" + "option" + " " + "selected" + " " + "value" + "=" + """'bar'""" + ">")
        buf("""Bar""")
        buf("</" + "option" + ">")
        nl()
        buf("")
        buf("</" + "select" + ">")
        buf("<" + "a" + " " + "foo" + "=" + """"class:"""" + ">")
        buf("</" + "a" + ">")
        nl()
        buf("")
        buf("<" + "input" + " " + "pattern" + "=" + """'\S+'""" + "/>")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "attrs.html")
      attrs_html() === testResult
    }
  }

  "attrs.js.jade" should {

    object attrs_js_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var id = 5

        def answer() = 42

        buf("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((42).toString) + """'""" + " " + "class" + "=" + """'button'""" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'/user/""" + escape((id).toString) + """'""" + " " + "class" + "=" + """'button'""" + ">")
        buf("</" + "a" + ">")
        val a = answer()

        nl()
        buf("")
        buf("<" + "meta" + " " + "key" + "=" + """'answer'""" + boolAttr(a).map(v => if (v) { " " + "value" } else "").getOrElse(falsy(a).map(v => " " + "value" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "attrs.js.html")
      attrs_js_html() === testResult
    }
  }

  "attrs.unescaped.jade" should {

    object attrs_unescaped_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "id" + "=" + """'user-<%= user.id %>'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""<%= user.title %>""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "attrs.unescaped.html")
      attrs_unescaped_html() === testResult
    }
  }

  "basic.jade" should {

    object basic_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Title""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "basic.html")
      basic_html() === testResult
    }
  }

  "blanks.jade" should {

    object blanks_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""foo""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""bar""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""baz""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "blanks.html")
      blanks_html() === testResult
    }
  }

  "block-expansion.jade" should {

    object block_expansion_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
        buf("""foo""")
        buf("</" + "a" + ">")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
        buf("""bar""")
        buf("</" + "a" + ">")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf("""baz""")
        buf("</" + "p" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "block-expansion.html")
      block_expansion_html() === testResult
    }
  }

  "block-expansion.shorthands.jade" should {

    object block_expansion_shorthands_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + " " + "class" + "=" + """'list-item'""" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "class" + "=" + """'foo'""" + ">")
        nl()
        buf("      ")
        buf("<" + "div" + " " + "id" + "=" + """'bar'""" + ">")
        buf("""baz""")
        buf("</" + "div" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
        nl()
        buf("  ")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "block-expansion.shorthands.html")
      block_expansion_shorthands_html() === testResult
    }
  }

  "blockquote.jade" should {

    object blockquote_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "figure" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "blockquote" + "" + ">")
        buf("""Try to define yourself by what you do, and you&#8217;ll burnout every time. You are. That is enough. I rest in that.""")
        buf("</" + "blockquote" + ">")
        nl()
        buf("  ")
        buf("<" + "figcaption" + "" + ">")
        buf("""from @thefray at 1:43pm on May 10""")
        buf("</" + "figcaption" + ">")
        nl()
        buf("")
        buf("</" + "figure" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "blockquote.html")
      blockquote_html() === testResult
    }
  }

  "case-blocks.jade" should {

    object case_blocks_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        val friends = (1)

        (friends) match {
          case 0 => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have no friends""")
            buf("</" + "p" + ">")
          }
          case 1 => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have a friend""")
            buf("</" + "p" + ">")
          }
          case _ => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have """ + escape((friends).toString) + """ friends""")
            buf("</" + "p" + ">")
          }
        }
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "case-blocks.html")
      case_blocks_html() === testResult
    }
  }

  "case.jade" should {

    object case_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        val friends = (1)

        (friends) match {
          case 0 => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have no friends""")
            buf("</" + "p" + ">")
          }
          case 1 => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have a friend""")
            buf("</" + "p" + ">")
          }
          case _ => {
            nl()
            buf("    ")
            buf("<" + "p" + "" + ">")
            buf("""you have """ + escape((friends).toString) + """ friends""")
            buf("</" + "p" + ">")
          }
        }
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "case.html")
      case_html() === testResult
    }
  }

  "classes-empty.jade" should {

    object classes_empty_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<" + "a" + " " + "class" + "=" + """""""" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + boolAttr(null).map(v => if (v) { " " + "class" } else "").getOrElse(falsy(null).map(v => " " + "class" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")
        val n = (None)

        buf("<" + "a" + boolAttr(n).map(v => if (v) { " " + "class" } else "").getOrElse(falsy(n).map(v => " " + "class" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "classes-empty.html")
      classes_empty_html() === testResult
    }
  }

  "classes.jade" should {

    object classes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<" + "a" + " " + "class" + "=" + """'foo bar'""" + ">")
        buf("</" + "a" + ">")
        buf("<" + "a" + " " + "class" + "=" + """'foo-bar_baz'""" + ">")
        buf("</" + "a" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "classes.html")
      classes_html() === testResult
    }
  }

  "code.conditionals.jade" should {

    object code_conditionals_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        if (true) {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""foo""")
          buf("</" + "p" + ">")
        } else {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        if (true) {
          {
            nl()
            buf("")
            buf("<" + "p" + "" + ">")
            buf("""foo""")
            buf("</" + "p" + ">")
          }
        } else {
          {
            nl()
            buf("")
            buf("<" + "p" + "" + ">")
            buf("""bar""")
            buf("</" + "p" + ">")
          }
        }

        if (true) {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""foo""")
          buf("</" + "p" + ">")
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""baz""")
          buf("</" + "p" + ">")
        } else {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        if (!(true)) {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""foo""")
          buf("</" + "p" + ">")
        } else {
          nl()
          buf("")
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        if (!("nested".isEmpty)) {
          if (!("works".isEmpty)) {
            nl()
            buf("")
            buf("<" + "p" + "" + ">")
            buf("""yay""")
            buf("</" + "p" + ">")
          }
        }

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "code.conditionals.html")
      code_conditionals_html() === testResult
    }
  }

  "code.escape.jade" should {

    object code_escape_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy("<script>").map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy("<script>").map(v => v).getOrElse(""))
        buf("</" + "p" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "code.escape.html")
      code_escape_html() === testResult
    }
  }

  "code.iteration.jade" should {

    object code_iteration_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var items = 1 :: 2 :: 3 :: Nil

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        items.foreach(item => {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf(falsy(item).map(v => escape(v)).getOrElse(""))
          buf("</" + "li" + ">")
        }
        )

        nl()
        buf("")
        buf("</" + "ul" + ">")
        val items2 = (1 :: 2 :: 3 :: Nil)

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for ((item, i) <- items2.zipWithIndex) {
          nl()
          buf("  ")
          buf("<" + "li" + " " + "class" + "=" + """'item-""" + escape((i).toString) + """'""" + ">")
          buf(falsy(item).map(v => escape(v)).getOrElse(""))
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for (item <- items2) {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf(falsy(item).map(v => escape(v)).getOrElse(""))
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        val nums = (1 to 3)

        val letters = ("a" :: "b" :: "c" :: Nil)

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for (l <- letters) {
          for (n <- nums) {
            nl()
            buf("  ")
            buf("<" + "li" + "" + ">")
            buf("""""" + escape((n).toString) + """: """ + escape((l).toString) + """""")
            buf("</" + "li" + ">")
          }
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        val count = (0)

        val counter = (() => (1 to 3).map(_ + count))

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        for (n <- counter()) {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf("""""" + escape((n).toString) + """""")
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "code.iteration.html")
      code_iteration_html() === testResult
    }
  }

  "code.jade" should {

    object code_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(null).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(None).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy("").map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(0).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(falsy(false).map(v => escape(v)).getOrElse(""))
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(null).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(null).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(None).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(None).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "foo" + "=" + """""""" + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(0).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(0).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + boolAttr(false).map(v => if (v) { " " + "foo" } else "").getOrElse(falsy(false).map(v => " " + "foo" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "p" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "code.html")
      code_html() === testResult
    }
  }

  "comments.conditional.jade" should {

    object comments_conditional_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("<!--[if IE lt 9]>")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'/lame.js'""" + ">")
        buf("</" + "script" + ">")
        buf("<![endif]-->")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "comments.conditional.html")
      comments_conditional_html() === testResult
    }
  }

  "comments.jade" should {

    object comments_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<!-- foo-->")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<!-- bar-->")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""one""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")
        buf("<!-- asdf")
        nl()
        buf("")
        buf("<!-- baz-->")
        nl()
        buf("")
        buf("<" + "li" + "" + ">")
        buf("""two""")
        buf("</" + "li" + ">")
        buf("-->")
        buf("<!--")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""foo""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")
        buf("-->")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "comments.html")
      comments_html() === testResult
    }
  }

  "doctype.default.jade" should {

    object doctype_default_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("""<!DOCTYPE html>""")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Title""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "doctype.default.html")
      doctype_default_html() === testResult
    }
  }

  "each.else.jade" should {

    object each_else_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        case class User(name: String, friends: List[String] = Nil, age: Int = 30)

        val users = (List[User]())

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        val temp$$0 = (users)
        if (!(temp$$0).isEmpty) {
          for (user <- temp$$0) {
            nl()
            buf("  ")
            buf("<" + "li" + "" + ">")
            buf(falsy(user.name).map(v => escape(v)).getOrElse(""))
            buf("</" + "li" + ">")
          }
        } else {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf("""no users!""")
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")
        val users2 = (User("tobi", "loki" :: Nil) :: User("loki") :: Nil)

        if (!(users2.isEmpty)) {
          nl()
          buf("")
          buf("<" + "ul" + "" + ">")
          val temp$$1 = (users2)
          if (!(temp$$1).isEmpty) {
            for (user <- temp$$1) {
              nl()
              buf("  ")
              buf("<" + "li" + "" + ">")
              buf(falsy(user.name).map(v => escape(v)).getOrElse(""))
              buf("</" + "li" + ">")
            }
          } else {
            nl()
            buf("  ")
            buf("<" + "li" + "" + ">")
            buf("""no users!""")
            buf("</" + "li" + ">")
          }
          nl()
          buf("")
          buf("</" + "ul" + ">")
        }
        val users3 = (List[User]())

        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        val temp$$2 = (users3)
        if (!(temp$$2).isEmpty) {
          for (user <- temp$$2) {
            nl()
            buf("  ")
            buf("<" + "li" + "" + ">")
            buf(falsy(user.name).map(v => escape(v)).getOrElse(""))
            buf("</" + "li" + ">")
          }
        } else {
          nl()
          buf("  ")
          buf("<" + "li" + "" + ">")
          buf("""no users!""")
          buf("</" + "li" + ">")
        }
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "each.else.html")
      each_else_html() === testResult
    }
  }

  "escape-chars.jade" should {

    object escape_chars_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        buf("""var re = /\d+/;""")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "escape-chars.html")
      escape_chars_html() === testResult
    }
  }

  "escape-test.jade" should {

    object escape_test_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("""<!DOCTYPE html>""")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""escape-test""")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "textarea" + "" + ">")
        var txt = """<param name="flashvars" value="a=&quot;value_a&quot;&b=&quot;value_b&quot;&c=3"/>"""

        buf("""""" + escape((txt).toString) + """""")
        nl()
        buf("    ")
        buf("</" + "textarea" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "escape-test.html")
      escape_test_html() === testResult
    }
  }

  "filters.cdata.jade" should {

    object filters_cdata_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        case class User(name: String, age: Int)

        val users = (User("tobi", 2) :: Nil)

        nl()
        buf("")
        buf("<" + "fb:users" + "" + ">")
        for (user <- users) {
          val age = (user.age)

          nl()
          buf("  ")
          buf("<" + "fb:user" + boolAttr(age).map(v => if (v) { " " + "age" } else "").getOrElse(falsy(age).map(v => " " + "age" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
          val name = (user.name)

          buf("<![CDATA[\n" + """""" + escape((name).toString) + """""" + "\n]]>")
          nl()
          buf("  ")
          buf("</" + "fb:user" + ">")
        }
        nl()
        buf("")
        buf("</" + "fb:users" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "filters.cdata.html")
      filters_cdata_html() === testResult
    }
  }

  "html.jade" should {

    object html_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("""<ul>""")
        nl()
        buf("")
        buf("")
        buf("""<li>foo</li>""")
        nl()
        buf("")
        buf("")
        buf("""<li>bar</li>""")
        nl()
        buf("")
        buf("")
        buf("""<li>baz</li>""")
        nl()
        buf("")
        buf("")
        buf("""</ul>""")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf("""You can <em>embed</em> html as well.""")
        buf("</" + "p" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "html.html")
      html_html() === testResult
    }
  }

  "html5.jade" should {

    object html5_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("""<!DOCTYPE html>""")
        nl()
        buf("")
        buf("<" + "input" + " " + "type" + "=" + """'checkbox'""" + " " + "checked" + "/>")
        nl()
        buf("")
        buf("<" + "input" + " " + "type" + "=" + """'checkbox'""" + boolAttr(true).map(v => if (v) { " " + "checked" } else "").getOrElse(falsy(true).map(v => " " + "checked" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")
        nl()
        buf("")
        buf("<" + "input" + " " + "type" + "=" + """'checkbox'""" + boolAttr(false).map(v => if (v) { " " + "checked" } else "").getOrElse(falsy(false).map(v => " " + "checked" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "html5.html")
      html5_html() === testResult
    }
  }

  "include-extends-from-root.jade" should {

    object include_extends_from_root_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""hello""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include-extends-from-root.html")
      include_extends_from_root_html() === testResult
    }
  }

  "include-extends-of-common-template.jade" should {

    object include_extends_of_common_template_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "div" + "" + ">")
        buf("""test1""")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("<" + "div" + "" + ">")
        buf("""test2""")
        buf("</" + "div" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include-extends-of-common-template.html")
      include_extends_of_common_template_html() === testResult
    }
  }

  "include-only-text-body.jade" should {

    object include_only_text_body_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        buf("""The message is """")
        buf(""""""")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include-only-text-body.html")
      include_only_text_body_html() === testResult
    }
  }

  "include-only-text.jade" should {

    object include_only_text_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""The message is """")
        buf("<" + "em" + "" + ">")
        buf("""hello world""")
        buf("</" + "em" + ">")
        buf(""""""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include-only-text.html")
      include_only_text_html() === testResult
    }
  }

  "include-with-text-head.jade" should {

    object include_with_text_head_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "head" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        buf("""alert('hello world');""")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("</" + "head" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include-with-text-head.html")
      include_with_text_head_html() === testResult
    }
  }

  "include-with-text.jade" should {

    object include_with_text_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        buf("""alert('hello world');""")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include-with-text.html")
      include_with_text_html() === testResult
    }
  }

  "include.script.jade" should {

    object include_script_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "id" + "=" + """'pet-template'""" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "class" + "=" + """'pet'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""{{name}}""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""{{name}} is a {{species}} that is {{age}} old""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include.script.html")
      include_script_html() === testResult
    }
  }

  "include.yield.nested.jade" should {

    object include_yield_nested_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Page""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "id" + "=" + """'content'""" + ">")
        nl()
        buf("      ")
        buf("<" + "div" + " " + "id" + "=" + """'content-wrapper'""" + ">")
        nl()
        buf("        ")
        buf("<" + "p" + "" + ">")
        buf("""some content""")
        buf("</" + "p" + ">")
        nl()
        buf("        ")
        buf("<" + "p" + "" + ">")
        buf("""and some more""")
        buf("</" + "p" + ">")
        nl()
        buf("      ")
        buf("</" + "div" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "id" + "=" + """'footer'""" + ">")
        nl()
        buf("      ")
        buf("<" + "stuff" + "" + ">")
        buf("</" + "stuff" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "include.yield.nested.html")
      include_yield_nested_html() === testResult
    }
  }

  "includes-with-ext-js.jade" should {

    object includes_with_ext_js_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        buf("<script type='text/javascript'>\n" + """var x = "\n here is some \n new lined text";""" + "\n</script>")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "includes-with-ext-js.html")
      includes_with_ext_js_html() === testResult
    }
  }

  "includes.jade" should {

    object includes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_foo()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""bar""")
          buf("</" + "p" + ">")
        }
        jade_mixin_foo()(0, indentLevel => {
        })
        nl()
        buf("")
        buf("<" + "body" + "" + ">")
        buf("""<p>:)</p>""")
        buf("""<script>
  console.log("foo\nbar")
</script>""")
        nl()
        buf("")
        buf("</" + "body" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "includes.html")
      includes_html() === testResult
    }
  }

  "inheritance.alert-dialog.jade" should {

    object inheritance_alert_dialog_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "div" + " " + "class" + "=" + """'window'""" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + " " + "class" + "=" + """'close'""" + ">")
        buf("""Close""")
        buf("</" + "a" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "class" + "=" + """'dialog'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""Alert!""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""I'm an alert!""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "div" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.alert-dialog.html")
      inheritance_alert_dialog_html() === testResult
    }
  }

  "inheritance.defaults.jade" should {

    object inheritance_defaults_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'keymaster.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.defaults.html")
      inheritance_defaults_html() === testResult
    }
  }

  "inheritance.extend.include.jade" should {

    object inheritance_extend_include_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""Page""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""Some content""")
        buf("</" + "p" + ">")
        nl()
        buf("    ")
        buf("<" + "div" + " " + "class" + "=" + """'window'""" + ">")
        buf("<" + "a" + " " + "href" + "=" + """'#'""" + " " + "class" + "=" + """'close'""" + ">")
        buf("""Close""")
        buf("</" + "a" + ">")
        nl()
        buf("      ")
        buf("<" + "h2" + "" + ">")
        buf("""Awesome""")
        buf("</" + "h2" + ">")
        nl()
        buf("      ")
        buf("<" + "p" + "" + ">")
        buf("""Now we can extend included blocks!""")
        buf("</" + "p" + ">")
        nl()
        buf("    ")
        buf("</" + "div" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.extend.include.html")
      inheritance_extend_include_html() === testResult
    }
  }

  "inheritance.extend.jade" should {

    object inheritance_extend_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""Page""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""Some content""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.extend.html")
      inheritance_extend_html() === testResult
    }
  }

  "inheritance.extend.mixins.jade" should {

    object inheritance_extend_mixins_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_article(title: String)(indentLevel: Int, block: Int => Unit) = {
          if (title != null && title != "") {
            nl()
            buf("" + ("  " * indentLevel))
            buf("<" + "h1" + "" + ">")
            buf(falsy(title).map(v => escape(v)).getOrElse(""))
            buf("</" + "h1" + ">")
          }
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_article("The meaning of life")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.extend.mixins.html")
      inheritance_extend_mixins_html() === testResult
    }
  }

  "inheritance.extend.recursive.jade" should {

    object inheritance_extend_recursive_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "h1" + "" + ">")
        buf("""grand-grandparent""")
        buf("</" + "h1" + ">")
        nl()
        buf("")
        buf("<" + "h2" + "" + ">")
        buf("""grandparent""")
        buf("</" + "h2" + ">")
        nl()
        buf("")
        buf("<" + "h3" + "" + ">")
        buf("""parent""")
        buf("</" + "h3" + ">")
        nl()
        buf("")
        buf("<" + "h4" + "" + ">")
        buf("""child""")
        buf("</" + "h4" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.extend.recursive.html")
      inheritance_extend_recursive_html() === testResult
    }
  }

  "inheritance.extend.whitespace.jade" should {

    object inheritance_extend_whitespace_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""Page""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""Some content""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.extend.whitespace.html")
      inheritance_extend_whitespace_html() === testResult
    }
  }

  "inheritance.jade" should {

    object inheritance_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "title" + "" + ">")
        buf("""My Application""")
        buf("</" + "title" + ">")
        nl()
        buf("    ")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""Page""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""Some content""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "inheritance.html")
      inheritance_html() === testResult
    }
  }

  "interpolation.escape.jade" should {

    object interpolation_escape_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        var id = 42;

        nl()
        buf("")
        buf("<" + "foo" + "" + ">")
        nl()
        buf("  ")
        buf("""some""")
        nl()
        buf("")
        buf("  ")
        buf("""#{text}""")
        nl()
        buf("")
        buf("  ")
        buf("""here""")
        nl()
        buf("")
        buf("  ")
        buf("""My ID is {""" + escape((id).toString) + """}""")
        nl()
        buf("")
        buf("</" + "foo" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "interpolation.escape.html")
      interpolation_escape_html() === testResult
    }
  }

  "layout.append.jade" should {

    object layout_append_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'bar.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "layout.append.html")
      layout_append_html() === testResult
    }
  }

  "layout.append.without-block.jade" should {

    object layout_append_without_block_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'bar.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "layout.append.without-block.html")
      layout_append_without_block_html() === testResult
    }
  }

  "layout.multi.append.prepend.block.jade" should {

    object layout_multi_append_prepend_block_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'first prepend'""" + ">")
        buf("""Last prepend must appear at top""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'first prepend'""" + ">")
        buf("""Something prepended to content""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'first append'""" + ">")
        buf("""Something appended to content""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + " " + "class" + "=" + """'last append'""" + ">")
        buf("""Last append must be most last""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "div" + " " + "class" + "=" + """'content'""" + ">")
        buf("""Defined content""")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "layout.multi.append.prepend.block.html")
      layout_multi_append_prepend_block_html() === testResult
    }
  }

  "layout.prepend.jade" should {

    object layout_prepend_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'bar.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "layout.prepend.html")
      layout_prepend_html() === testResult
    }
  }

  "layout.prepend.without-block.jade" should {

    object layout_prepend_without_block_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'foo.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'bar.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'vendor/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "layout.prepend.without-block.html")
      layout_prepend_without_block_html() === testResult
    }
  }

  "mixin-hoist.jade" should {

    object mixin_hoist_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_foo(title: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "h1" + "" + ">")
          buf(falsy(title).map(v => escape(v)).getOrElse(""))
          buf("</" + "h1" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_foo("Jade")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "mixin-hoist.html")
      mixin_hoist_html() === testResult
    }
  }

  "mixin.block-tag-behaviour.jade" should {

    object mixin_block_tag_behaviour_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_article1(name: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "section" + " " + "class" + "=" + """'article'""" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "h1" + "" + ">")
          buf(falsy(name).map(v => escape(v)).getOrElse(""))
          buf("</" + "h1" + ">")
          block(indentLevel + 1)
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "section" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_article1("Foo")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""I'm article foo""")
          buf("</" + "p" + ">")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        def jade_mixin_article2(name: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "section" + " " + "class" + "=" + """'article'""" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "h1" + "" + ">")
          buf(falsy(name).map(v => escape(v)).getOrElse(""))
          buf("</" + "h1" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          block(indentLevel + 2)
          nl()
          buf("  " + ("  " * indentLevel))
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "section" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_article2("Something")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("""I'm a much longer""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""text-only article,""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""but you can still""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""inline html tags""")
          nl()
          buf("")
          buf("" + ("  " * indentLevel))
          buf("""in me if you want.""")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "mixin.block-tag-behaviour.html")
      mixin_block_tag_behaviour_html() === testResult
    }
  }

  "mixin.blocks.jade" should {

    object mixin_blocks_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_form(method: String, action: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "form" + boolAttr(method).map(v => if (v) { " " + "method" } else "").getOrElse(falsy(method).map(v => " " + "method" + "=" + "'" + escape(v) + "'").getOrElse("")) + boolAttr(action).map(v => if (v) { " " + "action" } else "").getOrElse(falsy(action).map(v => " " + "action" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
          val csrf_token_from_somewhere = ("hey")

          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'hidden'""" + " " + "name" + "=" + """'_csrf'""" + boolAttr(csrf_token_from_somewhere).map(v => if (v) { " " + "value" } else "").getOrElse(falsy(csrf_token_from_somewhere).map(v => " " + "value" + "=" + "'" + escape(v) + "'").getOrElse("")) + "/>")
          block(indentLevel + 1)
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "form" + ">")
        }
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_form("GET", "/search")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'query'""" + " " + "placeholder" + "=" + """'Search'""" + "/>")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'submit'""" + " " + "value" + "=" + """'Search'""" + "/>")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_form("POST", "/search")(2, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'query'""" + " " + "placeholder" + "=" + """'Search'""" + "/>")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "input" + " " + "type" + "=" + """'submit'""" + " " + "value" + "=" + """'Search'""" + "/>")
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        jade_mixin_form("POST", "/search")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")
        def jade_mixin_bar()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "div" + " " + "id" + "=" + """'bar'""" + ">")
          block(indentLevel + 1)
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "div" + ">")
        }
        def jade_mixin_foo()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "div" + " " + "id" + "=" + """'foo'""" + ">")
          jade_mixin_bar()(1, indentLevel => {
            block(indentLevel + 0)
          })
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "div" + ">")
        }
        jade_mixin_foo()(0, indentLevel => {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""one""")
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""two""")
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "p" + "" + ">")
          buf("""three""")
          buf("</" + "p" + ">")
        })

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "mixin.blocks.html")
      mixin_blocks_html() === testResult
    }
  }

  "mixins.jade" should {

    object mixins_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        def jade_mixin_comment(title: String, str: String)(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "div" + " " + "class" + "=" + """'comment'""" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "h2" + "" + ">")
          buf(falsy(title).map(v => escape(v)).getOrElse(""))
          buf("</" + "h2" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "p" + " " + "class" + "=" + """'body'""" + ">")
          buf(falsy(str).map(v => escape(v)).getOrElse(""))
          buf("</" + "p" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "div" + ">")
        }
        nl()
        buf("")
        buf("<" + "div" + " " + "id" + "=" + """'user'""" + ">")
        nl()
        buf("  ")
        buf("<" + "h1" + "" + ">")
        buf("""Tobi""")
        buf("</" + "h1" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "class" + "=" + """'comments'""" + ">")
        jade_mixin_comment("This", "is regular, javascript")(2, indentLevel => {
        })
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "div" + ">")
        def jade_mixin_list()(indentLevel: Int, block: Int => Unit) = {
          nl()
          buf("" + ("  " * indentLevel))
          buf("<" + "ul" + "" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "li" + "" + ">")
          buf("""foo""")
          buf("</" + "li" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "li" + "" + ">")
          buf("""bar""")
          buf("</" + "li" + ">")
          nl()
          buf("  " + ("  " * indentLevel))
          buf("<" + "li" + "" + ">")
          buf("""baz""")
          buf("</" + "li" + ">")
          nl()
          buf("" + ("  " * indentLevel))
          buf("</" + "ul" + ">")
        }
        nl()
        buf("")
        buf("<" + "body" + "" + ">")
        jade_mixin_list()(1, indentLevel => {
        })
        nl()
        buf("")
        buf("</" + "body" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "mixins.html")
      mixins_html() === testResult
    }
  }

  "namespaces.jade" should {

    object namespaces_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "fb:user:role" + "" + ">")
        buf("""Something""")
        buf("</" + "fb:user:role" + ">")
        nl()
        buf("")
        buf("<" + "foo" + " " + "fb:foo" + "=" + """'bar'""" + ">")
        buf("</" + "foo" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "namespaces.html")
      namespaces_html() === testResult
    }
  }

  "nesting.jade" should {

    object nesting_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""a""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""b""")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "ul" + "" + ">")
        nl()
        buf("      ")
        buf("<" + "li" + "" + ">")
        buf("""c""")
        buf("</" + "li" + ">")
        nl()
        buf("      ")
        buf("<" + "li" + "" + ">")
        buf("""d""")
        buf("</" + "li" + ">")
        nl()
        buf("    ")
        buf("</" + "ul" + ">")
        nl()
        buf("  ")
        buf("</" + "li" + ">")
        nl()
        buf("  ")
        buf("<" + "li" + "" + ">")
        buf("""e""")
        buf("</" + "li" + ">")
        nl()
        buf("")
        buf("</" + "ul" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "nesting.html")
      nesting_html() === testResult
    }
  }

  "quotes.jade" should {

    object quotes_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(""""foo"""")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf("""'foo'""")
        buf("</" + "p" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "quotes.html")
      quotes_html() === testResult
    }
  }

  "regression.784.jade" should {

    object regression_784_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        val url = ("http://www.google.com".replaceAll("http://", "").replaceAll("www.", ""))

        nl()
        buf("")
        buf("<" + "div" + " " + "class" + "=" + """'url'""" + ">")
        buf("""""" + escape((url).toString) + """""")
        buf("</" + "div" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "regression.784.html")
      regression_784_html() === testResult
    }
  }

  "scala.args.default.jade" should {

    object scala_args_default_html {
      import com.github.btd.jade.Template._

      def apply(user: String = "Tobi") = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "foo" + boolAttr(user).map(v => if (v) { " " + "data-user" } else "").getOrElse(falsy(user).map(v => " " + "data-user" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "foo" + ">")
        nl()
        buf("")
        buf("<" + "bar" + " " + "data-username" + "=" + """'tobi'""" + ">")
        buf("</" + "bar" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "scala.args.default.html")
      scala_args_default_html() === testResult
    }
  }

  "script.whitespace.jade" should {

    object script_whitespace_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        nl()
        buf("  ")
        buf("""if (foo) {""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("  ")
        buf("""  bar();""")
        nl()
        buf("")
        buf("  ")
        buf("""  """)
        nl()
        buf("")
        buf("  ")
        buf("""}""")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "script.whitespace.html")
      script_whitespace_html() === testResult
    }
  }

  "scripts.jade" should {

    object scripts_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/javascript'""" + ">")
        nl()
        buf("  ")
        buf("""if (foo) {""")
        nl()
        buf("")
        buf("  ")
        buf("""  bar();""")
        nl()
        buf("")
        buf("  ")
        buf("""}""")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "scripts.html")
      scripts_html() === testResult
    }
  }

  "scripts.non-js.jade" should {

    object scripts_non_js_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "id" + "=" + """'user-template'""" + " " + "type" + "=" + """'text/template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "div" + " " + "id" + "=" + """'user'""" + ">")
        nl()
        buf("    ")
        buf("<" + "h1" + "" + ">")
        buf("""<%= user.name %>""")
        buf("</" + "h1" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""<%= user.description %>""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "div" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "id" + "=" + """'user-template'""" + " " + "type" + "=" + """'text/template'""" + ">")
        nl()
        buf("  ")
        buf("""if (foo) {""")
        nl()
        buf("")
        buf("  ")
        buf("""  bar();""")
        nl()
        buf("")
        buf("  ")
        buf("""}""")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "scripts.non-js.html")
      scripts_non_js_html() === testResult
    }
  }

  "source.jade" should {

    object source_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "audio" + " " + "preload" + "=" + """'auto'""" + " " + "autobuffer" + "=" + """'autobuffer'""" + " " + "controls" + "=" + """'controls'""" + ">")
        nl()
        buf("    ")
        buf("<" + "source" + " " + "src" + "=" + """'foo'""" + "/>")
        nl()
        buf("    ")
        buf("<" + "source" + " " + "src" + "=" + """'bar'""" + "/>")
        nl()
        buf("  ")
        buf("</" + "audio" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "source.html")
      source_html() === testResult
    }
  }

  "styles.jade" should {

    object styles_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "style" + "" + ">")
        nl()
        buf("      ")
        buf("""body {""")
        nl()
        buf("")
        buf("      ")
        buf("""  padding: 50px;""")
        nl()
        buf("")
        buf("      ")
        buf("""}""")
        nl()
        buf("    ")
        buf("</" + "style" + ">")
        nl()
        buf("  ")
        buf("</" + "head" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "styles.html")
      styles_html() === testResult
    }
  }

  "tags.self-closing.jade" should {

    object tags_self_closing_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "body" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + "" + ">")
        buf("</" + "foo" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + " " + "bar" + "=" + """'baz'""" + ">")
        buf("</" + "foo" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + "" + "/>")
        nl()
        buf("  ")
        buf("<" + "foo" + " " + "bar" + "=" + """'baz'""" + "/>")
        nl()
        buf("  ")
        buf("<" + "foo" + "" + ">")
        buf("""/""")
        buf("</" + "foo" + ">")
        nl()
        buf("  ")
        buf("<" + "foo" + " " + "bar" + "=" + """'baz'""" + ">")
        buf("""/""")
        buf("</" + "foo" + ">")
        nl()
        buf("")
        buf("</" + "body" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "tags.self-closing.html")
      tags_self_closing_html() === testResult
    }
  }

  "template.jade" should {

    object template_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("<" + "article" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "h2" + "" + ">")
        buf("""{{title}}""")
        buf("</" + "h2" + ">")
        nl()
        buf("    ")
        buf("<" + "p" + "" + ">")
        buf("""{{description}}""")
        buf("</" + "p" + ">")
        nl()
        buf("  ")
        buf("</" + "article" + ">")
        nl()
        buf("")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
        nl()
        buf("  ")
        buf("""article""")
        nl()
        buf("")
        buf("  ")
        buf("""  h2 {{title}}""")
        nl()
        buf("")
        buf("  ")
        buf("""  p {{description}}""")
        nl()
        buf("")
        buf("</" + "script" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "template.html")
      template_html() === testResult
    }
  }

  "text-block.jade" should {

    object text_block_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "label" + "" + ">")
        buf("""Username:""")
        nl()
        buf("  ")
        buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'user[name]'""" + "/>")
        nl()
        buf("")
        buf("</" + "label" + ">")
        nl()
        buf("")
        buf("<" + "label" + "" + ">")
        buf("""Password:""")
        nl()
        buf("  ")
        buf("<" + "input" + " " + "type" + "=" + """'text'""" + " " + "name" + "=" + """'user[pass]'""" + "/>")
        nl()
        buf("")
        buf("</" + "label" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "text-block.html")
      text_block_html() === testResult
    }
  }

  "text.jade" should {

    object text_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "option" + " " + "value" + "=" + """''""" + ">")
        buf("""-- (selected) --""")
        buf("</" + "option" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        nl()
        buf("  ")
        buf("""foo""")
        nl()
        buf("")
        buf("  ")
        buf("""bar""")
        nl()
        buf("")
        buf("  ")
        buf("""baz""")
        nl()
        buf("")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        nl()
        buf("  ")
        buf("""foo""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("  ")
        buf("""bar""")
        nl()
        buf("")
        buf("  ")
        buf("""baz""")
        nl()
        buf("")
        buf("  ")
        buf("""""")
        nl()
        buf("")
        buf("</" + "p" + ">")
        nl()
        buf("")
        buf("<" + "pre" + "" + ">")
        buf("""foo""")
        nl()
        buf("")
        buf("""  bar""")
        nl()
        buf("")
        buf("""    baz""")
        nl()
        buf("")
        buf(""".""")
        buf("</" + "pre" + ">")
        nl()
        buf("")
        buf("<" + "pre" + "" + ">")
        buf("""foo""")
        nl()
        buf("")
        buf("""  bar""")
        nl()
        buf("")
        buf("""    baz""")
        nl()
        buf("")
        buf(""".""")
        buf("</" + "pre" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "text.html")
      text_html() === testResult
    }
  }

  "utf8bom.jade" should {

    object utf8bom_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "p" + "" + ">")
        buf(""""foo"""")
        buf("</" + "p" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "utf8bom.html")
      utf8bom_html() === testResult
    }
  }

  "vars.jade" should {

    object vars_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        val foo = ("bar")

        val list = ((1 :: 2 :: 3 :: Nil).mkString(" "))

        buf("<" + "a" + boolAttr(list).map(v => if (v) { " " + "class" } else "").getOrElse(falsy(list).map(v => " " + "class" + "=" + "'" + escape(v) + "'").getOrElse("")) + boolAttr(foo).map(v => if (v) { " " + "id" } else "").getOrElse(falsy(foo).map(v => " " + "id" + "=" + "'" + escape(v) + "'").getOrElse("")) + ">")
        buf("</" + "a" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "vars.html")
      vars_html() === testResult
    }
  }

  "yield-before-conditional-head.jade" should {

    object yield_before_conditional_head_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "head" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        if (false) {
          nl()
          buf("  ")
          buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
          buf("</" + "script" + ">")
        }
        nl()
        buf("")
        buf("</" + "head" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "yield-before-conditional-head.html")
      yield_before_conditional_head_html() === testResult
    }
  }

  "yield-before-conditional.jade" should {

    object yield_before_conditional_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")
        if (false) {
          nl()
          buf("      ")
          buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
          buf("</" + "script" + ">")
        }
        nl()
        buf("    ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "yield-before-conditional.html")
      yield_before_conditional_html() === testResult
    }
  }

  "yield-head.jade" should {

    object yield_head_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "head" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("</" + "head" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "yield-head.html")
      yield_head_html() === testResult
    }
  }

  "yield-title-head.jade" should {

    object yield_title_head_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "head" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "title" + "" + ">")
        buf("</" + "title" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("  ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("")
        buf("</" + "head" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "yield-title-head.html")
      yield_title_head_html() === testResult
    }
  }

  "yield-title.jade" should {

    object yield_title_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("      ")
        buf("<" + "title" + "" + ">")
        buf("""My Title""")
        buf("</" + "title" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "yield-title.html")
      yield_title_html() === testResult
    }
  }

  "yield.jade" should {

    object yield_html {
      import com.github.btd.jade.Template._

      def apply() = {
        val builder = new collection.mutable.StringBuilder
        var firstLine = true
        def buf(str: String) {
          builder ++= str
          firstLine = false
        }
        def nl() = if (!firstLine) buf("\n")
        nl()
        buf("")
        buf("<" + "html" + "" + ">")
        nl()
        buf("  ")
        buf("<" + "body" + "" + ">")
        nl()
        buf("    ")
        buf("<" + "head" + "" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/caustic.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/app.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("      ")
        buf("<" + "script" + " " + "src" + "=" + """'/jquery.ui.js'""" + ">")
        buf("</" + "script" + ">")
        nl()
        buf("    ")
        buf("</" + "head" + ">")
        nl()
        buf("  ")
        buf("</" + "body" + ">")
        nl()
        buf("")
        buf("</" + "html" + ">")

        builder.toString

      }

    }

    "be equal expected html" in {
      val (testFileName, testResult) = Jade.sourceLoader.getInput("/" + "yield.html")
      yield_html() === testResult
    }
  }

}
