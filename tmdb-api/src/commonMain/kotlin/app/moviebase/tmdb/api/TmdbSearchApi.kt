package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterFirstAirDateYear
import app.moviebase.tmdb.remote.parameterLanguage
import app.moviebase.tmdb.remote.parameterPage
import app.moviebase.tmdb.remote.parameterPrimaryReleaseYear
import app.moviebase.tmdb.remote.parameterRegion
import app.moviebase.tmdb.remote.parameterYear
import io.ktor.client.*
import io.ktor.client.request.*


class TmdbSearchApi(private val client: HttpClient) {

    suspend fun findMovies(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false,
        year: Int? = null,
        primaryReleaseYear: Int? = null,
    ): TmdbMoviePageResult = client.get {
        endSearch(TmdbSearchType.MOVIE)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
        parameterYear(year)
        parameterPrimaryReleaseYear(primaryReleaseYear)
    }

    suspend fun findShows(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean? = null,
        firstAirDateYear: Int? = null,
    ): TmdbShowPageResult = client.get {
        endSearch(TmdbSearchType.TV)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
        parameterFirstAirDateYear(firstAirDateYear)
    }

    suspend fun findPeople(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false
    ): TmdbPersonPageResult = client.get {
        endSearch(TmdbSearchType.PERSON)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
    }

    suspend fun findCompanies(
        query: String,
        page: Int
    ): TmdbCompanyPageResult = client.get {
        endSearch(TmdbSearchType.COMPANY)

        parameterQuery(query)
        parameterPage(page)
    }

    suspend fun findCollections(
        query: String,
        page: Int,
        language: String? = null,
    ): TmdbCollectionPageResult = client.get {
        endSearch(TmdbSearchType.COLLECTION)

        parameterQuery(query)
        parameterPage(page)
        parameterLanguage(language)
    }

    suspend fun findKeywords(
        query: String,
        page: Int
    ): TmdbKeywordPageResult = client.get {
        endSearch(TmdbSearchType.KEYWORD)

        parameterQuery(query)
        parameterPage(page)
    }

    private fun HttpRequestBuilder.endSearch(searchType: TmdbSearchType) {
        endPointV3("search", searchType.value)
    }

    private fun HttpRequestBuilder.parameterIncludeAdult(includeAdult: Boolean?) {
        includeAdult?.let { parameter("include_adult", it) }
    }

    private fun HttpRequestBuilder.parameterQuery(query: String) {
        parameter("query", query)
    }

}
