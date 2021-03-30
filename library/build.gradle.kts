import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "app.moviebase"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    ios {
        binaries {
            framework {
                baseName = "tmdb"
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
        val iosMain by getting {
            dependencies {
                implementation(Libs.Util.ktorIos)
            }
        }
        val iosTest by getting {

        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}
