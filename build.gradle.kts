import java.net.URI

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.11.1")
    }
}

plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.roborazzi) apply false
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = URI("https://jitpack.io") }
    }
}

repositories {
    mavenCentral()
}
