package app.moviebase.tmdb.model

import app.moviebase.tmdb.remote.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tmdb4ListMeta(
    @SerialName("iso_639_1") val iso639: String,
    val id: Int,
    val featured: Int,
    val description: String? = null,
    val revenue: String,
    val public: Boolean,
    val name: String,
    @SerialName("updated_at") @Serializable(LocalDateTimeSerializer::class) val updatedAt: LocalDateTime?,
    @SerialName("created_at") @Serializable(LocalDateTimeSerializer::class) val createdAt: LocalDateTime?,
    @SerialName("sort_by") val sortBy: String,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("runtime") val runtime: Int? = null,
    @SerialName("average_rating") val averageRating: Float? = null,
    @SerialName("iso_3166_1") val iso3166: String? = null,
    @SerialName("adult") val adult: Boolean,
    @SerialName("number_of_items") val numberOfItems: Int,
    @SerialName("poster_path") val posterPath: String,
)
