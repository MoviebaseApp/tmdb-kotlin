package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.core.getByPaths
import app.moviebase.tmdb.core.parameterAppendResponses
import app.moviebase.tmdb.core.parameterIncludeImageLanguage
import app.moviebase.tmdb.core.parameterLanguage
import app.moviebase.tmdb.core.parameterPage
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get

class TmdbMoviesApi internal constructor(private val client: HttpClient) {

    /**
     * Get the primary information about a movie.
     */
    suspend fun getDetails(
        movieId: Int,
        language: String? = null,
        appendResponses: Iterable<AppendResponse>? = null
    ): TmdbMovieDetail = client.getByPaths(*moviePath(movieId)) {
        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }

    suspend fun getImages(
        movieId: Int,
        language: String? = null,
        includeImageLanguage: String? = null
    ): TmdbImages = client.getByPaths(*moviePath(movieId, "images")) {
        parameterLanguage(language)
        parameterIncludeImageLanguage(includeImageLanguage)
    }

    suspend fun getExternalIds(movieId: Int): TmdbExternalIds = client.getByPaths(*moviePath(movieId, "external_ids"))

    suspend fun getTranslations(movieId: Int): TmdbTranslations = client.getByPaths(*moviePath(movieId, "translations"))

    suspend fun getWatchProviders(movieId: Int): TmdbProviderResult = client.getByPaths(*moviePath(movieId, "watch", "providers"))

    suspend fun popular(
        page: Int,
        language: String? = null,
    ): TmdbMoviePageResult = client.get {
        endPointV3("movie", "popular")
        parameterPage(page)
        parameterLanguage(language)
    }.body()

    private fun moviePath(movieId: Int, vararg paths: String) = arrayOf("movie", movieId.toString(), *paths)
}
