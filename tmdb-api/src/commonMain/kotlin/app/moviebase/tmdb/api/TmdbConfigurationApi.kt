package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbConfiguration
import app.moviebase.tmdb.remote.endPointV3
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbConfigurationApi(private val client: HttpClient) {

    suspend fun getApiConfiguration(): TmdbConfiguration = client.get {
        endPointV3("configuration")
    }.body()

}
