package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbPerson(
    @SerialName("name")  val name: String,
    @SerialName("id")  val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("popularity") val popularity: Float,
)

@Serializable
data class TmdbCrew(
    @SerialName("name")  val name: String,
    @SerialName("id")  val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("job") val job: String,
)

@Serializable
data class TmdbCast(
    @SerialName("name")  val name: String,
    @SerialName("id")  val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("character") val character: String,
    @SerialName("order") val order: Int,
)

