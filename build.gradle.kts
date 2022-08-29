buildscript {

    repositories { mavenCentral() }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}