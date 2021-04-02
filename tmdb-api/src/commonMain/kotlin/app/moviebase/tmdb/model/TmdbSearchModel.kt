package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbFindResults(
    @SerialName("movie_results") val movieResults: List<TmdbMovie> = emptyList(),
    @SerialName("tv_results") val showResults: List<TmdbShow> = emptyList()
)

