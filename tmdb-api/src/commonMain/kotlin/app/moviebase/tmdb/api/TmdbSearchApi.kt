package app.moviebase.tmdb.api

import app.moviebase.tmdb.api.endPointV3
import app.moviebase.tmdb.api.parameterLanguage
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbPageResult
import app.moviebase.tmdb.model.TmdbPerson
import app.moviebase.tmdb.model.TmdbShow
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbSearchApi(private val client: HttpClient) {

    suspend fun findMovie(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbPageResult<TmdbMovie> = client.get {
        endPointV3("search", "movie")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

    suspend fun findShow(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbPageResult<TmdbShow> = client.get {
        endPointV3("search", "tv")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

    suspend fun findPerson(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbPageResult<TmdbPerson> = client.get {
        endPointV3("search", "person")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

}
