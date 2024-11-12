import ru.github.igla.ferriswheel.Configuration

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

val groupId = "com.github.meikpiep"
val libraryName = "Ferris-Wheel"
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    lint {
        abortOnError = true
    }
}

project.afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = groupId
                artifactId = artifact
                version = libraryVersion

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
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

kotlin {
    jvmToolchain(8)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

