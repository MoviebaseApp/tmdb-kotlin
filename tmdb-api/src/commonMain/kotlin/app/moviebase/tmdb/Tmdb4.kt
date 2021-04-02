package app.moviebase.tmdb

import app.moviebase.tmdb.api.Tmdb4AccountApi
import app.moviebase.tmdb.api.Tmdb4AuthenticationApi
import app.moviebase.tmdb.api.Tmdb4ListApi
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.request.*

class Tmdb4(tmdbApiKey: String) {

    private val client = buildHttpClient {
        it.header(TmdbUrlParameter.API_KEY, tmdbApiKey)
    }

    val account = Tmdb4AccountApi(client)
    val auth = Tmdb4AuthenticationApi(client)
    val list = Tmdb4ListApi(client)

}
