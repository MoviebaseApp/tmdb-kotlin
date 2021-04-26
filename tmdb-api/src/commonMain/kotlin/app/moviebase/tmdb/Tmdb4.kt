package app.moviebase.tmdb

import app.moviebase.tmdb.api.Tmdb4AccountApi
import app.moviebase.tmdb.api.Tmdb4AuthenticationApi
import app.moviebase.tmdb.api.Tmdb4ListApi
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.request.*

class Tmdb4(tmdbApiKey: String) {

    var authenticateToken: String? = null
    var accessToken: String? = null
    var requestToken: String? = null

    private val client = buildHttpClient {
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
        requireNotNull(accessToken) { "access token token not set for request account endpoints" }
        it.header("Authorization", "Bearer $accessToken")
    }

    private val authClient = buildHttpClient {
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
        requireNotNull(authenticateToken) { "authentication token not set for request auth endpoints" }
        it.header("Authorization", "Bearer $authenticateToken")

    }

    val account = Tmdb4AccountApi(client)
    val auth = Tmdb4AuthenticationApi(authClient)
    val list = Tmdb4ListApi(client)

}
