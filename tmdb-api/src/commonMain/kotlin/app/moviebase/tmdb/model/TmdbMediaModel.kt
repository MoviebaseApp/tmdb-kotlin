package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.core.LocalDateSerializer
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

interface TmdbAnyItem {
    val id: Int
}

interface TmdbBackdropItem {
    val backdropPath: String?
    val backdropImage get(): TmdbImage? = TmdbImage.backdrop(backdropPath)
}

interface TmdbPosterItem {
    val posterPath: String?
    val posterImage get(): TmdbImage? = TmdbImage.poster(posterPath)
}

interface TmdbProfileItem {
    val profilePath: String?
    val profileImage get(): TmdbImage? = TmdbImage.profile(profilePath)
}

interface TmdbRatingItem {
    val voteAverage: Float?
    val voteCount: Int?
}

/**
 * This item is a movie or show which can be listed.
 */
@Polymorphic
@Serializable
sealed interface TmdbMediaListItem : TmdbAnyItem, TmdbBackdropItem, TmdbPosterItem, TmdbRatingItem {
    val overview: String
    val genresIds: List<Int>
    val popularity: Float
    val originalLanguage: String
}

@Polymorphic
@Serializable
sealed interface TmdbSearchableListItem : TmdbAnyItem, TmdbSearchable

@Serializable
data class TmdbMultiPageResult(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbSearchableListItem> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int
) : TmdbPageResult<TmdbSearchableListItem>

@Serializable
@SerialName("movie")
data class TmdbMovie(
    @SerialName("poster_path") override val posterPath: String?,
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("overview") override val overview: String,
    @SerialName("release_date")
    @Serializable(LocalDateSerializer::class)
    val releaseDate: LocalDate? = null,
    @SerialName("genre_ids") override val genresIds: List<Int>,
    @SerialName("id") override val id: Int,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") override val originalLanguage: String,
    @SerialName("title") val title: String,
    @SerialName("backdrop_path") override val backdropPath: String?,
    @SerialName("popularity") override val popularity: Float,
    @SerialName("vote_count") override val voteCount: Int,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") override val voteAverage: Float
) : TmdbMediaListItem, TmdbSearchableListItem

@Serializable
data class TmdbMoviePageResult(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbMovie> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int
) : TmdbPageResult<TmdbMovie>

@Serializable
@SerialName("tv")
data class TmdbShow(
    @SerialName("poster_path") override val posterPath: String?,
    @SerialName("popularity") override val popularity: Float,
    @SerialName("id") override val id: Int,
    @SerialName("backdrop_path") override val backdropPath: String?,
    @SerialName("vote_average") override val voteAverage: Float,
    @SerialName("overview") override val overview: String,
    @SerialName("first_air_date")
    @Serializable(LocalDateSerializer::class)
    val firstAirDate: LocalDate? = null,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("genre_ids") override val genresIds: List<Int>,
    @SerialName("original_language") override val originalLanguage: String,
    @SerialName("vote_count") override val voteCount: Int,
    @SerialName("name") val name: String,
    @SerialName("original_name") val originalName: String
) : TmdbMediaListItem, TmdbSearchableListItem

@Serializable
data class TmdbShowPageResult(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbShow> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int
) : TmdbPageResult<TmdbShow>

@Serializable
@SerialName("person")
data class TmdbPerson(
    @SerialName("adult") val adult: Boolean,
    @SerialName("gender") val gender: TmdbGender,
    @SerialName("id") override val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") override val name: String,
    @SerialName("profile_path") override val profilePath: String? = null,
    @SerialName("popularity") override val popularity: Float
) : TmdbAnyPerson, TmdbSearchableListItem

@Serializable
data class TmdbPersonPageResult(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbPerson> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int
) : TmdbPageResult<TmdbPerson>
