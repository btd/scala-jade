val builder = new collection.mutable.StringBuilder
val user = ("Tobi")
builder += ("<" + "foo" +  "data-user" + "=" + "'" + user + "'" + ">")
builder += ("</" + "foo" + ">")
builder += ("<" + "bar" +  "data-username" + "=" + "'tobi'" + ">")
builder += ("</" + "bar" + ">")

builder.toString
