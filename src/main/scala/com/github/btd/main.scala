package com.github.btd.jade

import com.typesafe.scalalogging.slf4j._
import java.io._

object Main extends App with Logging {
  val testCasesJadeDir = "./jade/test/cases"
  Jade.getInput = (name: String) => {
    val fileName = Path.basename(name)
    val subDirName = Path.dirname(name)

    val ext = empty(Path.extname(name)).map("." + _).getOrElse(Jade.fileExt)

    logger.debug("Get input " + name)
    val path = if (Path.isAbsolute(name)) Path.join(testCasesJadeDir, subDirName, fileName + ext) else Path.join(subDirName, fileName + ext)
    logger.debug("Try to find file " + path)
    (Path.join(subDirName, fileName + ext), io.Source.fromFile(path).getLines.mkString("\n"))
  }
  val testCase = "include.yield.nested.jade"
  val tokens = new Parser(io.Source.fromFile(new java.io.File(testCasesJadeDir + "/" + testCase)).getLines.mkString("\n"), testCasesJadeDir + "/" + testCase).parse
  println(tokens)
  println(new Compiler(tokens, true).compile)
}

object GenTests extends App with Logging {
  val testCasesJadeDir = "./jade/test/cases"
  val testOutDir = "./src/test/scala/cases"

  val outdir = new java.io.File(testOutDir)
  outdir.mkdirs

  val dir = new java.io.File(testCasesJadeDir)

  def tryFoundFileByName(name: String) = {
    val path = if (Path.isAbsolute(name)) Path.join(testCasesJadeDir, name) else name
    val file = new File(path)
    if (!file.exists) None
    else {
      Some((name, io.Source.fromFile(file).getLines.mkString("\n")))
    }
  }

  Jade.getInput = (name: String) => {
    tryFoundFileByName(name).orElse(tryFoundFileByName(name + Jade.fileExt)).getOrElse(throw new Exception(s"File $name not found"))
  }

  val N = 42
  var i = 0
  for {
    test <- dir.list
    if test.endsWith(Jade.fileExt) && i < N
  } {
    i += 1
    println(">>>>>>>>>>>>>>>>> " + test.toUpperCase)
    val testCase = io.Source.fromFile(new java.io.File(testCasesJadeDir, test)).getLines.mkString("\n")

    val parser = new Parser(testCase, testCasesJadeDir + "/" + test)

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