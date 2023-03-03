package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbConfiguration(
    @SerialName("images") val images: TmdbImagesConfiguration,
    @SerialName("change_keys") val changeKeys: List<String> = emptyList(),
)

@Serializable
data class TmdbImagesConfiguration(
    @SerialName("base_url") val baseUrl: String,
    @SerialName("secure_base_url") val secureBaseUrl: String,
    @SerialName("backdrop_sizes") val backdropSizes: List<String> = emptyList(),
    @SerialName("poster_sizes") val posterSizes: List<String> = emptyList(),
    @SerialName("logo_sizes") val logoSizes: List<String> = emptyList(),
    @SerialName("profile_sizes") val profileSizes: List<String> = emptyList(),
    @SerialName("still_sizes") val stillSizes: List<String> = emptyList(),
)
