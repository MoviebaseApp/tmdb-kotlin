package app.moviebase.tmdb.remote

import app.moviebase.tmdb.TmdbUrlParameter
import io.ktor.client.request.*

internal object TmdbHttpClientFactory {

    fun create(tmdbApiKey: String, logLevel: TmdbLogLevel) = buildHttpClient(logLevel) {
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
    }

    fun createWithSession(tmdbApiKey: String, logLevel: TmdbLogLevel, tmdbSessionProvider: TmdbSessionProvider?) =
        buildHttpClient(logLevel) {
            val sessionId = tmdbSessionProvider?.getId()
            requireNotNull(sessionId) { "session id is not set in the Tmdb3 instance" }

            it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
            it.parameter(TmdbUrlParameter.SESSION_ID, sessionId)
        }
}
