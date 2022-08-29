plugins {
    id("android-app-convention")
}

dependencies {
    implementation(project(Modules.Base.CORE_MODEL))
    implementation(project(Modules.Base.CORE_DATA))
    api(project(Modules.Base.CORE))

    implementation("androidx.compose.runtime:runtime-rxjava3:1.2.1")
    implementation("io.coil-kt:coil-compose:2.2.0")
}