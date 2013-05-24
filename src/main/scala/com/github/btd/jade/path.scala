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

object Path {
  val sep = java.io.File.separator

  def isAbsolute(path: String) = path.startsWith(sep)

  def extname(path: String) = {
    val lastPoint = path.lastIndexOf(".")
    if (lastPoint > 0) path.substring(lastPoint + 1) else ""
  }

  def filename(path: String) = {
    val lastSep = path.lastIndexOf(sep)
    if (lastSep > 0) path.substring(lastSep + sep.length) else ""
  }

  def basename(path: String) = {
    val name = filename(path)
    val lastPoint = name.lastIndexOf(".")
    if (lastPoint > 0) name.substring(0, lastPoint) else name
  }

  def dirname(path: String) = {
    val lastSep = path.lastIndexOf(sep)
    if (lastSep > 0) path.substring(0, lastSep) else "."
  }

  def join(paths: String*) =
    paths.zipWithIndex.map { p =>
      val startIdx = if (p._2 != 0 && p._1.startsWith(sep)) sep.length else 0
      val endIdx = if (p._1.endsWith(sep)) p._1.length - sep.length else p._1.length
      p._1.substring(startIdx, endIdx)
    }.mkString(sep)
}