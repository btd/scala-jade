package com.github.btd.jade

import java.io._

object Main extends App {
  val tokens = new com.github.btd.jade.Parser(io.Source.fromFile(new java.io.File("test.jade")).getLines.mkString("\n")).parse
  println(tokens)
  println(new Compiler(tokens, true).compile)
}

object GenTests extends App {
  val testCasesJadeDir = "./jade/test/cases"
  val testOutDir = "./src/test/scala/cases"

  val outdir = new java.io.File(testOutDir)
  outdir.mkdirs

  val dir = new java.io.File(testCasesJadeDir)
  val N = 26
  var i = 0
  for {
    test <- dir.list
    if test.endsWith(Jade.fileExt) && i < N
  } {
    i += 1
    println(">>>>>>>>>>>>>>>>> " + test.toUpperCase)
    val testCase = io.Source.fromFile(new java.io.File(testCasesJadeDir, test)).getLines.mkString("\n")

    val parser = new Parser(testCase)

    val compiler = new Compiler(parser.parse, true)

    val writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(testOutDir, test + ".scala"))))

    val objName = test.replaceAll(".jade", ".html").replaceAll("\\.|-", "_")

    writer.write {
      s"""
package com.github.btd.jade.cases

import org.specs2.mutable._

class ${test.replaceAll("\\.|-", "_")}Spec extends Specification {
  "$test" should {

    object ${objName} {
      import com.github.btd.jade.Template._

      def apply() = {
        ${compiler.compile}
      }
    }

    "be equal expected html" in {
      val testCaseHtml = io.Source.fromFile(new java.io.File("$testCasesJadeDir", "${test.replaceAll(".jade", ".html")}")).getLines.mkString("\\n")
      ${objName}() === testCaseHtml
    }
  }
}
    """
    }

    writer.flush
    writer.close
  }
}