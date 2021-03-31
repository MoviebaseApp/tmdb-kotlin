package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbSeason
import app.moviebase.tmdb.model.TmdbTranslations
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbSeasonApi(private val client: HttpClient) {

    suspend fun getDetails(id: Int, seasonNumber: Int, language: String, appendResponses: Iterable<AppendResponse>): TmdbSeason = client.get {
        endPoint("tv", id.toString(), "season", seasonNumber.toString())

        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }

    suspend fun getTranslations(id: Int, seasonNumber: Int): TmdbTranslations = client.get {
        endPoint("tv", id.toString(), "season", seasonNumber.toString(), "translations")
    }

}
