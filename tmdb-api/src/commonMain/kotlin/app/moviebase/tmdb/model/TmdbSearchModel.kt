package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbFindResults(
    @SerialName("movie_results") val movieResults: List<TmdbMovie> = emptyList(),
    @SerialName("tv_results") val showResults: List<TmdbShow> = emptyList()
)

interface SearchResults<T> {
    val page: Int
    val results: List<T>
    val totalResults: Int
    val totalPages: Int
}

@Serializable
data class TmdbMovieSearchResults(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbMovie> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int,
) : SearchResults<TmdbMovie>

@Serializable
data class TmdbShowSearchResults(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbShow> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int,
) : SearchResults<TmdbShow>

@Serializable
data class TmdbPersonSearchResults(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbPerson> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int,
) : SearchResults<TmdbPerson>
