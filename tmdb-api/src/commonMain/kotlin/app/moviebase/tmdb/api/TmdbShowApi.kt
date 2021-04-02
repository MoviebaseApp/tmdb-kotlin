package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbProviderResult
import app.moviebase.tmdb.model.TmdbShowDetail
import app.moviebase.tmdb.model.TmdbTranslations
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbShowApi(private val client: HttpClient) {

    suspend fun getDetails(
        showId: Int,
        language: String? = null,
        appendResponses: Iterable<AppendResponse>? = null
    ): TmdbShowDetail = client.get {
        endPointShow(showId)
        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }

    suspend fun getTranslations(showId: Int): TmdbTranslations = client.get {
        endPointShow(showId, "translations")
    }

    suspend fun getWatchProviders(showId: Int): TmdbProviderResult = client.get {
        endPointShow(showId, "watch", "providers")
    }

    private fun HttpRequestBuilder.endPointShow(showId: Int, vararg paths: String) {
        endPointV3("tv", showId.toString(), *paths)
    }

}
