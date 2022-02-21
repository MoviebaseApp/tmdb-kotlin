package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbPersonDetail
import app.moviebase.tmdb.model.TmdbPersonMovieCredits
import app.moviebase.tmdb.model.TmdbPersonShowCredits
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterAppendResponses
import app.moviebase.tmdb.remote.parameterLanguage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbPeopleApi(private val client: HttpClient) {

    suspend fun getDetails(
        personId: Int,
        language: String? = null,
        appendResponses: Iterable<AppendResponse>? = null
    ): TmdbPersonDetail = client.get {
        endPointPerson(personId)
        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
    }.body()

    suspend fun getShowCredits(
        personId: Int,
        language: String? = null
    ): TmdbPersonShowCredits = client.get {
        endPointPerson(personId, "tv_credits")
        parameterLanguage(language)
    }.body()

    suspend fun getMovieCredits(
        personId: Int,
        language: String? = null
    ): TmdbPersonMovieCredits = client.get {
        endPointPerson(personId, "movie_credits")
        parameterLanguage(language)
    }.body()

    private fun HttpRequestBuilder.endPointPerson(personId: Int, vararg paths: String) {
        endPointV3("person", personId.toString(), *paths)
    }
}
