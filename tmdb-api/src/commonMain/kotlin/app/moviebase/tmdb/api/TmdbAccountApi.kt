package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.json
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbAccountApi internal constructor(private val client: HttpClient) {

    /**
     * Get your account details.
     * @see [Documentation](https://developers.themoviedb.org/3/account)
     */
    suspend fun getDetails(): TmdbAccountDetails = client.get {
        endPointV3("account")
    }.body()

    suspend fun getFavorites(accountId: Int, mediaType: TmdbMediaType): TmdbMoviePageResult = when (mediaType) {
        TmdbMediaType.MOVIE -> getFavoriteMovies(accountId)
        TmdbMediaType.SHOW -> getFavoriteShows(accountId)
        else -> throw IllegalArgumentException("Only movies and shows are supported.")
    }

    suspend fun getFavoriteMovies(accountId: Int): TmdbMoviePageResult = client.get {
        endPointAccount(accountId, "favorite", "movies")
    }.body()

    suspend fun getFavoriteShows(accountId: Int): TmdbMoviePageResult = client.get {
        endPointAccount(accountId, "favorite", "tv")
    }.body()

    /**
     * POST /account/{account_id}/favorite
     *
     * This method allows you to mark a movie or TV show as a favorite item.
     * @see [Documentation](https://developers.themoviedb.org/3/account/mark-as-favorite)
     */
    suspend fun markFavorite(accountId: Int, requestBody: TmdbFavoriteRequestBody): TmdbStatusResult = client.post {
        endPointAccount(accountId, "favorite")
        json()
        setBody(requestBody)
    }.body()

    suspend fun markFavorite(
        accountId: Int,
        mediaType: TmdbMediaType,
        mediaId: Int,
        favorite: Boolean
    ): TmdbStatusResult = markFavorite(accountId, TmdbFavoriteRequestBody(mediaType, mediaId, favorite))

    suspend fun addWatchlist(accountId: Int, requestBody: TmdbWatchlistRequestBody): TmdbStatusResult = client.post {
        endPointAccount(accountId, "watchlist")
        json()
        setBody(requestBody)
    }.body()

    private fun HttpRequestBuilder.endPointAccount(accountId: Int, vararg paths: String) {
        endPointV3("account", accountId.toString(), *paths)
    }
}
