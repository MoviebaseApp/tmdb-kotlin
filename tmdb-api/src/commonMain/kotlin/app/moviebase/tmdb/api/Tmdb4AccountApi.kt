package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbPageResult
import app.moviebase.tmdb.model.TmdbSortOrder
import app.moviebase.tmdb.model.getValueOrDefault
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbShow
import app.moviebase.tmdb.model.TmdbListSortBy
import io.ktor.client.*
import io.ktor.client.request.*

class Tmdb4AccountApi(private val client: HttpClient) {

    /**
     * Get Favorite, Recommendation, Watchlist, Rated from movie or TV show
     *
     * @see [Get Favorite Movies](https://developers.themoviedb.org/4/account/get-account-favorite-movies)
     */
    suspend fun getFavoriteMovies(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder? = null
    ): TmdbPageResult<TmdbMovie> = client.get {
        endPointV4("account", accountId, "movie", "favorites")

        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }

    suspend fun getFavoriteShows(
        accountId: String,
        page: Int,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder? = null
    ): TmdbPageResult<TmdbShow> = client.get {
        endPointV4("account", accountId, "tv", "favorites")

        sortBy?.let { parameterSortBy(it, sortOrder) }
        parameterPage(page)
    }

    private fun HttpRequestBuilder.parameterSortBy(sortBy: TmdbListSortBy, sortOrder: TmdbSortOrder?) {
        parameter("sort_by", sortBy.value + sortOrder.getValueOrDefault())
    }

}
