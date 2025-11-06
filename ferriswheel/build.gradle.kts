import ru.github.igla.ferriswheel.Configuration

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    alias(libs.plugins.roborazzi)
}

val groupId = "com.github.meikpiep"
val libraryName = "ferriswheel"
val artifact = "ferriswheel"
val libraryVersion = "1.3.3-SNAPSHOT"
val libraryDescription = "Simple android library to present an animated ferris wheel"

group = groupId
version = libraryVersion

android {
    namespace = "$groupId.$artifact"

    compileSdkVersion = Configuration.compileSdk
    buildToolsVersion = Configuration.buildTools

    defaultConfig {
        minSdk = Configuration.minSdk
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = groupId
            artifactId = artifact
            version = libraryVersion

            //afterEvaluate {
            //    from(components["release"])
            //}

            pom {
                licenses {
                    license {
                        name = "The Apache Software License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "meikpiep"
                        name = "Meik Piepmeyer"
                        email = "meik.piepmeyer@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/meikpiep/Ferris-Wheel.git"
                    url = "https://github.com/meikpiep/Ferris-Wheel"
                }
            }
        }
    }
}

dependencies {
    testImplementation(libs.bundles.screenshotTests)
}