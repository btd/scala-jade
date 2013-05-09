val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "ul" + "" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("foo")
builder ++= ("</" + "li" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("bar")
builder ++= ("</" + "li" + ">")
builder ++= ("<" + "li" + "" + ">")
builder ++= ("baz")
builder ++= ("</" + "li" + ">")
builder ++= ("</" + "ul" + ">")

builder.toString
