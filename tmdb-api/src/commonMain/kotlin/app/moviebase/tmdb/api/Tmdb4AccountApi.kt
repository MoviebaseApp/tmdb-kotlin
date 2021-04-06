package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import io.ktor.client.*
import io.ktor.client.request.*

class Tmdb4AccountApi(private val client: HttpClient) {

    /**
     * Get all of the lists you've created.
     */
    suspend fun getLists(accountId: String, page: Int): TmdbPageResult<Tmdb4ListMeta> = client.get {
        endPointAccount(accountId, "lists")
        parameterPage(page)
    }

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
    ): TmdbPageResult<TmdbMovie> = getFavorites(TmdbRequestMediaType.MOVIE, accountId, page, sortBy, sortOrder)

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
    ): TmdbPageResult<TmdbShow> = getFavorites(TmdbRequestMediaType.TV, accountId, page, sortBy, sortOrder)

    suspend fun <T : TmdbMediaListItem> getFavorites(
        mediaType: TmdbRequestMediaType,
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<T> = client.get {
        endPointAccount(accountId, mediaType.value, "favorites")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }

    suspend fun getMovieRecommendation(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<TmdbMovie> = getRecommendations(TmdbRequestMediaType.MOVIE, accountId, page, sortBy, sortOrder)

    suspend fun getShowRecommendation(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<TmdbShow> = getRecommendations(TmdbRequestMediaType.TV, accountId, page, sortBy, sortOrder)

    suspend fun <T : TmdbMediaListItem> getRecommendations(
        mediaType: TmdbRequestMediaType,
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<T> = client.get {
        endPointAccount(accountId, mediaType.value, "recommendations")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }

    suspend fun getMovieWatchlist(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<TmdbMovie> = getWatchlist(TmdbRequestMediaType.MOVIE, accountId, page, sortBy, sortOrder)

    suspend fun getShowWatchlist(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<TmdbShow> = getWatchlist(TmdbRequestMediaType.TV, accountId, page, sortBy, sortOrder)

    suspend fun <T : TmdbMediaListItem> getWatchlist(
        mediaType: TmdbRequestMediaType,
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC,
    ): TmdbPageResult<T> = client.get {
        endPointAccount(accountId, mediaType.value, "watchlist")
        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }

    private fun HttpRequestBuilder.parameterSortBy(sortBy: TmdbListSortBy, sortOrder: TmdbSortOrder) {
        parameter("sort_by", sortBy.value + sortOrder.value)
    }

    private fun HttpRequestBuilder.endPointAccount(accountId: String, vararg paths: String) {
        endPointV4("account", accountId, *paths)
    }

}
