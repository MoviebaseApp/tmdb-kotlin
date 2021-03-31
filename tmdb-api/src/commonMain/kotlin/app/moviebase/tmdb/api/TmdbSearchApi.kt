package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbMovieResults
import app.moviebase.tmdb.model.TmdbShowResults
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbSearchApi(private val client: HttpClient) {

    suspend fun findMovie(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbMovieResults = client.get {
        endPoint("search", "movie")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

    suspend fun findShow(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbShowResults = client.get {
        endPoint("search", "tv")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

}
