package com.github.btd

package object jade {

  private[jade]type ?[T] = Option[T]

  private[jade] def empty(str: String) = if (str == null || str == "") None else Some(str)
}