Differences with javascript Jade
================================

	1. For translated to scala for comprehension so traversing by indexes not supported but still possible.

	2. Yield with include is required and never used last block /include-with-text.jade and it replace all yields in included file

	3. Case translated to scala case, default it it case _ => ...

	4. Mixin call will happen only with +mixin_name(...)

	5. Mixin call could not have attributes