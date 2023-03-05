package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV4
import app.moviebase.tmdb.remote.parameterPage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class Tmdb4AccountApi internal constructor(private val client: HttpClient) {

    /**
     * Get all of the lists you've created.
     */
    suspend fun getLists(accountId: String, page: Int): TmdbListMetaPageResult = client.get {
        endPointAccount(accountId, "lists")
        parameterPage(page)
    }.body()

    /**
     * Get the list of movies you have marked as a favorite.
     *
     * @see [Get Favorite Movies](https://developers.themoviedb.org/4/account/get-account-favorite-movies)
     */
    suspend fun getFavoriteMovies(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbMoviePageResult = client.get {
        endPointAccount(accountId, TmdbRequestMediaType.MOVIE.value, "favorites")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }.body()

    /**
     * Get the list of TV shows you have marked as a favorite.
     *
     * @see [Get Favorite TV Shows](https://developers.themoviedb.org/4/account/get-account-favorite-tv-shows)
     */
    suspend fun getFavoriteShows(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbShowPageResult  = client.get {
        endPointAccountList(accountId, TmdbRequestMediaType.TV, "favorites")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }.body()

    suspend fun getMovieRecommendation(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbMoviePageResult = client.get {
        endPointAccountList(accountId, TmdbRequestMediaType.MOVIE, "recommendations")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }.body()

    suspend fun getShowRecommendation(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbShowPageResult = client.get {
        endPointAccount(accountId, TmdbRequestMediaType.TV.value, "recommendations")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }.body()

    suspend fun getMovieWatchlist(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbMoviePageResult = client.get {
        endPointAccountList(accountId, TmdbRequestMediaType.MOVIE, "watchlist")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }.body()

    suspend fun getShowWatchlist(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbShowPageResult = client.get {
        endPointAccountList(accountId, TmdbRequestMediaType.TV, "watchlist")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }.body()

    private fun HttpRequestBuilder.parameterSortBy(sortBy: TmdbListSortBy, sortOrder: TmdbSortOrder) {
        parameter("sort_by", sortBy.value + sortOrder.value)
    }

    private fun HttpRequestBuilder.endPointAccountList(accountId: String, type: TmdbRequestMediaType, list: String) {
        endPointV4("account", accountId, type.value, list)
    }

    private fun HttpRequestBuilder.endPointAccount(accountId: String, vararg paths: String) {
        endPointV4("account", accountId, *paths)
    }
}
