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
data class TmdbMovie(
    override val id: Int,
    val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("genre_ids") val genresIds: List<Int>,
    @SerialName("vote_average") val voteAverage: Float,
    val popularity: Float,
    @SerialName("release_date") @Serializable(LocalDateSerializer::class) val releaseDate: LocalDate? = null,
) : TmdbAnyMedia {

    val posterImage get(): TmdbImage? = TmdbImage.poster(posterPath)
    val backdropImage get(): TmdbImage? = TmdbImage.backdrop(backdropPath)

}

@Serializable
data class TmdbMovieDetail(
    override val id: Int,
    val title: String,
    @SerialName("imdb_id") val imdbId: String? = null,
    val runtime: Int? = null,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("vote_average") val voteAverage: Float,
    val status: TmdbMovieStatus,
    @SerialName("genres") val genres: List<TmdbGenre>,
    val popularity: Float,
    @SerialName("release_date") @Serializable(LocalDateSerializer::class) val releaseDate: LocalDate?,
    @SerialName("release_dates") val releaseDates: TmdbResult<TmdbReleaseDates>? = null,
    @SerialName("watch/providers") val watchProviders: TmdbProviderResult? = null,
) : TmdbAnyMedia {

    val posterImage get(): TmdbImage? = TmdbImage.poster(posterPath)
    val backdropImage get(): TmdbImage? = TmdbImage.backdrop(backdropPath)

}


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
