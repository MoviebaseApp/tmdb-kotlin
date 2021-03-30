buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", Versions.kotlin))
        classpath(kotlin("serialization", Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
