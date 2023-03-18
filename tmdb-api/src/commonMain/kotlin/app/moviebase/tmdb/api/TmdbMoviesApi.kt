package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.core.getResponse
import app.moviebase.tmdb.core.parameterAppendResponses
import app.moviebase.tmdb.core.parameterIncludeImageLanguage
import app.moviebase.tmdb.core.parameterLanguage
import io.ktor.client.*

class TmdbMoviesApi internal constructor(private val client: HttpClient) {

    /**
     * Get the primary information about a movie.
     */
    suspend fun getDetails(
        movieId: Int,
        language: String? = null,
        appendResponses: Iterable<AppendResponse>? = null
    ): TmdbMovieDetail = client.getResponse(*moviePath(movieId)) {
        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }

    suspend fun getImages(
        movieId: Int,
        language: String? = null,
        includeImageLanguage: String? = null
    ): TmdbImages = client.getResponse(*moviePath(movieId, "images")) {
        parameterLanguage(language)
        parameterIncludeImageLanguage(includeImageLanguage)
    }

    suspend fun getExternalIds(movieId: Int): TmdbExternalIds = client.getResponse(*moviePath(movieId, "external_ids"))

    suspend fun getTranslations(movieId: Int): TmdbTranslations = client.getResponse(*moviePath(movieId, "translations"))

    suspend fun getWatchProviders(movieId: Int): TmdbProviderResult = client.getResponse(*moviePath(movieId, "watch", "providers"))

    private fun moviePath(movieId: Int, vararg paths: String) = arrayOf("movie", movieId.toString(), *paths)
}
