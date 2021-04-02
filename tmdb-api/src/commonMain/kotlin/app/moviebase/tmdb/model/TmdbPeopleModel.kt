package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbPerson(
    @SerialName("name") val name: String,
    @SerialName("id") override val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("popularity") val popularity: Float,
): TmdbAnyMedia, TmdbSearchable

@Serializable
data class TmdbPersonDetail(
    @SerialName("also_known_as") val alsoKnownAs: String,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val birthday: String,
    @SerialName("deathday") val deathday: String? = null,
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("popularity") val popularity: Float,
    @SerialName("place_of_birth") val placeOfBirth: String,
    @SerialName("external_ids") val externalIds: TmdbExternalIds? = null,
)

@Serializable
data class TmdbCrew(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("job") val job: String,
)

@Serializable
data class TmdbCast(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("character") val character: String,
    @SerialName("order") val order: Int,
)

