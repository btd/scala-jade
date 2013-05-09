val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
builder ++= ("foo")
builder ++= ("</" + "a" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'#'""" + ">")
builder ++= ("bar")
builder ++= ("</" + "a" + ">")
builder ++= ("</" + "li" + ">")
builder ++= ("</" + "ul" + ">")
builder ++= ("<" + "p" + "" + ">")
builder ++= ("baz")
builder ++= ("</" + "p" + ">")

builder.toString
