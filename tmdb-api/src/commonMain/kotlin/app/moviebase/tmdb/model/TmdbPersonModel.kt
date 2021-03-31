package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TmdbPerson(
    val name: String,
    val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("popularity") val popularity: Float,
)
