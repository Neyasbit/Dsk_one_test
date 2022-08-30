plugins {
   id("android-app-convention")
}

dependencies {

    api(Libs.AndroidX.CORE)
    api(Libs.Compose.UI)
    api(Libs.Compose.MATERIAL)
    api(Libs.Compose.UI_PREVIEW)
    api(Libs.AndroidX.LIFECYCLE_RUNTIME)
    api(Libs.Compose.ACTIVITY)
    api(Libs.Compose.VIEW_MODEL)
    api(Libs.RxKotlin.CORE)

    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.5.1")

    testApi(Libs.Test.JUNIT)
    testApi(Libs.Test.COROUTINES)
    androidTestApi(Libs.Test.ANDROID_JUNIT)
    androidTestApi(Libs.Test.ESPRESSO)
    androidTestApi(Libs.Test.COMPOSE_JUNIT)

    debugApi(Libs.Compose.TOOLING)
}