import com.github.meikpiep.ferriswheel.Configuration

plugins {
    "com.android.library"
    "org.jetbrains.kotlin.android"
    "maven-publish"
}

ext {

    libraryName = 'Ferris-Wheel'
    artifact = 'ferriswheel'
    libraryVersion = '1.3.3-SNAPSHOT'
    libraryDescription = 'Simple android library to present an animated ferris wheel'

    siteUrl = 'https://github.com/meikpiep/Ferris-Wheel'
    gitUrl = 'https://github.com/meikpiep/Ferris-Wheel.git'

    developerName = 'Meik Piepmeyer'
    developerEmail = 'meik.piepmeyer@gmail.com'

    licenseName = 'The Apache Software License, Version 2.0'
    licenseUrl = 'http://www.apache.org/licenses/LICENSE-2.0.txt'
    licenses = ["Apache-2.0"]
}

group = groupId
version = libraryVersion

android {
    namespace = groupId + '.' + artifact

    compileSdkVersion = Configuration.compileSdk
    buildToolsVersion = Configuration.buildTools
    defaultConfig {
        minSdkVersion = Configuration.minSdk
        targetSdkVersion = Configuration.targetSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles = listOf(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    publishing {
        singleVariant('release') {
            withSourcesJar()
        }
    }

    lintOptions {
        abortOnError = true
    }
}

project.afterEvaluate {
    publishing {
        publications {
            libraryProject(MavenPublication) {
                setGroupId Configuration.groupId
                setArtifactId Configuration.artifact
                version Configuration.libraryVersion
                artifact bundleReleaseAar
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

dependencies {
    //implementation("org.jetbrains.kotlin:kotlin-stdlib:${Configuration.kotlin}")
}
