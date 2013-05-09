val builder = new collection.mutable.StringBuilder
builder ++= ("<" + "script" + " " + "type" + "=" + """'text/x-template'""" + ">")
builder ++= ("<" + "div" + " " + "id" + "=" + """'user'""" + ">")
builder ++= ("(id!='user-<%= user.id %>')")
builder ++= ("<" + "h1" + "" + ">")
builder ++= ("<%= user.title %>")
builder ++= ("</" + "h1" + ">")
builder ++= ("</" + "div" + ">")
builder ++= ("</" + "script" + ">")

builder.toString
