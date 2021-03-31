package app.moviebase.tmdb.model

import app.moviebase.tmdb.remote.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbMovie(
    override val id: Int,
    val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("genre_ids") val genresIds: List<Int>,
    @SerialName("vote_average") val voteAverage: Float,
    val popularity: Float,
    @SerialName("release_date") @Serializable(LocalDateSerializer::class) val releaseDate: LocalDate? = null,
) : TmdbAnyMedia

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
) : TmdbAnyMedia
