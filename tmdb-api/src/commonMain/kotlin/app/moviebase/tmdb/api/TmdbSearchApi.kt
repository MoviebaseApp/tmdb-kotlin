package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbMovieSearchResults
import app.moviebase.tmdb.model.TmdbShowSearchResults
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbSearchApi(private val client: HttpClient) {

    suspend fun findMovie(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbMovieSearchResults = client.get {
        endPoint("search", "movie")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

    suspend fun findShow(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbShowSearchResults = client.get {
        endPoint("search", "tv")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

    suspend fun findPerson(query: String, page: Int, language: String, region: String, includeAdult: Boolean): TmdbMovieSearchResults = client.get {
        endPoint("search", "person")

        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", includeAdult)
        parameter("region", region)
        parameterLanguage(language)
    }

}
