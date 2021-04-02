package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbNetwork
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbNetworksApi(private val client: HttpClient) {

    suspend fun getDetails(networkId: Int, appendResponses: Iterable<AppendResponse>? = null): TmdbNetwork = client.get {
        endPointV3("network", networkId.toString())
        parameterAppendResponses(appendResponses)
    }

}
