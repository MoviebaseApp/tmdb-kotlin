package app.moviebase.tmdb.model

import app.moviebase.tmdb.remote.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
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
enum class TmdbShowStatus(val value: String) {
    @SerialName("Returning Series")
    RETURNING_SERIES("Returning Series"),

    @SerialName("In Production")
    IN_PRODUCTION("In Production"),

    @SerialName("Planned")
    PLANNED("Planned"),

    @SerialName("Canceled")
    CANCELED("Canceled"),

    @SerialName("Ended")
    ENDED("Ended"),

    @SerialName("Pilot")
    PILOT("Pilot");

    companion object {
        fun find(value: String?) = values().find { it.value == value }
    }
}

@Serializable
enum class TmdbShowType(val value: String) {
    @SerialName("Scripted")
    SCRIPTED("Scripted"),

    @SerialName("Reality")
    REALITY("Reality"),

    @SerialName("Documentary")
    DOCUMENTARY("Documentary"),

    @SerialName("News")
    NEWS("News"),

    @SerialName("Talk")
    TALK("Talk"),

    @SerialName("Talk Show")
    TALK_SHOW("Talk Show"),

    @SerialName("Show")
    SHOW("Show"),

    @SerialName("Miniseries")
    MINISERIES("Miniseries");

    companion object {
        fun find(value: String?) = values().find { it.value == value }
    }
}

@Serializable
enum class TmdbExternalSource(val value: String) {
    @SerialName("imdb_id")
    IMDB("imdb_id"),
    @SerialName("tvdb_id")
    TVDB("tvdb_id")
}


enum class AppendResponse(val value: String) {
    VIDEOS("videos"),
    RELEASES_DATES("release_dates"),
    REVIEWS("reviews"),
    CREDITS("credits"),
    IMAGES("images"),
    TAGGED_IMAGES("tagged_images"),
    EXTERNAL_IDS("external_ids"),
    CONTENT_RATING("content_ratings"),
    MOVIE_CREDITS("movie_credits"),
    TV_CREDITS("tv_credits"),
    WATCH_PROVIDERS("watch/providers");

    companion object {
        fun build(appendResponses: Iterable<AppendResponse>) = appendResponses.joinToString(",") { it.value }
    }

}

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
data class TmdbResult<T>(
    val results: List<T>
)

interface TmdbAnyMedia {
    val id: Int
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
data class TmdbTranslations(
    val id: Int,
    val translations: List<TmdbTranslation>
)

@Serializable
data class TmdbTranslation(
    @SerialName("iso_3166_1") val iso3166: String,
    @SerialName("iso_639_1") val iso639: String,
    val name: String,
    @SerialName("english_name") val englishName: String,
)


@Serializable
data class TmdbGenre(
    val id: Int
)

@Serializable
data class TmdbExternalIds(
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("tvdb_id") val tvdbId: Int? = null
)



