package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.core.parameterLanguage
import app.moviebase.tmdb.core.parameterPage
import app.moviebase.tmdb.core.parameterRegion
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbSearchApi internal constructor(private val client: HttpClient) {

    /**
     * Use multi search when you want to search for movies, TV shows and people in a single request.
     * See https://developer.themoviedb.org/reference/search-multi
     */
    suspend fun findMulti(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false,
    ): TmdbMultiPageResult = client.get {
        endSearch(TmdbSearchType.MULTI)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
    }.body()

    suspend fun findMovies(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false,
        year: Int? = null,
        primaryReleaseYear: Int? = null
    ): TmdbMoviePageResult = client.get {
        endSearch(TmdbSearchType.MOVIE)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
        parameterYear(year)
        parameterPrimaryReleaseYear(primaryReleaseYear)
    }.body()

    suspend fun findShows(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean? = null,
        firstAirDateYear: Int? = null
    ): TmdbShowPageResult = client.get {
        endSearch(TmdbSearchType.TV)

        parameterQuery(query)
        parameterIncludeAdult(includeAdult)
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
        parameterFirstAirDateYear(firstAirDateYear)
    }.body()

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
    }.body()

    suspend fun findCompanies(
        query: String,
        page: Int
    ): TmdbCompanyPageResult = client.get {
        endSearch(TmdbSearchType.COMPANY)

        parameterQuery(query)
        parameterPage(page)
    }.body()

    suspend fun findCollections(
        query: String,
        page: Int,
        language: String? = null
    ): TmdbCollectionPageResult = client.get {
        endSearch(TmdbSearchType.COLLECTION)

        parameterQuery(query)
        parameterPage(page)
        parameterLanguage(language)
    }.body()

    suspend fun findKeywords(
        query: String,
        page: Int
    ): TmdbKeywordPageResult = client.get {
        endSearch(TmdbSearchType.KEYWORD)

        parameterQuery(query)
        parameterPage(page)
    }.body()

    private fun HttpRequestBuilder.endSearch(searchType: TmdbSearchType) {
        endPointV3("search", searchType.value)
    }

    private fun HttpRequestBuilder.parameterIncludeAdult(includeAdult: Boolean?) {
        includeAdult?.let { parameter("include_adult", it) }
    }

    private fun HttpRequestBuilder.parameterQuery(query: String) {
        parameter("query", query)
    }

    private fun HttpRequestBuilder.parameterFirstAirDateYear(year: Int?) {
        year?.let { parameter("first_air_date_year", it) }
    }

    private fun HttpRequestBuilder.parameterYear(year: Int?) {
        year?.let { parameter("year", it) }
    }

    private fun HttpRequestBuilder.parameterPrimaryReleaseYear(year: Int?) {
        year?.let { parameter("primary_release_year", it) }
    }
}
