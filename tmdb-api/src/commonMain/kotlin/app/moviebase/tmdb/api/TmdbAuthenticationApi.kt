package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbGuestSession
import app.moviebase.tmdb.model.TmdbRequestToken
import app.moviebase.tmdb.model.TmdbSession
import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.url.TmdbAuthenticationUrlBuilder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

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

    /**
     * @return the sessionId or null the request was if unsuccessful
     */
    suspend fun acquireAccountSession(userName: String, password: String): String? {
        var token = requestToken()
        token = validateToken(userName, password, token.requestToken)
        if (!token.success) return null

        val session = createSession(token.requestToken)
        if (!session.success) return null

        return session.sessionId
    }

    suspend fun acquireGuestSession(): String? {
        val session = createGuestSession()
        if (!session.success) return null

        return session.guestSessionId
    }

    /**
     * @return the authorization TMDB URL or null if request was unsuccessful
     */
    suspend fun requestAuthorizationUrl(redirectTo: String): String? {
        val requestToken = requestToken()
        val requestTokenValue = requestToken.requestToken
        return if (requestToken.success) {
            TmdbAuthenticationUrlBuilder.buildAuthorizationUrl(requestTokenValue, redirectTo)
        } else {
            null
        }
    }
}
