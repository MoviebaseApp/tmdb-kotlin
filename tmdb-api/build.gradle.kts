import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("maven-publish")
    id("signing")
}

group = "app.moviebase"
version = Versions.versionName

kotlin {
    jvm()
    js {
        browser()
        nodejs()
    }
    iosArm64()
    iosX64()

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
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))

                implementation(Libs.Kotlin.coroutines)
                implementation(Libs.Testing.jupiter)
                runtimeOnly(Libs.Testing.jupiterEngine)
                implementation(Libs.Testing.truth)
                implementation(Libs.Testing.ktorClientMock)
            }
        }
        val jsMain by getting {
            dependencies {

            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            kotlin.srcDir("src/iosMain/kotlin")

            dependencies {
                implementation(Libs.Data.ktorIos)
            }
        }
        val iosTest by creating {
            dependsOn(commonTest)
            kotlin.srcDir("src/iosTest/kotlin")
            dependencies {
                implementation(Libs.Data.ktorIos)
            }
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)

        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Test by getting {
            dependsOn(iosTest)

        }
        val iosX64Test by getting {
            dependsOn(iosTest)
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-Xjvm-default=all"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
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
