package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbDiscoverApi(private val client: HttpClient) {

    suspend fun discoverMovie(
        page: Int,
        language: String? = null,
        region: String? = null,
        sortBy: TmdbListSortBy = TmdbListSortBy.POPULARITY,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
        filterParameters: Map<String, Any?>
    ): TmdbPageResult<TmdbMovie> = client.get {
        endPointV3("discover", "movie")
        parameterPage(page)
        parameterLanguage(language)
        parameterRegion(region)
        parameter("sort_by", sortBy.value + sortOrder.value)
        parameters(filterParameters)
    }

    suspend fun discoverShow(
        page: Int,
        language: String? = null,
        region: String? = null,
        sortBy: TmdbListSortBy = TmdbListSortBy.POPULARITY,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
        filterParameters: Map<String, Any?>
    ):  TmdbPageResult<TmdbShow>  = client.get {
        endPointV3("discover", "show")
        parameterPage(page)
        parameterLanguage(language)
        parameterRegion(region)
        parameter("sort_by", sortBy.value + sortOrder.value)
        parameters(filterParameters)
    }

}
