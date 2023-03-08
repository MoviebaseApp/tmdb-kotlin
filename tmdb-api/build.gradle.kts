import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ben.manes.versions)
    id("maven-publish")
    id("signing")
}

kotlin {
    jvm()
    js(IR) {
        browser()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization)
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.core)
                implementation(libs.ktor.json)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.auth)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotations)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
                implementation(libs.ktor.mock)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.kotlin.junit5)
                implementation(libs.junit)
                implementation(libs.junit.jupiter.api)
                runtimeOnly(libs.junit.jupiter.engine)
                implementation(libs.truth)
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

// Adding empty javadoc
val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    publishing {
        publications {
            withType<MavenPublication> {
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
    }

    signing {
        sign(publishing.publications)
    }
}

// skip signing for SNAPSHOT builds
tasks.withType<Sign>().configureEach {
    onlyIf { !project.version.toString().endsWith("SNAPSHOT") }
}

// Workaround for optimization publishing issue, see https://youtrack.jetbrains.com/issue/KT-46466
val signingTasks = tasks.withType<Sign>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(signingTasks)
}
