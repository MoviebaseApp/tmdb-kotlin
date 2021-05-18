object Versions {
    val versionMajor = 0
    val versionMinor = 5
    val versionPatch = 0

    val versionName = "$versionMajor.$versionMinor.$versionPatch"

    // Plugins
    val dokka = "1.4.30"
    val nexus = "1.0.0"
    val swiftpackage = "2.0.3"

    // Kotlin
    val kotlin = "1.4.32"
    val coroutines = "1.4.3"
    val kotlinxDatetime = "0.1.1"
    val serialization = "1.1.0"
    val kotlinIo = "0.1.16"

    // Testing
    val junitJupiter = "5.7.0"

    // Data
    val ktor = "1.5.3"
}

object Plugins {
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
    val swiftpackage = "com.chromaticnoise.multiplatform-swiftpackage"
}

object Libs {

    object Kotlin {
        val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
        val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
        val kotlinIo = "org.jetbrains.kotlinx:kotlinx-io:${Versions.kotlinIo}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}-native-mt"
    }

    object Data {
        val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val ktorJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        val ktorIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
        val ktorAuth = "io.ktor:ktor-client-auth:${Versions.ktor}"
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
    }

}
