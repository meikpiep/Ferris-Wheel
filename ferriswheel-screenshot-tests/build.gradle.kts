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
    compileSdkVersion = Configuration.compileSdk
    buildToolsVersion = Configuration.buildTools

    defaultConfig {
        minSdk = 23 // needed by Roborazzi
        targetSdk = Configuration.targetSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions += "version"

    productFlavors {
        create("development") {
            dimension = "version"
            minSdk = 23
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    testImplementation(project(":ferriswheel"))
    testImplementation(libs.bundles.screenshotTests)
}