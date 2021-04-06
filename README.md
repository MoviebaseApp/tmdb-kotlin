<a href="https://www.themoviedb.org"><img alt="TMDb" src="doc/images/blue_short.svg" width="400"></a>

[![Maven Central](https://img.shields.io/maven-central/v/app.moviebase/tmdb-api?label=Maven%20Central)](https://search.maven.org/artifact/app.moviebase/tmdb-api)
[![Build Status](https://app.bitrise.io/app/0e5dcdc490429c81/status.svg?token=dRSlP2lXiMSQ8keWh-o8mQ&branch=master)](https://app.bitrise.io/app/0e5dcdc490429c81)
[![Kotlin](https://img.shields.io/badge/kotlin-1.4.32-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)


TMDb API
===========================
This is a library to access the [TMDb API](https://developers.themoviedb.org/3) for mobile, desktop and web applications. Written in Kotlin Multiplatform.

## Adding to your project

The library is published to Maven Central.

### Gradle

Add the Maven Central repository if it is not already there.

```kotlin
repositories {
    mavenCentral()
}
```

To use the library in a single-platform project, add a dependency.

```kotlin
dependencies {
    implementation("app.moviebase:tmdb-api:0.2.0")
}
```

In Kotlin Multiplatform projects, add the dependency to your commonMain source-set dependencies.

```kotlin
commonMain {
    dependencies {
        implementation("app.moviebase:tmdb-api:0.2.0")
    }
}
``` 

### Maven

Add a dependency to the `<dependencies>` element.

```xml
<dependency>
    <groupId>app.moviebase</groupId>
    <artifactId>tmdb-api</artifactId>
    <version>0.2.0</version>
</dependency>
```


## Usage
Most of the library follows the possibilities and naming at the official [TMDb documentation](https://developers.themoviedb.org/3/getting-started).


### Get information
For getting basic information about a movie or other media content.

```kotlin
val tmdb = Tmdb3("apiKey")
val movieDetail = tmdb.movies.getDetails(
    movieId = 12,
    language = "DE",
    appendResponses = listOf(AppendResponse.MOVIE_CREDITS)
)
```

### Search
Search for TV shows by a query.

```kotlin
val tmdb = Tmdb3("apiKey")
val showPageResult = tmdb.search.findShows(
    query = "The Expanse",
    page = 1,
    language = "DE",
    region = "US",
    includeAdult = false
)
```

### Build image URL

You can build an image URL via the poster file path and size key. More information on the [TMDb images site](https://developers.themoviedb.org/3/getting-started/images).

```kotlin
val url = TmdbImageUrlBuilder.build( "nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "w154")
```

Or create the URL by an image class and the best matched width/height.

```kotlin
val url = TmdbImageUrlBuilder.build(image = movie.posterImage, width = 200, height = 300)
```

<br/>
---

*This library uses the TMDb but is not endorsed or certified by TMDb. These services are licensed under [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0).*
