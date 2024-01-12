package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbKeywordDetail(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("id") override val id: Int,
    @SerialName("name") val name: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String?
) : TmdbSearchable

@Serializable
data class TmdbKeywordPageResult(
    @SerialName("page") override val page: Int,
    @SerialName("results") override val results: List<TmdbKeywordDetail> = emptyList(),
    @SerialName("total_results") override val totalResults: Int,
    @SerialName("total_pages") override val totalPages: Int
) : TmdbPageResult<TmdbKeywordDetail>
