package app.moviebase.tmdb

import app.moviebase.tmdb.api.*
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.request.*

class Tmdb(tmdbApiKey: String) {

    private val client = buildHttpClient {
        it.header(TmdbUrlParameter.API_KEY, tmdbApiKey)
    }

    val movie = TmdbMovieApi(client)
    val show = TmdbShowApi(client)
    val season = TmdbSeasonApi(client)
    val find = TmdbFindApi(client)
    val search = TmdbSearchApi(client)

}
