package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV4
import app.moviebase.tmdb.remote.json
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class Tmdb4AuthenticationApi(private val client: HttpClient) {

    suspend fun requestToken(auth: Tmdb4RedirectToBodyAuth): TmdbListMetaPageResult = client.post {
        endPointV4("auth", "request_token")
        json()
        setBody(auth)
    }.body()

    suspend fun accessToken(requestToken: Tmdb4RequestTokenBody): TmdbListMetaPageResult = client.post {
        endPointV4("auth", "access_token")
        json()
        setBody(requestToken)
    }.body()

}
