package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbMovieDetail
import app.moviebase.tmdb.model.TmdbProviderResult
import app.moviebase.tmdb.model.TmdbTranslations
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbMovieApi(private val client: HttpClient) {

    suspend fun getDetails(id: Int, language: String, appendResponses: Iterable<AppendResponse>): TmdbMovieDetail = client.get {
        endPoint("movie", id.toString())

        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }

    suspend fun getTranslations(id: Int): TmdbTranslations = client.get {
        endPoint("movie", id.toString(), "translations")
    }

    suspend fun getWatchProviders(id: Int): TmdbProviderResult = client.get {
        endPoint("movie", id.toString(), "watch", "providers")
    }

}
