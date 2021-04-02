package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbTrendingApi(private val client: HttpClient) {

    suspend fun getTrendingMovies(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null,
    ): TmdbPageResult<TmdbMovie> = getTrending(TmdbRequestMediaType.MOVIE, timeWindow, page, language, region)

    suspend fun getTrendingShows(
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null,
    ): TmdbPageResult<TmdbShow> = getTrending(TmdbRequestMediaType.TV, timeWindow, page, language, region)

    suspend fun <T : TmdbAnyMedia> getTrending(
        mediaType: TmdbRequestMediaType,
        timeWindow: TmdbTimeWindow,
        page: Int,
        language: String? = null,
        region: String? = null,
    ): TmdbPageResult<T> = client.get {
        endPointV4("trending", mediaType.value, timeWindow.value)
        parameterLanguage(language)
        parameterRegion(region)
        parameterPage(page)
    }

}
