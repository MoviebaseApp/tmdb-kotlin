import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("maven-publish")
    id("signing")
    id("com.github.ben-manes.versions") version Versions.benManesVersions
}

group = "app.moviebase"
version = Versions.versionName

kotlin {
    jvm()
    js {
        browser()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Kotlin.coroutines)
                implementation(Libs.Kotlin.kotlinSerialization)
                implementation(Libs.Kotlin.kotlinxDateTime)
                api(Libs.Kotlin.kotlinIo)

                implementation(Libs.Data.ktorCore)
                implementation(Libs.Data.ktorJson)
                implementation(Libs.Data.ktorLogging)
                implementation(Libs.Data.ktorSerialization)
                implementation(Libs.Data.ktorContentNegotiation)
                implementation(Libs.Data.ktorAuth)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Libs.Data.ktorOkhttp)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation(Libs.Data.ktorOkhttp)

                implementation(Libs.Kotlin.coroutines)
                implementation(Libs.Testing.jupiter)
                runtimeOnly(Libs.Testing.jupiterEngine)
                implementation(Libs.Testing.truth)
                implementation(Libs.Testing.ktorClientMock)
            }
        }

        val jsMain by getting {

        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.freeCompilerArgs += "-Xjvm-default=all"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("failed")
        showStandardStreams = true
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    extensions.findByType<PublishingExtension>()?.apply {
        publications.withType<MavenPublication>().configureEach {
            artifact(javadocJar.get())
            pom {
                name.set("Kotlin Multiplatform TMDB API")
                description.set("A Kotlin Multiplatform library to access the TMDB API.")
                url.set("https://github.com/MoviebaseApp/tmdb-api")
                inceptionYear.set("2021")

                developers {
                    developer {
                        id.set("chrisnkrueger")
                        name.set("Christian Krüger")
                        email.set("christian.krueger@moviebase.app")
                    }
                }
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/MoviebaseApp/tmdb-api/issues")
                }
                scm {
                    connection.set("scm:git:https://github.com/MoviebaseApp/tmdb-api.git")
                    developerConnection.set("scm:git:git@github.com:MoviebaseApp/tmdb-api.git")
                    url.set("https://github.com/MoviebaseApp/tmdb-api")
                }
            }
        }
    }
    signing {
        sign(publishing.publications)
    }
}
