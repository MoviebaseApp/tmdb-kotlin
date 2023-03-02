[Website](https://www.themoviedb.org) |
[Forum](https://www.themoviedb.org/talk/category/5047958519c29526b50017d6) |
[Documentation](https://www.themoviedb.org/documentation/api) |
[TMDb 3 API](https://developers.themoviedb.org/3)


<a href="https://www.themoviedb.org"><img alt="TMDb" src="doc/images/blue_short.svg" width="600"></a>

***Get movie and TV show content from TMDb in a fast and simple way.***

[![Maven Central](https://img.shields.io/maven-central/v/app.moviebase/tmdb-api?label=Maven%20Central)](https://search.maven.org/artifact/app.moviebase/tmdb-api)
[![Build Status](https://app.bitrise.io/app/0e5dcdc490429c81/status.svg?token=dRSlP2lXiMSQ8keWh-o8mQ&branch=master)](https://app.bitrise.io/app/0e5dcdc490429c81)
[![Issues](https://img.shields.io/github/issues/MoviebaseApp/tmdb-api/total)](http://kotlinlang.org)
[![Kotlin](https://img.shields.io/badge/kotlin-1.7.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-7-green?style=flat)](https://gradle.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

<hr>


# TMDb API
This library gives access to [TMDb API](https://www.themoviedb.org/documentation/api) version 3 and 4 for mobile, desktop, and web applications. 
It supports Swift, Kotlin, and JavaScript by setting up as a Kotlin Multiplatform project.

*This library is mainly used and supported by [Moviebase](https://www.moviebase.app).*


## Adding to your project

The library is available on Maven Central.

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
    implementation("app.moviebase:tmdb-api:0.7.0")
}
```

In Kotlin Multiplatform projects, add the dependency to your commonMain source-set dependencies.

```kotlin
commonMain {
    dependencies {
        implementation("app.moviebase:tmdb-api:0.7.0")
    }
}
``` 

### Maven

Add a dependency to the `<dependencies>` element.

```xml
<dependency>
    <groupId>app.moviebase</groupId>
    <artifactId>tmdb-api</artifactId>
    <version>0.7.0</version>
</dependency>
```


## Usage
Most of the library follows the possibilities and naming at the official [TMDb documentation](https://www.themoviedb.org/documentation/api).

The documentation of the endpoints can be found in [Version 3](https://developers.themoviedb.org/3) and [Version 4](https://developers.themoviedb.org/4).


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


### Discover
Discover a movie or TV show by the discover parameter class.

```kotlin
val discover = TmdbDiscover.Movie(
    sortBy = TmdbDiscoverMovieSortBy.POPULARITY,
    sortOrder = TmdbSortOrder.DESC,
    voteAverageGte = 5,
    voteCountGte = 200,
    releaseDate = TmdbDiscoverTimeRange.BetweenYears(from = 2020, to = 2021)
)

val discoverPageResult = tmdb.discover.discoverMovie(
    page = 1,
    region = "DE",
    language = "de",
    discover = discover
)
```

Alternatively, use predefined discover categories like upcoming, networks or on streaming.

```kotlin
val discoverCategory = DiscoverCategory.OnStreaming.Netflix(
    mediaType = TmdbMediaType.MOVIE,
    watchRegion = "DE"
)

val result = tmdb.discover.discoverByCategory(
    page = 1,
    region = "DE",
    language = "de",
    category = discoverCategory
)
```

### Build image URL

You can build an image URL via the poster file path and size key. More information on the [TMDb images site](https://developers.themoviedb.org/3/getting-started/images).

```kotlin
val url = TmdbImageUrlBuilder.build("nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "w154")
```

Or create the URL by an image class with the best matched width/height.

```kotlin
val url = TmdbImageUrlBuilder.build(image = movie.posterImage, width = 200, height = 300)
```

For creating the poster URL by the movie item.

```kotlin
val url = TmdbImageUrlBuilder.buildPoster(item = movie, width = 200)
```

### Build video URL

You can build a video URL by the key value into `TmdbVideo`. TMDb API currently support videos from YouTube and Vimeo

```kotlin
val youtubeTmdbVideo = TmdbVideo(id = "123", key = "qwerasdf", site = TmdbVideoSite.YOUTUBE)
val url = TmdbImageUrlBuilder.build(youtubeTmdbVideo) // It will return `https://www.youtube.com/watch?v=qwerasdf`

val vimeoTmdbVideo = TmdbVideo(id = "123", key = "qwerasdf", site = TmdbVideoSite.VIMEO)
val url = TmdbImageUrlBuilder.build(vimeoTmdbVideo) // It will return `https://vimeo.com/qwerasdf`
```

<br>

<hr>

*This library uses the TMDb but is not endorsed or certified by TMDb. These services are licensed under [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0).*
