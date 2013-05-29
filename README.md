Scala Jade
==========

Port of original javascript jade to scala.

It translate jade file to scala source file that you can invoke in your code as usual scala code.

How to run and play with it
===========================

See `main.scala` how i generate test cases from jade files.

In short it is:

```scala
import com.github.btd.jade._

Jade.sourceLoader = new FileSourceLoader("basedir" :: Nil)

val (filename, jadeText) = Jade.sourceLoader.getInput("jadefile.jade")
println(new Compiler(new Parser(jadeText, filename).parse, true).compile)
```


Contributing
============
 
* Check out the latest master to make sure the feature hasn't been implemented or the bug hasn't been fixed yet
* Check out the issue tracker to make sure someone already hasn't requested it and/or contributed it
* Fork the project
* Start a feature/bugfix branch
* Commit and push until you are happy with your contribution (make sensitive changes)
* Discuss with me, if your changes are big
* Please try not to mess with the build files, version, or history. If you want to have your own version, or is otherwise necessary, that is fine, but please isolate to its own commit so I can cherry-pick around it.
* By default i suppose that contributor agreed with Apache License and Individual Contributor License Agreement (http://www.apache.org/licenses/icla.txt).

License
=======

Copyright (c) 2013 Denis Bardadym. See LICENSE.txt for further details.