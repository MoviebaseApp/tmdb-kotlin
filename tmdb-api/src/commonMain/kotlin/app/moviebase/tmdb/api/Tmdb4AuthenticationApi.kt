package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.core.endPointV4
import app.moviebase.tmdb.core.json
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * User Authorization
 *
 * @see [Documentation] (https://developers.themoviedb.org/4/auth/user-authorization-1)
 */
class Tmdb4AuthenticationApi internal constructor(private val client: HttpClient) {

    /**
     * This method generates a new request token that you can ask a user to approve.
     * @see [Documentation] (https://developers.themoviedb.org/4/auth/create-request-token)
     */
    suspend fun requestToken(auth: Tmdb4RedirectToBodyAuth): Tmdb4RequestToken = client.post {
        endPointV4("auth", "request_token")
        json()
        setBody(auth)
    }.body()

    /**
     * This method will finish the user authentication flow and issue an official user access token.
     * @see [Documentation] (https://developers.themoviedb.org/4/auth/create-access-token)
     */
    suspend fun accessToken(requestToken: Tmdb4RequestTokenBody): Tmdb4AccessToken = client.post {
        endPointV4("auth", "access_token")
        json()
        setBody(requestToken)
    }.body()

    /**
     * This method gives your users the ability to log out of a session.
     * @see [Documentation] (https://developers.themoviedb.org/4/auth/delete-access-token)
     */
    suspend fun deleteAccessToken(deleteAccessToken: Tmdb4DeleteAccessTokenBody): Tmdb4AccessToken = client.delete {
        endPointV4("auth", "access_token")
        json()
        setBody(deleteAccessToken)
    }.body()
}
