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

import com.typesafe.scalalogging.slf4j._
import java.io._

object Main extends App with Logging {
  Jade.sourceLoader = new FileSourceLoader("./jade/test/cases" :: Nil)

  val testCase = "/utf8bom.jade"

  val (filename, content) = Jade.sourceLoader.getInput(testCase)
  val tokens = new Parser(content, filename).parse
  println(tokens)
  println(new Compiler(tokens, true).compile)
}

object GenTests extends App with Logging {
  val testCasesJadeDir = "./jade/test/cases"
  val testOutDir = "./src/test/scala/cases"

  val outdir = new java.io.File(testOutDir)
  outdir.mkdirs

  Jade.sourceLoader = new FileSourceLoader(testCasesJadeDir :: Nil)

  val dir = new java.io.File(testCasesJadeDir)

  for {
    test <- dir.list
    if test.endsWith(Jade.fileExt)
  } {
    println(">>>>>>>>>>>>>>>>> " + test.toUpperCase)
    val (filename, content) = Jade.sourceLoader.getInput("/" + test)
    val parser = new Parser(content, filename)

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