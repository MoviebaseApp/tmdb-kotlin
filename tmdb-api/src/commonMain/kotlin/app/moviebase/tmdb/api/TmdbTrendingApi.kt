package app.moviebase.tmdb.api

import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.core.parameterLanguage
import app.moviebase.tmdb.core.parameterPage
import app.moviebase.tmdb.core.parameterRegion
import app.moviebase.tmdb.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbTrendingApi internal constructor(private val client: HttpClient) {

    suspend fun getTrendingMovies(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null
    ): TmdbMoviePageResult = client.get {
        endPointV3("trending", TmdbRequestMediaType.MOVIE.value, timeWindow.value)
        parameterLanguage(language)
        parameterRegion(region)
        parameterPage(page)
    }.body()

    suspend fun getTrendingShows(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null
    ): TmdbShowPageResult = client.get {
        endPointV3("trending", TmdbRequestMediaType.TV.value, timeWindow.value)
        parameterLanguage(language)
        parameterRegion(region)
        parameterPage(page)
    }.body()

    suspend fun getTrendingPeople(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null
    ): TmdbPersonPageResult = client.get {
        endPointV3("trending", TmdbRequestMediaType.PERSON.value, timeWindow.value)
        parameterLanguage(language)
        parameterRegion(region)
        parameterPage(page)
    }.body()
}
