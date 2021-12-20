package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterAppendResponses
import app.moviebase.tmdb.remote.parameterLanguage
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbMoviesApi(private val client: HttpClient) {

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
    }

    suspend fun getImages(
        movieId: Int,
        language: String? = null,
        includeImageLanguage: String? = null,
    ): TmdbImages = client.get {
        endPointMovie(movieId, "images")
        parameterLanguage(language)
        parameterIncludeImageLanguage(includeImageLanguage)
    }

    suspend fun getExternalIds(movieId: Int): TmdbExternalIds = client.get {
        endPointMovie(movieId, "external_ids")
    }

    suspend fun getTranslations(movieId: Int): TmdbTranslations = client.get {
        endPointMovie(movieId, "translations")
    }

    suspend fun getWatchProviders(movieId: Int): TmdbProviderResult = client.get {
        endPointMovie(movieId, "watch", "providers")
    }

    private fun HttpRequestBuilder.endPointMovie(movieId: Int, vararg paths: String) {
        endPointV3("movie", movieId.toString(), *paths)
    }

    fun HttpRequestBuilder.parameterIncludeImageLanguage(language: String?) {
        language?.let { parameter("include_image_language", it) }
    }
}
