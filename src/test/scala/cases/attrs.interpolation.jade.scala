val builder = new collection.mutable.StringBuilder
var id = 5
builder ++= ("<" + "a" + " " + "href" + "=" + """'/user/""" + id + """'""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "foo" + " " + "bar" + "=" + """'stuff #{here} yup'""" + ">")
builder ++= ("</" + "foo" + ">")

builder.toString
