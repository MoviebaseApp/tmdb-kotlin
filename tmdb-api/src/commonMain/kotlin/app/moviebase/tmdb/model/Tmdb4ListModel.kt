package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tmdb4CreateListRequest(
    @SerialName("name") val name: String,
    @SerialName("iso_639_1") val iso639: String,
    @SerialName("iso_3166_1") val iso3166: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("public") val public: Boolean? = null
)

@Serializable
data class Tmdb4UpdateListRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("public") val public: Boolean? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("sort_by") val sortBy: String? = null
)

@Serializable
data class Tmdb4List(
    @SerialName("poster_path") val posterPath: String,
    val id: Int,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("total_results") val totalResults: Int,
    val public: Boolean,
    val revenue: String,
    val page: Int,
    val results: List<TmdbMediaListItem>,
    @SerialName("iso_639_1") val iso639: String,
    @SerialName("total_pages") val totalPages: Int,
    val description: String? = null,
    @SerialName("created_by") val createdBy: Tmdb4Account,
    @SerialName("iso_3166_1") val iso3166: String? = null,
    @SerialName("average_rating") val averageRating: Float? = null,
    @SerialName("runtime") val runtime: Int? = null,
    val name: String,
)

@Serializable
data class Tmdb4Account(
    @SerialName("gravatar_hash") val gravatarHash: String,
    val name: String,
    @SerialName("username") val userName: String
)


@Serializable
data class Tmdb4ItemsRequest(
    @SerialName("items") val items: List<Tmdb4ListItem>,
)

@Serializable
data class Tmdb4UpdateItemsRequest(
    @SerialName("items") val items: List<Tmdb4UpdateListItem>,
)

@Serializable
data class Tmdb4ListItem(
    @SerialName("media_type") val mediaType: TmdbMediaType,
    @SerialName("media_id") val mediaId: Int
)

@Serializable
data class Tmdb4UpdateListItem(
    @SerialName("media_type") val mediaType: TmdbMediaType,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("comment") val comment: String,
)


@Serializable
data class Tmdb4ItemStatus(
    @SerialName("status_message") val statusMessage: String? = null,
    @SerialName("error_message") val errorMessage: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("success") val success: Boolean? = null,
    @SerialName("status_code") val statusCode: Int,
    @SerialName("media_type") val mediaType: TmdbMediaType,
    @SerialName("media_id") val mediaId: Int,
)
