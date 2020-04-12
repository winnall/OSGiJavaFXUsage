# OSGiJavaFXUsage

This illustrates the usage of [winnall/OSGiJavaFX][osgi-javafx].

The service `Usage.java` creates an instance of `JavaFXLauncher` (from [winnall/OSGiJavaFX][osgi-javafx]). The name of the JavaFX `Application` to be executed is passed to the method `JavaFXLauncher.launch()`. This `Application` must be implemented by subclassing `AbstractJavaFXApplication`, which is abstract only in concept (i.e. it is NOT an `abstract class`). In the case implemented in this example the application is the classic JavaFX "hello world".

The class `HelloWorld.java` contains the "hello world" application. Note that this class may NOT be compiled as a declarative service NOR as a main class replacement as is typical with JavaFX POJOs.

`HelloWorld.java` extends `AbstractJavaFXApplication` and the following conditions have to be fulfilled:

- `HellWorld.init()` must call `super.init()` (implicitly – as here – or explicitly), which registers the current class (`this`) as an OSGi service even though it has been created under JavaFX control. A variable `protected BundleContext bundlecontext`, which refers to the OSGi service thus registered, is also made available.
- `HelloWorld.start()` must call `super.start(primaryStage)` to make `primaryStage` available to its superclass and the two `onStage()` methods defined there; otherwise these two methods will not work.

If other OSGi services are to be referenced in `HelloWorld` (which is not the case in the present example), they can be made available with code similar to the following:

```java
  private XXX xxx;  // to hold the reference to the required service
  private ServiceTracker<XXX, XXX> xxxTracker;
  
  public void init() {
    super.init();
    try {
      // bundleContext is declared in the superclass
      xxxTracker = new ServiceTracker<>(bundleContext, XXX.class, null);
      xxxTracker.open();
      xxx = xxxTracker.waitForService(0);
    }...
  }
```

It is probably best – when using a `ServiceTracker` as above – to clean up on termination as follows:

```java
  public void stop() {
    super.stop();
    xxxTracker.close();
  }
```

`HelloWorld.java` looks in all other respects exactly the same as any other non-OSGi JavaFX start-up class.

The accompanying `pom.xml` directs Maven to compile `OSGiJavaFXUsage` and places all the relevant JARs (including the one from [winnall/OSGiJavaFX][osgi-javafx]) into the directory `target/deploy`. If all the files in this directory are deployed appropriately into [Karaf][karaf] or another OSGi framework (or [Felix][felix], anyway), the "hello world" application runs.

[osgi-javafx]: https://github.com/winnall/OSGiJavaFX.git
[karaf]: http://karaf.apache.org/
[felix]: http://felix.apache.org/
