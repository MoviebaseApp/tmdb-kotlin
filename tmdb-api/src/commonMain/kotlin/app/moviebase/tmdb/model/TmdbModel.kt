package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImageType
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
enum class TmdbExternalSource(val value: String) {
    @SerialName("imdb_id")
    IMDB("imdb_id"),

    @SerialName("tvdb_id")
    TVDB("tvdb_id");
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

object TmdbStatusCode {
    const val SUCCESS_ADDED = 1
    const val SUCCESS_UPDATED = 12
    const val SUCCESS_DELETED = 13
    const val RESOURCE_NOT_FOUND = 34
}


@Serializable
data class TmdbResult<T>(
    val results: List<T>
)

@Serializable
data class TmdbStatusResult(
    @SerialName("status_code") val statusCode: Int
)

interface TmdbAnyMedia {
    val id: Int
}

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
    val id: Int,
    val name: String,
)

@Serializable
data class TmdbKeyword(
    val id: Int,
    val name: String,
)

@Serializable
data class TmdbExternalIds(
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("tvdb_id") val tvdbId: Int? = null,
    @SerialName("id") val id: Int,
    @SerialName("facebook_id") val facebook: String? = null,
    @SerialName("instagram_id") val instagram: String? = null,
    @SerialName("twitter_id") val twitter: String? = null,
)


@Serializable
enum class TmdbVideoType(val value: String) {
    @SerialName("Trailer")
    TRAILER("Trailer"),

    @SerialName("Teaser")
    TEASER("Teaser"),

    @SerialName("Clip")
    CLIP("Clip"),

    @SerialName("Featurette")
    FEATURETTE("Featurette");
}


@Serializable
data class TmdbVideo(
    @SerialName("id") val id: Int,
    @SerialName("iso_639_1") val iso639: String,
    @SerialName("iso_3166_1") val iso3166: String,
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("site") val site: String,
    @SerialName("type") val type: String,
)



@Serializable
data class TmdbImages(
    @SerialName("id") val id: Int,
    @SerialName("posters") val posters: List<TmdbFileImage>,
    @SerialName("backdrops") val backdrops: List<TmdbFileImage>,
)

@Serializable
data class TmdbFileImage(
    @SerialName("file_path") val filePath: String,
    @SerialName("aspect_ratio") val aspectRation: Double,
)

@Serializable
data class TmdbLogoImage(
    @SerialName("file_path") val filePath: String?
)

@Serializable
data class TmdbCompany(
    @SerialName("id") val id: Int,
    @SerialName("logo_path") val logoPath: String? = null,
    @SerialName("name") val name: String,
    @SerialName("origin_country") val originCountry: String
)
