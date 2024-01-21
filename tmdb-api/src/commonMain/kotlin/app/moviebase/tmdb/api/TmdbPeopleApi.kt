package app.moviebase.tmdb.api

import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.core.parameterAppendResponses
import app.moviebase.tmdb.core.parameterLanguage
import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbPersonDetail
import app.moviebase.tmdb.model.TmdbPersonMovieCredits
import app.moviebase.tmdb.model.TmdbPersonShowCredits
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get

class TmdbPeopleApi internal constructor(private val client: HttpClient) {

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
