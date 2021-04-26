package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbAccountDetails
import app.moviebase.tmdb.model.TmdbFavoriteRequestBody
import app.moviebase.tmdb.model.TmdbWatchlistRequestBody
import app.moviebase.tmdb.remote.interceptRequest
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbAccountApi internal constructor(private val client: HttpClient) {

    suspend fun getDetails(sessionId: String): TmdbAccountDetails = client.get {
        endPointV3("account")
        parameter("session_id", sessionId)
    }

    suspend fun markFavorite(accountId: String, requestBody: TmdbFavoriteRequestBody): TmdbAccountDetails = client.post {
        endPointAccount(accountId, "favorite")
        parameter("account_id", accountId)
        json()

        body = requestBody
    }

    suspend fun markWatchlist(accountId: String, requestBody: TmdbWatchlistRequestBody): TmdbAccountDetails = client.post {
        endPointAccount(accountId, "favorite")
        parameter("account_id", accountId)
        json()

        body = requestBody
    }

    private fun HttpRequestBuilder.endPointAccount(accountId: String, vararg paths: String) {
        endPointV3("account", accountId, *paths)
    }

}
