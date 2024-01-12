package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbExternalSource
import app.moviebase.tmdb.model.TmdbFindResults
import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.core.parameterLanguage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TmdbFindApi internal constructor(private val client: HttpClient) {

    suspend fun find(externalId: String, language: String, externalSource: TmdbExternalSource): TmdbFindResults = client.get {
        endPointV3("find", externalId)

        parameterLanguage(language)
        parameter("external_source", externalSource.value)
    }.body()
}
