package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbProviderResult
import app.moviebase.tmdb.model.TmdbShowDetail
import app.moviebase.tmdb.model.TmdbTranslations
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbShowApi(private val client: HttpClient) {

    suspend fun getDetails(id: Int, language: String, appendResponses: Iterable<AppendResponse>): TmdbShowDetail = client.get {
        endPoint("tv", id.toString())

        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }

    suspend fun getTranslations(id: Int): TmdbTranslations = client.get {
        endPoint("tv", id.toString(), "translations")
    }

    suspend fun getWatchProviders(id: Int): TmdbProviderResult = client.get {
        endPoint("tv", id.toString(), "watch", "providers")
    }

}
