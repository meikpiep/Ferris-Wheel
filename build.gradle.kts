import com.github.meikpiep.ferriswheel.Configuration

buildscript {

    val kotlinVersion = Configuration.
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.6.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

