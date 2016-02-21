# OSGiJavaFXUsage

This illustrates the usage of [winnall/OSGiJavaFX][osgi-javafx].

The bundle `Usage.java` creates an instance of `JavaFX` (from [winnall/OSGiJavaFX][osgi-javafx]). The name of the JavaFX `Application` to be executed is passed to the method `launch()` of `JavaFX`. In this case it is the classic JavaFX "hello world" application.

The bundle `HelloWorld.java` contains the "hello world" application. Note that this bundle is compiled as a declarative
service and not as a main class replacement as is typical with JavaFX POJOs.

The accompanying `pom.xml` directs Maven to compile `OSGiJavaFXUsage` and places all the relevant JARs (including the one from [winnall/OSGiJavaFX][osgi-javafx]) into the directory `target/deploy`. If all the files in this directory are deployed appropriately into [Karaf][karaf] or another OSGi framework (or [Felix][felix], anyway), the "hello world" application runs.

[osgi-javafx]: https://github.com/winnall/OSGiJavaFX.git
[karaf]: http://karaf.apache.org/
[felix]: http://felix.apache.org/
