package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV4
import app.moviebase.tmdb.remote.parameterLanguage
import app.moviebase.tmdb.remote.parameterPage
import app.moviebase.tmdb.remote.parameterRegion
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbTrendingApi(private val client: HttpClient) {

    suspend fun getTrendingMovies(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null,
    ): TmdbMoviePageResult = client.get {
        endPointV4("trending", TmdbRequestMediaType.MOVIE.value, timeWindow.value)
        parameterLanguage(language)
        parameterRegion(region)
        parameterPage(page)
    }

    suspend fun getTrendingShows(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null,
    ): TmdbShowPageResult = client.get {
        endPointV4("trending", TmdbRequestMediaType.TV.value, timeWindow.value)
        parameterLanguage(language)
        parameterRegion(region)
        parameterPage(page)
    }

}
