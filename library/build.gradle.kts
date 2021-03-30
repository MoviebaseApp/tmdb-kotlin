plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("maven-publish")
    signing
    id("org.jetbrains.dokka")
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

    val publicationsFromMainHost = listOf(jvm(), js(), iosArm64(), iosX64()).map { it.name } + "kotlinMultiplatform"
    publishing {
        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
                    .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Kotlin.coroutines)
                implementation(Libs.Kotlin.kotlinSerialization)
                implementation(Libs.Kotlin.kotlinxDateTime)
                api(Libs.Kotlin.kotlinIo)

                implementation(Libs.Util.ktorCore)
                implementation(Libs.Util.ktorJson)
                implementation(Libs.Util.ktorLogging)
                implementation(Libs.Util.ktorSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {

        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))

                implementation(Libs.Testing.jupiter)
                runtimeOnly(Libs.Testing.jupiterEngine)
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

        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
//            kotlin.srcDir("src/iosMain/kotlin")

        }
        val iosX64Main by getting {
            dependsOn(iosMain)
//                        kotlin.srcDir("src/iosMain/kotlin")

        }
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "sonatype"
                if (Versions.versionName.endsWith("-SNAPSHOT"))
                    setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                else
                    setUrl("https://s01.oss.sonatype.org/service/local/")

                credentials {
                    username = findProperty("SONATYPE_USER") as String?
                    password = findProperty("SONATYPE_PASSWORD") as String?
                }
            }
        }
        publications.withType<MavenPublication>().configureEach {
            groupId = "app.moviebase"
            artifactId = "tmdb-kotlin"
            version = Versions.versionName
            artifact(javadocJar.get())

            pom {
                name.set("Multiplatform TMDB API")
                description.set("A Kotlin Multiplatform library to access the TMDB API.")
                url.set("https://github.com/MoviebaseApp/tmdb-kotlin")
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
                    url.set("https://github.com/MoviebaseApp/tmdb-kotlin/issues")
                }
                scm {
                    connection.set("scm:git:https://github.com/MoviebaseApp/tmdb-kotlin.git")
                    developerConnection.set("scm:git:git@github.com:MoviebaseApp/tmdb-kotlin.git")
                    url.set("https://github.com/MoviebaseApp/tmdb-kotlin")
                }
            }
        }
    }

    signing {
//        val publishing = extensions.findByType<PublishingExtension>() ?: return@signing
//        val key = properties["signingKey"]?.toString()?.replace("\\n", "\n")
//        val password = properties["signingPassword"]?.toString()
//
//        useInMemoryPgpKeys(key, password)
        sign(publishing.publications)
    }


//    tasks.withType<Sign>().configureEach {
//        onlyIf { isReleaseBuild }
//    }

}
val isReleaseBuild: Boolean
    get() = properties.containsKey("signingKey")
