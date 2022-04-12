import org.gradle.api.JavaVersion

object Modules {

}

object Config {
    const val application_id = "ru.edamamlearning.graduationproject"
    const val compile_sdk = 32
    const val min_sdk = 27
    const val target_sdk = 32
    const val jvm_target = "1.8"
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Versions {

    //Glide
    const val glide = "4.11.0"

    //Room
    const val room = "2.4.1"

    //Jetpack Navigation
    const val jetNav = "2.4.1"

    //Kotlin coroutines
    const val coroutinesCore = "1.6.0"
    const val coroutinesAndroid = "1.6.0"

    //RxJava
    const val rxJava = "3.0.0"

    //Lifecycle KTX
    const val lifecycleViewModel = "2.4.0"
    const val lifecycleRunTime = "2.4.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val retrofitAdapterRxJava = "2.9.0"

    //OkHttp
    const val okHttpVer = "4.9.3"

    //Kotlin ktx
    const val kotlinCoreKtx = "1.7.0"

    //Appcompat
    const val appCompat = "1.4.0"

    //Material
    const val material = "1.4.0"

    //constraintLayout
    const val constraintLayout = "2.1.2"

    //Dagger
    const val dagger = "2.41"

    //Test
    const val jUnit = "4.13.2"
    const val extjUnit = "1.1.3"
    const val espressoCore = "3.4.0"
    const val mockito = "4.0.0"
    const val mockWebServer = "4.9.1"
    const val archCore = "2.1.0"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object JetpackNavigation {
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.jetNav}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.jetNav}"
}

object Coroutines {
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object RxJava {
    const val rx_android = "io.reactivex.rxjava3:rxandroid:${Versions.rxJava}"
    const val rx_java = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
}

object LifecycleComponents {
    const val viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRunTime}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_adapter_rx =
        "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitAdapterRxJava}"
}

object OkHttpDeps {
    const val ok_http = "com.squareup.okhttp3:okhttp:${Versions.okHttpVer}"
    const val ok_http_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVer}"
}

object Kotlin {
    const val kotlin_cote_ktx = "androidx.core:core-ktx:${Versions.kotlinCoreKtx}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Dagger {
    const val dagger_d = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_k = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"

    const val ext_junit = "androidx.test.ext:junit:${Versions.extjUnit}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val fragment_testing = "androidx.fragment:fragment-testing:1.4.1"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:3.4.0"
    const val navigation_testing = "androidx.navigation:navigation-testing:${Versions.jetNav}"

    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val mock_inline = "org.mockito:mockito-inline:${Versions.mockWebServer}"

    const val arch_core = "androidx.arch.core:core-testing:${Versions.archCore}"

    const val ui_automator = "androidx.test.uiautomator:uiautomator:2.2.0"
    const val test_rules = "androidx.test:rules:1.4.0"

    const val robolectric = "org.robolectric:robolectric:4.5.1"
    const val test_core = "androidx.test:core:1.4.0"
    const val test_runner = "androidx.test:runner:1.4.0"
    const val ext_truth = "androidx.test.ext:truth:1.4.0"
    const val espresso_intents = "androidx.test.espresso:espresso-intents:3.4.0"

    const val coroutine_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0"
}