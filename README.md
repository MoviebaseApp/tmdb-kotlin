<img alt="TMDb" src="doc/images/blue_short.svg" width="400">


[![Maven Central](https://img.shields.io/maven-central/v/app.moviebase/tmdb-api?label=Maven%20Central)](https://search.maven.org/artifact/app.moviebase/tmdb-api)
[![Kotlin](https://img.shields.io/badge/kotlin-1.4.32-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)


TMDb API
===========================
This is a lightweight library to access the [TMDb API](https://developers.themoviedb.org/3) for mobile, desktop and web applications. Written in Kotlin Multiplatform.

## Adding to your project

tmdb-api is currently published to Maven Central.

### Use Gradle

For using Gradle just add the dependency:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("app.moviebase:tmdb-api:0.1.3")
}
```

For Kotlin Multiplatform add the dependency to your common source-set dependencies:

```kotlin
commonMain {
    dependencies {
        implementation("app.moviebase:tmdb-api:0.1.3")
    }
}
``` 

## Usage
Most of the library follows the possibilities and naming at the official [TMDb documentation site](https://developers.themoviedb.org/3/getting-started).


### Get Information
For getting a movie or another media content just create a new instance and choose your section

```kotlin
import app.moviebase.tmdb.*

val tmdb = Tmdb("apiKey")
val movieDetail = tmdb.movie.getDetails(
    id = 12,
    language = "EN",
    appendResponses = listOf(AppendResponse.MOVIE_CREDITS)
)
```

## Reporting Issues and Support
For issues, feature requests, and discussion please use [GitHub Issues][issues]. 

[Create an issue][issues] to request additional API coverage.
