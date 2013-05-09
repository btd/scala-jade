val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "html" + "" + ">")
builder ++= ("<" + "body" + "" + ">")
builder ++= ("<" + "h1" + "" + ">")
builder ++= ("Title")
builder ++= ("</" + "h1" + ">")
builder ++= ("</" + "body" + ">")
builder ++= ("</" + "html" + ">")

builder.toString
