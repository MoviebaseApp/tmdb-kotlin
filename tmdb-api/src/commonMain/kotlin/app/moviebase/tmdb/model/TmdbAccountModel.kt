package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDetails(
    val id: String,
    @SerialName("username") val userName: String,
    val name: String,
    val avatar: TmdbAvatar
)

@Serializable
data class TmdbAvatar(
    val gravatar: TmdbGravatar
)

@Serializable
data class TmdbGravatar(
    val hash: String?
)

@Serializable
data class TmdbFavoriteRequestBody(
    @SerialName("media_type") val mediaType: String,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("favorite") val favorite: Boolean
)


@Serializable
data class TmdbWatchlistRequestBody(
    @SerialName("media_type") val mediaType: String,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("watchlist") val watchlist: Boolean
)


