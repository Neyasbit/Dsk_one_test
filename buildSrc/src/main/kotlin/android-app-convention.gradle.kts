/**
 * plugins used in the all modules project
 */
plugins {
    id("com.android.library")
    kotlin("android")
}


project.apply(from = "${project.rootDir}/codequality/detekt.gradle")

@Suppress("UnstableApiUsage")
android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {

        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
        freeCompilerArgs = listOf("-Xjvm-default=compatibility", "-opt-in=kotlin.RequiresOptIn")

    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    android.sourceSets.all {
        java.srcDirs("src/$name/kotlin")
    }
}