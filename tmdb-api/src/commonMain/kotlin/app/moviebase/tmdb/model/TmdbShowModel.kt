package app.moviebase.tmdb.model

import app.moviebase.tmdb.remote.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbShow(
    @SerialName("id") override val id: Int,
    @SerialName("name") val name: String,
    @SerialName("genre_ids") val genresIds: List<Int>,
    @SerialName("vote_average") val voteAverage: Float,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("popularity") val popularity: Float,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("first_air_date") @Serializable(LocalDateSerializer::class) val firstAirDate: LocalDate?,
) : TmdbAnyMedia

@Serializable
data class TmdbShowDetail(
    @SerialName("id") override val id: Int,
    val name: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    val popularity: Float,
    @SerialName("first_air_date") @Serializable(LocalDateSerializer::class) val firstAirDate: LocalDate?,
    @SerialName("genres") val genres: List<TmdbGenre>,
    @SerialName("last_episode_to_air") val lastEpisodeToAir: TmdbEpisode? = null,
    @SerialName("next_episode_to_air") val nextEpisodeToAir: TmdbEpisode? = null,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int,
    @SerialName("number_of_seasons") val numberOfSeasons: Int,
    @SerialName("episode_run_time") val episodeRuntime: List<Int>,
    val seasons: List<TmdbSeason>,
    val networks: List<TmdbNetwork>,
    val status: TmdbShowStatus,
    val type: TmdbShowType,
    @SerialName("vote_average") val voteAverage: Float,
    @SerialName("external_ids") val externalIds: TmdbExternalIds,
    @SerialName("watch/providers") val watchProviders: TmdbProviderResult? = null,
) : TmdbAnyMedia


@Serializable
data class TmdbSeason(
    val id: Int,
    @SerialName("air_date") @Serializable(LocalDateSerializer::class) val airDate: LocalDate? = null,
    @SerialName("episode_count") val episodeCount: Int? = null,
    val name: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int,
    val episodes: List<TmdbEpisode>? = null,
) {

    val numberOfEpisodes get() = episodeCount ?: episodes?.size ?: 0

}

@Serializable
data class TmdbEpisode(
    override val id: Int,
    @SerialName("episode_number") val episodeNumber: Int,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("air_date") @Serializable(LocalDateSerializer::class) val airDate: LocalDate? = null,
    val name: String? = null,
    @SerialName("vote_average") val voteAverage: Float? = null,
    @SerialName("still_path") val stillPath: String? = null,
) : TmdbAnyMedia

@Serializable
data class TmdbNetwork(
    val id: Int,
    val name: String
)
