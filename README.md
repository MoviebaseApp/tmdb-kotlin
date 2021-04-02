<a href="https://www.themoviedb.org"><img alt="TMDb" src="doc/images/blue_short.svg" width="400"></a>

[![Maven Central](https://img.shields.io/maven-central/v/app.moviebase/tmdb-api?label=Maven%20Central)](https://search.maven.org/artifact/app.moviebase/tmdb-api)
[![Kotlin](https://img.shields.io/badge/kotlin-1.4.32-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)


TMDb API
===========================
This is a lightweight library to access the [TMDb API](https://developers.themoviedb.org/3) for mobile, desktop and web applications. Written in Kotlin Multiplatform.

## Adding to your project

The library published to Maven Central.

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
    implementation("app.moviebase:tmdb-api:0.1.3")
}
```

In Kotlin Multiplatform projects, add the dependency to your commonMain source-set dependencies.

```kotlin
commonMain {
    dependencies {
        implementation("app.moviebase:tmdb-api:0.1.3")
    }
}
``` 

### Maven

Add a dependency to the `<dependencies>` element.

```xml
<dependency>
    <groupId>app.moviebase</groupId>
    <artifactId>tmdb-api</artifactId>
    <version>0.1.3</version>
</dependency>
```


## Usage
Most of the library follows the possibilities and naming at the official [TMDb documentation site](https://developers.themoviedb.org/3/getting-started).


### Get information
For getting a movie or another media content just create a new instance and choose your section

```kotlin
val tmdb = TmdbV3("apiKey")
val movieDetail = tmdb.movie.getDetails(
    id = 12,
    language = "EN",
    appendResponses = listOf(AppendResponse.MOVIE_CREDITS)
)
```

### Build image URL

You can easily build a image URL via the poster file path and size key.  

```kotlin
val url = TmdbImageUrlBuilder.build("w154", "nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
```

Or create by a image and the best matched width/height.

```kotlin
val movie: TmdbMovie = TmdbMovie(...)
val url = TmdbImageUrlBuilder.build(image = movie.posterImage, width = 200,height = 300)
```
