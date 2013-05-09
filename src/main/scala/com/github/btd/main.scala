package com.github.btd.jade

import java.io._

object Main extends App {
  println(new com.github.btd.jade.Parser(io.Source.fromFile(new java.io.File("test.jade")).getLines.mkString("\n"), "test.jade").parse)
}

object GenTests extends App {
  val testCasesJadeDir = "./jade/test/cases"
  val testOutDir = "./src/test/scala/cases"

  val outdir = new java.io.File(testOutDir)
  outdir.mkdirs

  val dir = new java.io.File(testCasesJadeDir)
  for {
    test <- dir.list
    if test.endsWith(Jade.fileExt)
  } {
    val testCase = io.Source.fromFile(new java.io.File(testCasesJadeDir, test)).getLines.mkString("\n")

    val parser = new Parser(testCase, test)

    val compiler = new Compiler(parser.parse)

    val writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(testOutDir, test + ".scala"))))

    writer.write(compiler.compile)

    writer.flush
    writer.close
  }
}