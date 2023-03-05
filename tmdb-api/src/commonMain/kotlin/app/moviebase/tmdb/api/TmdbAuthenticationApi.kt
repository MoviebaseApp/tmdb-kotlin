package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbGuestSession
import app.moviebase.tmdb.model.TmdbRequestToken
import app.moviebase.tmdb.model.TmdbSession
import app.moviebase.tmdb.remote.endPointV3
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbAuthenticationApi internal constructor(private val client: HttpClient) {

    suspend fun requestToken(): TmdbRequestToken = client.get {
        endPointV3("authentication/token/new")
    }.body()

    suspend fun validateToken(userName: String, password: String, requestToken: String): TmdbRequestToken = client.get {
        endPointV3("authentication/token/new")

        parameter("request_token", requestToken)
        parameter("username", userName)
        parameter("password", password)
    }.body()

    suspend fun createSession(requestToken: String): TmdbSession = client.get {
        endPointV3("authentication/session/new")
        parameter("request_token", requestToken)
    }.body()

    suspend fun createGuestSession(): TmdbGuestSession = client.get {
        endPointV3("authentication/guest_session/new")
    }.body()

    suspend fun acquireAccountSession(userName: String, password: String): String {
        var token = requestToken()
        token = validateToken(userName, password, token.requestToken)
        val session = createSession(token.requestToken)
        return session.sessionId
    }

    suspend fun requestAuthorizationUrl(redirectTo: String): String {
        val requestToken = requestToken().requestToken
        return "https://www.themoviedb.org/authenticate/${requestToken}?redirect_to=$redirectTo"
    }
}
