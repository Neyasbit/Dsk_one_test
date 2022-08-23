plugins {
    `kotlin-dsl`
}


repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    api("com.android.tools.build:gradle:7.0.4")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
}