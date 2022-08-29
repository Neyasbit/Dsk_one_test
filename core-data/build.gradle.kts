plugins {
    id("android-app-convention")
    id("kotlinx-serialization")
}


dependencies {
    api(project(Modules.Base.CORE_MODEL))
    api(Libs.RxKotlin.CORE)
    api(Libs.KotlinJson.CORE)
    api(Libs.JakeWharton.THREETEN)

    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.ANDROID_JUNIT)

    
    implementation("com.github.akarnokd:rxjava3-extensions:3.1.1")
}