package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AccountDetails
import app.moviebase.tmdb.model.TmdbFavoriteRequestBody
import app.moviebase.tmdb.model.TmdbWatchlistRequestBody
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbAccountApi(private val client: HttpClient) {

    suspend fun getDetails(sessionId: String): AccountDetails = client.get {
        endPointV3("account")
        parameter("session_id", sessionId)
    }

    suspend fun markFavorite(accountId: String, requestBody: TmdbFavoriteRequestBody): AccountDetails = client.post {
        endPointV3("account", accountId, "favorite")
        parameter("account_id", accountId)
        json()

        body = requestBody
    }

    suspend fun markWatchlist(accountId: String, requestBody: TmdbWatchlistRequestBody): AccountDetails = client.post {
        endPointV3("account", accountId, "favorite")
        parameter("account_id", accountId)
        json()

        body = requestBody
    }

}
