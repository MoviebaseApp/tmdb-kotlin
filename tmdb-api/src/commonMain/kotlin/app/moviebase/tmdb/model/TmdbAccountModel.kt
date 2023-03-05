package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbAccountDetails(
    @SerialName("id") val id: Int,
    @SerialName("username") val userName: String,
    @SerialName("name") val name: String?,
    @SerialName("include_adult") val includeAdult: Boolean,
    @SerialName("iso_639_1") val language: String,
    @SerialName("iso_3166_1") val region: String,
    @SerialName("avatar") val avatar: TmdbAvatar? = null
)

@Serializable
data class TmdbAvatar(
    @SerialName("gravatar") val gravatar: TmdbGravatar,
    @SerialName("tmdb") val tmdb: TmdbGravatar
)

@Serializable
data class TmdbGravatar(
    @SerialName("hash") val hash: String? = null
)

@Serializable
data class TmdbAvatarPath(
    @SerialName("avatar_path") val avatarPath: String? = null
)

@Serializable
data class TmdbFavoriteRequestBody(
    @SerialName("media_type") val mediaType: TmdbMediaType,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("favorite") val favorite: Boolean
)

@Serializable
data class TmdbWatchlistRequestBody(
    @SerialName("media_type") val mediaType: TmdbMediaType,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("watchlist") val watchlist: Boolean
)
