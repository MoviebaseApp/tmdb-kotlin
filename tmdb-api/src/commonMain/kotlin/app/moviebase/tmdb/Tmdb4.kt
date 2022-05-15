package app.moviebase.tmdb

import app.moviebase.tmdb.api.Tmdb4AccountApi
import app.moviebase.tmdb.api.Tmdb4AuthenticationApi
import app.moviebase.tmdb.api.Tmdb4ListApi
import app.moviebase.tmdb.remote.TmdbLogLevel
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.request.*

class Tmdb4(
    tmdbApiKey: String,
    authenticationToken: String? = null,
    logLevel: TmdbLogLevel = TmdbLogLevel.NONE
) {

    var accessToken: String? = null
    var requestToken: String? = null

    private val client = buildHttpClient(logLevel) {
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
        accessToken?.let { token ->
            it.header("Authorization", "Bearer $token")
        }
    }

    private val authClient = buildHttpClient(logLevel) {
        // TODO: Install Auth client https://ktor.io/docs/auth.html
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
        requireNotNull(authenticationToken) { "authentication token not set for request auth endpoints" }
        it.header("Authorization", "Bearer $authenticationToken")
    }

    val account = Tmdb4AccountApi(client)
    val auth = Tmdb4AuthenticationApi(authClient)
    val list = Tmdb4ListApi(client)

}
