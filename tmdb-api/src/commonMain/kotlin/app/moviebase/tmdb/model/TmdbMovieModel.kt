package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.remote.LocalDateSerializer
import app.moviebase.tmdb.remote.LocalDateTimeSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TmdbReleaseType(val value: Int) {
    @SerialName("1")
    PREMIERE(1),

    @SerialName("2")
    THEATRICAL_LIMITED(2),

    @SerialName("3")
    THEATRICAL(3),

    @SerialName("4")
    DIGITAL(4),

    @SerialName("5")
    PHYSICAL(5),

    @SerialName("6")
    TV(6);

    companion object {
        fun find(value: Int?) = values().find { it.value == value }
    }
}

@Serializable
enum class TmdbMovieStatus(val value: String) {
    @SerialName("Rumored")
    RUMORED("Rumored"),

    @SerialName("Planned")
    PLANNED("Planned"),

    @SerialName("In Production")
    IN_PRODUCTION("In Production"),

    @SerialName("Post Production")
    POST_PRODUCTION("Post Production"),

    @SerialName("Released")
    RELEASED("Released"),

    @SerialName("Canceled")
    CANCELED("Canceled");

    companion object {
        fun find(value: String?) = values().find { it.value == value }
    }
}

@Serializable
data class TmdbMovieDetail(
    val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    val budget: Long,
    @SerialName("genres") val genres: List<TmdbGenre>,
    val homepage: String? = null,
    val id: Int,
    @SerialName("imdb_id") val imdbId: String? = null,
    val title: String,
    val runtime: Int? = null,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val voteAverage: Float,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("external_ids") val externalIds: TmdbExternalIds? = null,
    val status: TmdbMovieStatus,
    val tagline: String,
    val video: Boolean,
    val popularity: Float,
    @SerialName("release_date") @Serializable(LocalDateSerializer::class) val releaseDate: LocalDate?,
    val revenue: Long,
    @SerialName("release_dates") val releaseDates: TmdbResult<TmdbReleaseDates>? = null,
    @SerialName("watch/providers") val watchProviders: TmdbProviderResult? = null,
    @SerialName("credits") val credits: TmdbCredits? = null,
    @SerialName("videos") val videos: TmdbResult<TmdbVideo>? = null,
) {

    val posterImage get(): TmdbImage? = TmdbImage.poster(posterPath)
    val backdropImage get(): TmdbImage? = TmdbImage.backdrop(backdropPath)

}

fun TmdbResult<TmdbReleaseDates>.getCertification(country: String): String? =
    getReleaseDatesBy(country)
        ?.sortedBy { it.type }
        ?.find { !it.certification.isNullOrBlank() }
        ?.certification

fun TmdbResult<TmdbReleaseDates>.getReleaseDateBy(country: String): TmdbReleaseDate? =
    getReleaseDatesBy(country)?.minByOrNull { it.type }

fun TmdbResult<TmdbReleaseDates>.getReleaseDatesBy(country: String): List<TmdbReleaseDate>? =
    results.find { it.iso3166 == country }?.releaseDates

@Serializable
data class TmdbReleaseDates(
    @SerialName("iso_3166_1") val iso3166: String,
    @SerialName("release_dates") val releaseDates: List<TmdbReleaseDate>
)

@Serializable
data class TmdbReleaseDate(
    @SerialName("iso_639_1") val iso639: String? = null,
    @SerialName("release_date") @Serializable(LocalDateTimeSerializer::class) val releaseDate: LocalDateTime?,
    val certification: String? = null,
    val type: TmdbReleaseType
)

@Serializable
data class TmdbCountry(
    @SerialName("iso_3166_1") val iso3166: String,
    val name: String
)

@Serializable
data class TmdbBelongsToCollection(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("parts") val parts: List<TmdbMovie>,
)

@Serializable
data class TmdbReview(
    @SerialName("id") val id: String,
    val author: String,
    val content: String,
    val url: String,
)
