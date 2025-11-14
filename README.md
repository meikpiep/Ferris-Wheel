# Ferris Wheel View

This is a fork of [iglaweb/Ferris-Wheel](https://github.com/iglaweb/Ferris-Wheel) which had its most
recent release in 2018 (until now) and its distribution repository was JCenter which was recently
shutdown completely. This fork aims to fill the gap and provide a ferris wheel with updated
dependencies and distribution via JitPack.

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

![image](/art/logo.png)


## Overview

An Android Library used to implement an animated Ferris Wheel in android.

- **API SDK 15+**
- **Written in [Kotlin](https://kotlinlang.org)**
- **Supports landscape mode**
- **Lightweight view with 1 drawable inside, uses canvas 2D drawing technique**

![Preview-demo](/art/preview_demo.gif "Preview demo")  ![Monochrome-sample](/art/preview_demo_monochrome.gif "Monochrome sample")<br />
Inspired by the one-color [Planet Coaster loading screen](https://youtu.be/5zHhW5TLW2s)

## Mentions

* Featured in [25 new Android libraries, projects and tools worthy to check in Spring 2018](https://medium.com/@mmbialas/25-new-android-libraries-projects-and-tools-worthy-to-check-in-spring-2018-68e3c5e93568)
* Mentioned in [Android Weekly issue 332](http://androidweekly.net/issues/issue-322)
* Featured in [12 New Android Libraries - Full HD](https://www.youtube.com/watch?v=22k5uak4yYg)

## Quick Setup

### Include library

**Using Gradle**

FerrisWheelView is distributed using [JitPack](https://jitpack.io/#meikpiep/Ferris-Wheel).
``` gradle
repositories { 
    jcenter()
}
dependencies {
    implementation 'com.github.meikpiep:Ferris-Wheel:1.3.2'
}
```

**Or Maven**

``` maven
<dependency>
  <groupId>com.github.meikpiep</groupId>
  <artifactId>Ferris-Wheel</artifactId>
  <version>1.3.2</version>
  <type>pom</type>
</dependency>
```

### Usage
Add widget in your xml layout like this:

```xml
    <ru.github.igla.ferriswheel.FerrisWheelView
        android:id="@+id/ferrisWheelView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center"
        app:fwv_cabinsNumber="8"
        app:fwv_rotateSpeed="6" />
```


To start animation you need only call this method:

``` kotlin
    ferrisWheelView.startAnimation()
```

Or you can stop/pause/resume animation by the following methods:
``` kotlin
    ferrisWheelView.stopAnimation()
    ferrisWheelView.pauseAnimation()
    ferrisWheelView.resumeAnimation()
```


## Attributes
| attr                     | format    | default                                                                                                |                       description                       |
|--------------------------|:----------|:-------------------------------------------------------------------------------------------------------|:-------------------------------------------------------:|
| fwv_cabinSize            | dimension | 42dp                                                                                                   |                 the size of each cabin                  |
| fwv_cabinsNumber         | integer   | 8                                                                                                      |            the number of cabins on the wheel            |
| fwv_isClockwise          | boolean   | true                                                                                                   |               toogle the rotate direction               |
| fwv_isAutoRotate         | boolean   | true                                                                                                   | start rotating wheel automatically after initialization |
| fwv_rotateSpeed          | integer   | 6                                                                                                      |        wheel speed rotation measured in degrees         |
| fwv_startAngle           | float     | 0                                                                                                      |        angle at which wheel will start to rotate        |
| fwv_wheelStrokeColor     | color     | ![#73302b](https://placehold.it/15/73302b/000000?text=+) `#73302b`                                     |        with this color the wheel will be filled         |
| fwv_baseStrokeColor      | color     | ![#666666](https://placehold.it/15/666666/000000?text=+) `#666666`                                     |      with this color the wheel base will be filled      |
| fwv_cabinFillColor       | color     | [array](https://github.com/meikpiep/Ferris-Wheel/blob/main/ferriswheel/src/main/res/values/arrays.xml) |        with this color the cabin will be filled         |
| fwv_cabinLineStrokeColor | color     | ![#000000](https://placehold.it/15/000000/000000?text=+) `#000000`                                     |      with this color the cabin line will be filled      |

Issues
------

If you find any problems or would like to suggest a feature, please
feel free to file an [issue](https://github.com/meikpiep/Ferris-Wheel/issues)

## License

    Copyright 2018 Igor Lashkov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 [license-svg]: https://img.shields.io/badge/license-APACHE-lightgrey.svg
 [license-link]: https://github.com/meikpiep/Ferris-Wheel/blob/main/LICENSE
