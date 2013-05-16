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