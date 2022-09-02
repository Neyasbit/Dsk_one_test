object Libs {

    object BuildPlugins {
        const val GRADLE = "com.android.tools.build:gradle:7.0.4"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"
    }

    object AndroidX {
        private const val VERSION = "1.8.0"
        const val CORE = "androidx.core:core-ktx:$VERSION"
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"
    }

    object Compose {
        private const val VERSION = "1.1.0"
        const val UI = "androidx.compose.ui:ui:$VERSION"
        const val MATERIAL = "androidx.compose.material:material:$VERSION"
        const val UI_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$VERSION"
        const val ACTIVITY = "androidx.activity:activity-compose:1.5.0"
        const val TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val ANDROID_JUNIT = "androidx.test.ext:junit:1.1.3"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:1.1.1"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
    }

    object Arrow {
        private const val VERSION = "1.0.1"
        const val CORE = "io.arrow-kt:arrow-core:$VERSION"
    }

    object RxKotlin {
        private const val VERSION = "3.0.1"
        const val CORE = "io.reactivex.rxjava3:rxkotlin:$VERSION"
    }

    object KotlinJson {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0"
    }

    object Mockk {
        const val CORE = "io.mockk:mockk:1.12.7"
    }

    object JakeWharton {
        const val THREETEN = "com.jakewharton.threetenabp:threetenabp:1.3.1"
    }
}