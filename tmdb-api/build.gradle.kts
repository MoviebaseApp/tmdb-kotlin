import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.dokka")
    id("maven-publish")
    id("signing")
}

group = "app.moviebase"
version = Versions.versionName

kotlin {
    jvm()
    android {
        publishLibraryVariants("release", "debug")
    }
    js {
        browser()
        nodejs {
            val compilations = compilations as NamedDomainObjectContainer<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinJsCompilation>
            val main by compilations.getting {
                compileKotlinTask.kotlinOptions {
                    moduleKind = "commonjs"
                    sourceMap = true
                    metaInfo = true
                }
            }
        }
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
        val androidMain by getting {

        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))

                implementation(Libs.Util.ktorAndroid)

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
            kotlin.srcDir("src/iosMain/kotlin")

            dependencies {
                implementation(Libs.Util.ktorIos)
            }
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)

        }
        val iosX64Main by getting {
            dependsOn(iosMain)

        }
    }
}

kotlin {
    explicitApi()
    targets.all {
        compilations.all {
            kotlinOptions.allWarningsAsErrors = true
        }
    }
}

android {
    compileSdkVersion(Versions.compileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
            artifact(javadocJar.get())
            pom {
                name.set("Multiplatform TMDB API")
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
