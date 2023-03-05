package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbNetwork
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterAppendResponses
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbNetworksApi internal constructor(private val client: HttpClient) {

    suspend fun getDetails(networkId: Int, appendResponses: Iterable<AppendResponse>? = null): TmdbNetwork = client.get {
        endPointV3("network", networkId.toString())
        parameterAppendResponses(appendResponses)
    }.body()
}
