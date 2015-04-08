concur5
============

This gives sample Scala and Java code for the
[fifth article in my JVM Concurrency series](http://www.ibm.com/developerworks/library/j-jvmc5/index.html) on IBM
developerWorks. The project uses a Maven build, so just do the usual `mvn clean install` to get
everything to a working state. The code is three separate packages:

1. `com.sosnoski.concur.article5scala`, within the *main/scala* tree.
2. `com.sosnoski.concur.article5java`, within the *main/java* tree.
3. `com.sosnoski.concur.article5java8`, within the *main/java* tree.

The demonstration code for this article doesn't do much, but if you want to try it out you can run
the Scala demonstration code from the command line with
`mvn scala:run -Dlauncher={name}`, where {name} selects the test code:

1. `hello1` - Simple actor hello
2. `hello2` - Stateful actor hello
3. 'hello3' - Actor properties and interactions
4. 'hello4' - Asking vs telling

You can import the project into ScalaIDE with the standard Maven project import handling, and both Scala
and Java versions can be executed from within the IDE.
