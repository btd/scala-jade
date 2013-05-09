val builder = new collection.mutable.StringBuilder
var id = 5
def answer() = 42
builder ++= ("<" + "a" + " " + "href" + "=" + """'/user/""" + 42 + """'""" + " " + "class" + "=" + """'button'""" + ">")
builder ++= ("</" + "a" + ">")
builder ++= ("<" + "a" + " " + "href" + "=" + """'/user/""" + id + """'""" + " " + "class" + "=" + """'button'""" + ">")
builder ++= ("</" + "a" + ">")
val a = answer()
builder ++= ("<" + "meta" + " " + "key" + "=" + """'answer'""" + " " + "value" + "=" + "'" + a + "'" + ">")
builder ++= ("</" + "meta" + ">")

builder.toString
