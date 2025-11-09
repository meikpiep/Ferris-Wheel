Change Log
==========

Unreleased
----------

Version 2.0.0 *(2025-11-09)*
----------------------------

Changed implementation of scaling of the Ferris wheel, still ongoing.
This version will try to support broad variants in terms of sizes:

* Relevant enhancements on large sizes like 600 dp x 600 dp and above.
* Small sizes like 200 dp x 200 dp are supported and will draw a recognizable Ferris wheel. Previous
  versions failed using these sizes.

There is still room for enhancements, one to mention: Huge sizes are a bit inelegant.

May include regressions as not every variant got tested, just the ones relevant for app [Gauguin](https://github.com/meikpiep/gauguin).

* Use Kotlin version 2.2.21 (was 2.0.20)
* Use Gradle version 9.2.0 (was 8.9)
* Use Android Gradle Plugin 8.11.1 (was 8.6.1)
* Upgrade all other dependencies

Version 1.3.2 *(2024-09-23)*
----------------------------

Change group id from 'org.piepmeyer' to 'com.github.meikpiep' to comply with JitPack.

Version 1.3.1 *(2024-09-21)*
----------------------------

Adds build metadata for JitPack to fix its build.

Version 1.3.0 *(2024-09-21)*
----------------------------

First release of fork with group and artifact id 'org.piepmeyer.ferriswheel'.
Updated all versions used by this project.

 * Use Kotlin version 2.0.20 (was 1.2.60)
 * Use Gradle version 8.9 (was 4.8)
 * Use Android SDK 35 (was 27)

Will be distributed via JitPack (was JCenter).


Version 1.2 *(2018-08-03)*
--------------------------

 * Add new API to add click listener for cabin
 * Add isAutoRotate attribute
 * Use kotlin plugin version 1.2.60
 
 
Version 1.1.0 *(2018-03-24)*
----------------------------

 * Add new API to change cabin and wheel color, see monochrome sample
 * Reduce methods count
 * Use kotlin plugin version 1.2.31
 
 
Version 1.0.1 *(2018-03-16)*
----------------------------

 * Simplify API to start wheel animation
 * Fix: replace multiple drawLine calls with drawLines while drawing the wheel


Version 1.0.0 *(2018-03-02)*
----------------------------

Initial release.