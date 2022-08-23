plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false

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
dependencies {
    //implementation features
    //implementation(project(Modules.Base.CORE))
}