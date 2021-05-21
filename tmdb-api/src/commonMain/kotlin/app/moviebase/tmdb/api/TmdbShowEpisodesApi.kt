package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbExternalIds
import app.moviebase.tmdb.model.TmdbSeason
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterAppendResponses
import app.moviebase.tmdb.remote.parameterLanguage
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbShowEpisodesApi(private val client: HttpClient) {

    suspend fun getDetails(
        showId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        language: String? = null,
        appendResponses: Iterable<AppendResponse>? = null,
        includeImageLanguages: String? = null
    ): TmdbSeason = client.get {
        endPointEpisode(showId, seasonNumber, episodeNumber)
        parameterLanguage(language)
        parameterAppendResponses(appendResponses)
        includeImageLanguages?.let { parameter("include_image_language", it) }
    }

    suspend fun getExternalIds(showId: Int, seasonNumber: Int, episodeNumber: Int): TmdbExternalIds = client.get {
        endPointEpisode(showId, seasonNumber, episodeNumber,"external_ids")
    }

    private fun HttpRequestBuilder.endPointEpisode(showId: Int, seasonNumber: Int, episodeNumber: Int, vararg paths: String) {
        endPointV3("tv", showId.toString(), "season", seasonNumber.toString(), "episode", episodeNumber.toString(), *paths)
    }

}
