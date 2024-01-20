package app.moviebase.tmdb.model

import app.moviebase.tmdb.core.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object TmdbCrewJobType {

    const val DIRECTOR = "Director"
    const val PRODUCER = "Producer"
    const val WRITER = "Writer"
    const val STORY = "Story"
    const val SCREENPLAY = "Screenplay"
    const val CHARACTERS = "Characters"
    const val ART_DIRECTION = "Art Direction"
    const val EDITOR = "Editor"
    const val NOVEL = "Novel"
    const val EXECUTIVE_PRODUCER = "Executive Producer"

    val importantJobs = listOf(DIRECTOR, PRODUCER, WRITER, STORY, SCREENPLAY, CHARACTERS)
}

@Serializable
enum class TmdbGender(val value: Int) {
    @SerialName("0")
    UNKNOWN(0),

    @SerialName("1")
    FEMALE(1),

    @SerialName("2")
    MALE(2),

    @SerialName("3")
    NON_BINARY(3)
}

@Serializable
data class TmdbCredits(
    @SerialName("cast") val cast: List<TmdbCast>,
    @SerialName("crew") val crew: List<TmdbCrew>
) {

    /**
     * Groups the crew by job.
     */
    fun getGroupedCrew(): Map<String, List<TmdbCrew>> {
        val jobsSet = TmdbCrewJobType.importantJobs.toSet()
        return crew.filter { jobsSet.contains(it.job) }.groupBy { it.job }
    }

    /**
     * Sorts the crew by most important job.
     */
    fun getSortedCrew(): List<TmdbCrew> {
        val jobsSet = TmdbCrewJobType.importantJobs.toSet()
        val orderByJob = TmdbCrewJobType.importantJobs.withIndex().associate { it.value to it.index }
        return crew.filter { jobsSet.contains(it.job) }.sortedBy { orderByJob[it.job] }
    }
}

interface TmdbAnyPerson : TmdbAnyItem, TmdbProfileItem {
    val name: String
    val popularity: Float?
}

@Serializable
data class TmdbAggregateCredits(
    @SerialName("cast") val cast: List<TmdbAggregateCast>,
    @SerialName("crew") val crew: List<TmdbAggregateCrew>
)

@Serializable
data class TmdbPersonDetail(
    @SerialName("also_known_as") val alsoKnownAs: List<String>,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val birthday: String? = null,
    @SerialName("deathday") val deathday: String? = null,
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("id") override val id: Int,
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("name") override val name: String,
    @SerialName("profile_path") override val profilePath: String? = null,
    @SerialName("popularity") override val popularity: Float,
    @SerialName("place_of_birth") val placeOfBirth: String? = null,
    @SerialName("external_ids") val externalIds: TmdbExternalIds? = null,
    @SerialName("tagged_images") val taggedImages: TmdbImagePageResult? = null,
    @SerialName("movie_credits") val movieCredits: TmdbPersonMovieCredits? = null,
    @SerialName("tv_credits") val tvCredits: TmdbPersonShowCredits? = null
) : TmdbAnyPerson

@Serializable
data class TmdbCrew(
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("gender") val gender: TmdbGender = TmdbGender.UNKNOWN,
    @SerialName("id") override val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") override val name: String,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") override val popularity: Float? = null,
    @SerialName("profile_path") override val profilePath: String? = null,
    @SerialName("credit_id") val creditId: String,
    @SerialName("department") val department: String,
    @SerialName("job") val job: String
) : TmdbAnyPerson

@Serializable
data class TmdbCast(
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("gender") val gender: TmdbGender,
    @SerialName("id") override val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") override val name: String,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") override val popularity: Float? = null,
    @SerialName("profile_path") override val profilePath: String? = null,
    @SerialName("cast_id") val castId: Int? = null,
    @SerialName("character") val character: String,
    @SerialName("credit_id") val creditId: String,
    @SerialName("order") val order: Int
) : TmdbAnyPerson

@Serializable
data class TmdbAggregateCast(
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("gender") val gender: TmdbGender,
    @SerialName("id") val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") val name: String,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") val popularity: Float? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("roles") val roles: List<TmdbRole>,
    @SerialName("total_episode_count") val totalEpisodeCount: Int,
    @SerialName("order") val order: Int
)

@Serializable
data class TmdbAggregateCrew(
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("gender") val gender: TmdbGender,
    @SerialName("id") override val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") override val name: String,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") override val popularity: Float? = null,
    @SerialName("profile_path") override val profilePath: String? = null,
    @SerialName("jobs") val jobs: List<TmdbJob>,
    @SerialName("department") val department: String,
    @SerialName("total_episode_count") val totalEpisodeCount: Int
) : TmdbAnyPerson

@Serializable
data class TmdbRole(
    @SerialName("credit_id") val creditId: String,
    @SerialName("character") val character: String,
    @SerialName("episode_count") val episodeCount: Int
)

@Serializable
data class TmdbJob(
    @SerialName("credit_id") val creditId: String,
    @SerialName("job") val job: String,
    @SerialName("episode_count") val episodeCount: Int
)

@Serializable
data class TmdbTaggedImage(
    val media: TmdbTaggedMedia
)

@Serializable
data class TmdbShowCreatedBy(
    @SerialName("id") val id: Int,
    @SerialName("credit_id") val creditId: String? = null,
    @SerialName("gender") val gender: TmdbGender? = null,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String? = null,
)

@Serializable
data class TmdbImagePageResult(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbTaggedImage> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int
) : TmdbPageResult<TmdbTaggedImage>

@Serializable
data class TmdbTaggedMedia(
    @SerialName("backdrop_path") val backdropPath: String?
)

@Serializable
data class TmdbPersonMovieCredits(
    @SerialName("cast") val cast: List<TmdbPersonCredit.Movie.Cast>,
    @SerialName("crew") val crew: List<TmdbPersonCredit.Movie.Crew>
)

@Serializable
data class TmdbPersonShowCredits(
    @SerialName("cast") val cast: List<TmdbPersonCredit.Show.Cast>,
    @SerialName("crew") val crew: List<TmdbPersonCredit.Show.Crew>
)

sealed interface TmdbPersonCredit : TmdbAnyItem, TmdbBackdropItem, TmdbPosterItem {

    val voteAverage: Float
    val voteCount: Int
    val overview: String
    val genresIds: List<Int>
    val popularity: Float
    val originalLanguage: String

    sealed interface Movie : TmdbPersonCredit {

        val adult: Boolean
        val releaseDate: LocalDate?
        val originalTitle: String?
        val title: String?
        val video: Boolean

        @Serializable
        data class Cast(
            @SerialName("poster_path") override val posterPath: String?,
            @SerialName("adult") override val adult: Boolean = false,
            @SerialName("overview") override val overview: String,
            @SerialName("release_date")
            @Serializable(LocalDateSerializer::class)
            override val releaseDate: LocalDate? = null,
            @SerialName("genre_ids") override val genresIds: List<Int>,
            @SerialName("id") override val id: Int,
            @SerialName("original_title") override val originalTitle: String? = null,
            @SerialName("original_language") override val originalLanguage: String,
            @SerialName("title") override val title: String? = null,
            @SerialName("backdrop_path") override val backdropPath: String?,
            @SerialName("popularity") override val popularity: Float,
            @SerialName("video") override val video: Boolean = false,
            @SerialName("vote_average") override val voteAverage: Float,
            @SerialName("vote_count") override val voteCount: Int,
            @SerialName("character") val character: String,
            @SerialName("credit_id") val creditId: String,
            @SerialName("order") val order: Int? = null
        ) : Movie

        @Serializable
        data class Crew(
            @SerialName("poster_path") override val posterPath: String?,
            @SerialName("adult") override val adult: Boolean = false,
            @SerialName("overview") override val overview: String,
            @SerialName("release_date")
            @Serializable(LocalDateSerializer::class)
            override val releaseDate: LocalDate? = null,
            @SerialName("genre_ids") override val genresIds: List<Int>,
            @SerialName("id") override val id: Int,
            @SerialName("original_title") override val originalTitle: String? = null,
            @SerialName("original_language") override val originalLanguage: String,
            @SerialName("title") override val title: String? = null,
            @SerialName("backdrop_path") override val backdropPath: String?,
            @SerialName("popularity") override val popularity: Float,
            @SerialName("video") override val video: Boolean = false,
            @SerialName("vote_count") override val voteCount: Int,
            @SerialName("vote_average") override val voteAverage: Float,
            @SerialName("credit_id") val creditId: String,
            @SerialName("department") val department: String,
            @SerialName("job") val job: String
        ) : Movie
    }

    sealed interface Show : TmdbPersonCredit {

        val firstAirDate: LocalDate?
        val originCountry: List<String>
        val name: String
        val originalName: String

        @Serializable
        data class Cast(
            @SerialName("poster_path") override val posterPath: String?,
            @SerialName("popularity") override val popularity: Float,
            @SerialName("id") override val id: Int,
            @SerialName("backdrop_path") override val backdropPath: String?,
            @SerialName("vote_average") override val voteAverage: Float,
            @SerialName("overview") override val overview: String,
            @SerialName("first_air_date")
            @Serializable(LocalDateSerializer::class)
            override val firstAirDate: LocalDate? = null,
            @SerialName("origin_country") override val originCountry: List<String>,
            @SerialName("genre_ids") override val genresIds: List<Int>,
            @SerialName("original_language") override val originalLanguage: String,
            @SerialName("vote_count") override val voteCount: Int,
            @SerialName("name") override val name: String,
            @SerialName("original_name") override val originalName: String,
            @SerialName("character") val character: String,
            @SerialName("credit_id") val creditId: String,
            @SerialName("order") val order: Int? = null
        ) : Show

        @Serializable
        data class Crew(
            @SerialName("poster_path") override val posterPath: String?,
            @SerialName("popularity") override val popularity: Float,
            @SerialName("id") override val id: Int,
            @SerialName("backdrop_path") override val backdropPath: String?,
            @SerialName("vote_average") override val voteAverage: Float,
            @SerialName("overview") override val overview: String,
            @SerialName("first_air_date")
            @Serializable(LocalDateSerializer::class)
            override val firstAirDate: LocalDate? = null,
            @SerialName("origin_country") override val originCountry: List<String>,
            @SerialName("genre_ids") override val genresIds: List<Int>,
            @SerialName("original_language") override val originalLanguage: String,
            @SerialName("vote_count") override val voteCount: Int,
            @SerialName("name") override val name: String,
            @SerialName("original_name") override val originalName: String,
            @SerialName("credit_id") val creditId: String,
            @SerialName("department") val department: String,
            @SerialName("job") val job: String
        ) : Show
    }
}
