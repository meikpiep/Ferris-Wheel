import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import ru.github.igla.ferriswheel.Configuration

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    alias(libs.plugins.roborazzi)
}

val groupId = "com.github.meikpiep"
val artifact = "ferriswheel"

android {
    namespace = "$groupId.${artifact}screenshottests"
    compileSdkVersion = Configuration.COMPILE_SDK
    buildToolsVersion = Configuration.BUILD_TOOLS

    defaultConfig {
        minSdk = 23 // needed by Roborazzi
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true

        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    lint {
        abortOnError = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}

dependencies {
    testImplementation(project(":ferriswheel"))
    testImplementation(libs.bundles.screenshotTests)
}
