package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.Tmdb4ListMeta
import app.moviebase.tmdb.model.Tmdb4RedirectToBodyAuth
import app.moviebase.tmdb.model.Tmdb4RequestTokenBody
import app.moviebase.tmdb.model.TmdbPageResult
import io.ktor.client.*
import io.ktor.client.request.*

class Tmdb4AuthenticationApi(private val client: HttpClient) {

    suspend fun requestToken(auth: Tmdb4RedirectToBodyAuth): TmdbPageResult<Tmdb4ListMeta> = client.post {
        endPointV4("auth", "request_token")
        json()
        body = auth
    }

    suspend fun accessToken(requestToken: Tmdb4RequestTokenBody): TmdbPageResult<Tmdb4ListMeta> = client.post {
        endPointV4("auth", "access_token")
        json()
        body = requestToken
    }

}
