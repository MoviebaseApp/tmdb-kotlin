package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbPersonDetail
import io.ktor.client.*
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
    }

    private fun HttpRequestBuilder.endPointPerson(personId: Int, vararg paths: String) {
        endPointV3("person", personId.toString(), *paths)
    }


}
