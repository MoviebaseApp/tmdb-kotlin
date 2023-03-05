package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterAppendResponses
import app.moviebase.tmdb.remote.parameterIncludeImageLanguage
import app.moviebase.tmdb.remote.parameterLanguage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbMoviesApi internal constructor(private val client: HttpClient) {

    /**
     * Get the primary information about a movie.
     */
    suspend fun getDetails(
        movieId: Int,
        language: String? = null,
        appendResponses: Iterable<AppendResponse>? = null
    ): TmdbMovieDetail = client.get {
        endPointMovie(movieId)
        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }.body()

    suspend fun getImages(
        movieId: Int,
        language: String? = null,
        includeImageLanguage: String? = null,
    ): TmdbImages = client.get {
        endPointMovie(movieId, "images")
        parameterLanguage(language)
        parameterIncludeImageLanguage(includeImageLanguage)
    }.body()

    suspend fun getExternalIds(movieId: Int): TmdbExternalIds = client.get {
        endPointMovie(movieId, "external_ids")
    }.body()

    suspend fun getTranslations(movieId: Int): TmdbTranslations = client.get {
        endPointMovie(movieId, "translations")
    }.body()

    suspend fun getWatchProviders(movieId: Int): TmdbProviderResult = client.get {
        endPointMovie(movieId, "watch", "providers")
    }.body()

    private fun HttpRequestBuilder.endPointMovie(movieId: Int, vararg paths: String) {
        endPointV3("movie", movieId.toString(), *paths)
    }
}
