package app.moviebase.tmdb

import app.moviebase.tmdb.api.Tmdb4AccountApi
import app.moviebase.tmdb.api.Tmdb4AuthenticationApi
import app.moviebase.tmdb.api.Tmdb4ListApi
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.request.*

class Tmdb4(tmdbApiKey: String) {

    var authenticationToken: String? = null

    private val client = buildHttpClient {
        it.header(TmdbUrlParameter.API_KEY, tmdbApiKey)
    }

    private val authClient = buildHttpClient {
        it.header(TmdbUrlParameter.API_KEY, tmdbApiKey)
//        it.header("Authorization", "Bearer $authenticationToken")

    }

    val account = Tmdb4AccountApi(client)
    val auth = Tmdb4AuthenticationApi(authClient)
    val list = Tmdb4ListApi(client)

}
