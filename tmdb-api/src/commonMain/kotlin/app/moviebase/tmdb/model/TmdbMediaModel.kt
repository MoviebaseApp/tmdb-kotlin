package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.remote.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TmdbMediaType(val value: String) {
    @SerialName("movie")
    MOVIE("movie"),

    @SerialName("tv")
    SHOW("tv"),

    @SerialName("season")
    SEASON("season"),

    @SerialName("episode")
    EPISODE("episode")
}

interface TmdbAnyMedia {
    val id: Int
}

interface TmdbBackdropMedia {
    val backdropPath: String?
    val backdropImage get(): TmdbImage? = TmdbImage.backdrop(backdropPath)
}

interface TmdbPosterMedia {
    val posterPath: String?
    val posterImage get(): TmdbImage? = TmdbImage.poster(posterPath)
}

/**
 * This item is a movie or show which can be listed.
 */
@Polymorphic
@Serializable
sealed class TmdbMediaListItem : TmdbAnyMedia, TmdbBackdropMedia, TmdbPosterMedia {
    abstract val voteAverage: Float
    abstract val voteCount: Int
    abstract val overview: String
    abstract val genresIds: List<Int>
    abstract val popularity: Float
    abstract val originalLanguage: String
}

@Serializable
@SerialName("movie")
data class TmdbMovie(
    @SerialName("poster_path") override val posterPath: String?,
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("overview") override val overview: String,
    @SerialName("release_date") @Serializable(LocalDateSerializer::class) val releaseDate: LocalDate? = null,
    @SerialName("genre_ids") override val genresIds: List<Int>,
    @SerialName("id") override val id: Int,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") override val originalLanguage: String,
    @SerialName("title") val title: String,
    @SerialName("backdrop_path") override val backdropPath: String?,
    @SerialName("popularity") override val popularity: Float,
    @SerialName("vote_count") override val voteCount: Int,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") override val voteAverage: Float,
) : TmdbMediaListItem(), TmdbSearchable

@Serializable
@SerialName("tv")
data class TmdbShow(
    @SerialName("poster_path") override val posterPath: String?,
    @SerialName("popularity") override val popularity: Float,
    @SerialName("id") override val id: Int,
    @SerialName("backdrop_path") override val backdropPath: String?,
    @SerialName("vote_average") override val voteAverage: Float,
    @SerialName("overview") override val overview: String,
    @SerialName("first_air_date") @Serializable(LocalDateSerializer::class) val firstAirDate: LocalDate?,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("genre_ids") override val genresIds: List<Int>,
    @SerialName("original_language") override val originalLanguage: String,
    @SerialName("vote_count") override val voteCount: Int,
    @SerialName("name") val name: String,
    @SerialName("original_name") val originalName: String,
) : TmdbMediaListItem(), TmdbSearchable
