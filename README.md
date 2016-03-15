Android Debug Grid
======================

Draws debug grid over activities in android application

Usage
-----

Add dependency in your _build.gradle_ 
```groovy
compile 'ru.rambler.android:debug-grid:0.2.1'
```

Build and show debug grid in Application.onCreate()

```java
new DebugGrid.Builder()
     .with(Line.fromLeft(72, TypedValue.COMPLEX_UNIT_DIP))
     .with(Line.fromRight(24, TypedValue.COMPLEX_UNIT_MM))
     .build(this);
```

Distributing
------------

This project is distributed under the terms of the Apache License v2.0. 
See LICENSE for details  