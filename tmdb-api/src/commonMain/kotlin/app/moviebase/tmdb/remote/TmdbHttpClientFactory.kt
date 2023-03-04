package app.moviebase.tmdb.remote

import app.moviebase.tmdb.TmdbUrlParameter
import io.ktor.client.*
import io.ktor.client.request.*

internal object TmdbHttpClientFactory {

    fun create(
        tmdbApiKey: String,
        logLevel: TmdbLogLevel,
        httpClientConfigBlock: (HttpClientConfig<*>.() -> Unit)? = null,
    ) = buildHttpClient(
        logLevel = logLevel,
        httpClientConfigBlock = httpClientConfigBlock,
    ).apply {
        interceptRequest {
            it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
        }
    }

    fun createWithSession(
        tmdbApiKey: String,
        logLevel: TmdbLogLevel,
        tmdbSessionProvider: TmdbSessionProvider?,
        httpClientConfigBlock: (HttpClientConfig<*>.() -> Unit)? = null,
    ) = buildHttpClient(
        logLevel = logLevel,
        httpClientConfigBlock = httpClientConfigBlock
    ).apply {
        interceptRequest {
            val sessionId = tmdbSessionProvider?.getId()
            requireNotNull(sessionId) { "session id is not set in the Tmdb3 instance" }

            it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
            it.parameter(TmdbUrlParameter.SESSION_ID, sessionId)
        }
    }
}
