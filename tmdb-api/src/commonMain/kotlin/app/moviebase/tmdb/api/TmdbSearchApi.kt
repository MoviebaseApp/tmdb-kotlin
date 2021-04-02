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
    ): TmdbPageResult<TmdbMovie> = find(TmdbSearchType.TV, query, page, language, region, includeAdult)

    suspend fun findShows(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false
    ): TmdbPageResult<TmdbShow> = find(TmdbSearchType.TV, query, page, language, region, includeAdult)

    suspend fun findPeople(
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean = false
    ): TmdbPageResult<TmdbPerson> = find(TmdbSearchType.PERSON, query, page, language, region, includeAdult)

    suspend fun findCompanies(
        query: String,
        page: Int
    ): TmdbPageResult<TmdbCompany> = find(TmdbSearchType.COMPANY, query, page)

    suspend fun findCollections(
        query: String,
        page: Int,
        language: String? = null,
    ): TmdbPageResult<TmdbBelongsToCollection> = find(TmdbSearchType.COLLECTION, query, page, language)

    suspend fun findKeywords(
        query: String,
        page: Int
    ): TmdbPageResult<TmdbKeyword> = find(TmdbSearchType.KEYWORD, query, page)

    suspend fun <T : TmdbSearchable> find(
        searchType: TmdbSearchType,
        query: String,
        page: Int,
        language: String? = null,
        region: String? = null,
        includeAdult: Boolean? = null
    ): TmdbPageResult<T> = client.get {
        endPointV3("search", searchType.value)

        parameter("query", query)
        includeAdult?.let { parameter("include_adult", it) }
        parameterPage(page)
        parameterRegion(region)
        parameterLanguage(language)
    }

}
