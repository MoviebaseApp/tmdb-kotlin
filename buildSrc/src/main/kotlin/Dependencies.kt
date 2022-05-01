object Versions {
    val versionMajor = 0
    val versionMinor = 6
    val versionPatch = 6

    val versionName = "$versionMajor.$versionMinor.$versionPatch"

    // Plugins
    val dokka = "1.4.30"
    val nexus = "1.0.0"
    val benManesVersions = "0.42.0"

    // Kotlin
    val kotlin = "1.6.10"
    val coroutines = "1.6.0"
    val kotlinxDatetime = "0.3.1"
    val serialization = "1.3.2"
    val kotlinIo = "0.1.16"

    // Data
    val ktor = "2.0.0"

    // Testing
    val junitJupiter = "5.8.0"
    val truth = "1.1.3"
    val junit = "4.13.2"
    val mockito = "3.12.4"
    val mockitoKotlin = "3.2.0"
}

object Plugins {
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
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
        val ktorSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        val ktorAuth = "io.ktor:ktor-client-auth:${Versions.ktor}"
    }

    object Testing {
        val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        val ktorClientMock = "io.ktor:ktor-client-mock:${Versions.ktor}"
        val truth = "com.google.truth:truth:${Versions.truth}"
        val junit = "junit:junit:${Versions.junit}"
        val jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
        val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
        val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
        val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    }
}
