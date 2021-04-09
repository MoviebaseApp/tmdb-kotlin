object Versions {
    val versionMajor = 0
    val versionMinor = 3
    val versionPatch = 3

    val versionName = "$versionMajor.$versionMinor.$versionPatch"

    val minSdk = 21
    val targetSdk = 30
    val compileSdk = 30
    val buildTools = "30.0.2"

    // Plugins
    val androidGradle = "4.1.3"
    val androidJunit = "1.7.1.1"
    val dokka = "1.4.30"
    val nexus = "1.0.0"

    // Kotlin
    val kotlin = "1.4.32"
    val coroutines = "1.4.3"
    val kotlinxDatetime = "0.1.1"
    val ktor = "1.5.2"
    val serialization = "1.1.0"
    val kodein = "7.3.0"
    val kotlinIo = "0.1.16"

    // AndroidX
    val appCompat = "1.2.0"
    val constraintLayout = "2.1.0-alpha2"
    val lifecycle = "2.2.0"
    val preference = "1.1.1"
    val navigation = "2.3.3"
    val recyclerView = "1.2.0-beta01"
    val swipeRefreshLayout = "1.1.0"

    // Google
    val material = "1.3.0"
    val billing = "3.0.2"
    val dagger = "2.31.2"
    val hiltAndroid = "1.0.0-alpha03"
    val hilt = "2.31.2-alpha"

    // User Interface
    val glide = "4.12.0"

    // Testing
    val junitJupiter = "5.7.0"

}

object Libs {

    val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    val androidJunit = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJunit}"
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"

    object Kotlin {
        val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
        val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
        val kotlinIo = "org.jetbrains.kotlinx:kotlinx-io:${Versions.kotlinIo}"

        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}-native-mt"
        val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val coroutinesServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"
    }

    object AndroidX {
        val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
        val cardView = "androidx.cardview:cardview:1.0.0"
        val browser = "androidx.browser:browser:1.3.0"
        val annotation = "androidx.annotation:annotation:1.1.0"
        val preference = "androidx.preference:preference:${Versions.preference}"
        val preferenceKtx = "androidx.preference:preference-ktx:${Versions.preference}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val coreKtx = "androidx.core:core-ktx:1.3.1"
        val paging = "androidx.paging:paging-runtime:2.1.2"
        val pagingKtx = "androidx.paging:paging-runtime-ktx:2.1.2"
        val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-rc02"
        val activityKtx = "androidx.activity:activity-ktx:1.2.0-rc01"
        val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
        val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
        val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
        val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"
        val viewPager2 = "androidx.viewpager2:viewpager2:1.1.0-alpha01"
        val collection = "androidx.collection:collection:1.1.0"
        val work = "androidx.work:work-runtime-ktx:2.5.0"
        val workMultiprocess = "androidx.work:work-multiprocess:2.5.0"
        val webkit = "androidx.webkit:webkit:1.4.0"
        val security = "androidx.security:security-crypto:1.1.0-alpha03"
        val drawerLayout = "androidx.drawerlayout:drawerlayout:1.1.1"
        val hiltWork = "androidx.hilt:hilt-work:${Versions.hiltAndroid}"
        val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroid}"
        val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltAndroid}"
    }

    object Google {
        val material = "com.google.android.material:material:${Versions.material}"
        val billing = "com.android.billingclient:billing:${Versions.billing}"
        val billingKtx = "com.android.billingclient:billing-ktx:${Versions.billing}"
        val playCore = "com.google.android.play:core:1.9.1"
        val playOss = "com.google.android.gms:play-services-oss-licenses:17.0.0"
        val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Firebase {
        val firebaseBom = "com.google.firebase:firebase-bom:26.4.0"
        val firebaseKtx = "com.google.firebase:firebase-common-ktx"
        val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
        val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        val firebaseConfig = "com.google.firebase:firebase-config-ktx"
        val firebasePerf = "com.google.firebase:firebase-perf"
        val firestore = "com.google.firebase:firebase-firestore-ktx"
        val firebaseMessaging = "com.google.firebase:firebase-messaging"
        val firebaseAuth = "com.google.firebase:firebase-auth"
        val firebaseLinks = "com.google.firebase:firebase-dynamic-links"
        val firebaseAds = "com.google.firebase:firebase-ads"
        val firebaseInApp = "com.google.firebase:firebase-inappmessaging-display-ktx"
        val firebaseFunctions = "com.google.firebase:firebase-functions-ktx"
        val firebaseAuthUi = "com.firebaseui:firebase-ui-auth:7.1.1"
        val firebaseStorage = "com.google.firebase:firebase-storage-ktx"
    }

    object Ui {
        val glideRecyclerView = "com.github.bumptech.glide:recyclerview-integration:4.12.0"
    }

    object Data {
        val tmdbApi = "app.moviebase:tmdb-api:0.2.0"

        val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val ktorJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        val ktorIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    }

    object Util {
        val kodein = "org.kodein.di:kodein-di:${Versions.kodein}"
        val timber = "com.jakewharton.timber:timber:4.7.1"
        val dagger = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
        val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        val threeTenAbpAndroid = "com.jakewharton.threetenabp:threetenabp:1.3.0"
        val inject = "javax.inject:javax.inject:1"

        val androidKtx = "app.moviebase:android-ktx:1.3.3"
        val androidElements = "app.moviebase:android-elements:1.6.0"
    }

    object Testing {
        val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        val ktorClientMock = "io.ktor:ktor-client-mock:${Versions.ktor}"
        val truth = "com.google.truth:truth:1.1.2"
        val junit = "junit:junit:4.13.2"
        val jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
        val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
        val mockito = "org.mockito:mockito-inline:3.8.0"
        val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        val robolectric = "org.robolectric:robolectric:4.4"
        val androidCoreTesting = "androidx.arch.core:core-testing:2.1.0"
    }

}
