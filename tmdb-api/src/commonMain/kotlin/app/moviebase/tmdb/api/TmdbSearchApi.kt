package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import io.ktor.client.*
import io.ktor.client.request.*


class TmdbSearchApi(private val client: HttpClient) {

    suspend fun findMovies(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false
    ): TmdbPageResult<TmdbMovie> = client.get {
        endSearch(TmdbSearchType.MOVIE)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
    }

    suspend fun findShows(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean? = null
    ): TmdbPageResult<TmdbShow> = client.get {
        endSearch(TmdbSearchType.TV)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
    }

    suspend fun findPeople(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false
    ): TmdbPageResult<TmdbPerson> = client.get {
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
    ): TmdbPageResult<TmdbCompany> = client.get {
        endSearch(TmdbSearchType.COMPANY)

        parameterQuery(query)
        parameterPage(page)
    }

    suspend fun findCollections(
        query: String,
        page: Int,
        language: String? = null,
    ): TmdbPageResult<TmdbCollection> = client.get {
        endSearch(TmdbSearchType.COLLECTION)

        parameterQuery(query)
        parameterPage(page)
        parameterLanguage(language)
    }

    suspend fun findKeywords(
        query: String,
        page: Int
    ): TmdbPageResult<TmdbKeywordDetail> = client.get {
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
