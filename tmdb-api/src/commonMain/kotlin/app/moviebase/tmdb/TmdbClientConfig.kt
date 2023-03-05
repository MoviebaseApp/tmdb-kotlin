package app.moviebase.tmdb

import app.moviebase.tmdb.remote.TmdbDsl
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.logging.*

@TmdbDsl
class TmdbClientConfig {

    var tmdbApiKey: String? = null
    var tmdbAuthenticationToken: String? = null
    internal var tmdbCredentials: TmdbCredentials? = null

    var expectSuccess: Boolean = true
    var useCache: Boolean = false
    var useTimeout: Boolean = false

    internal var httpClientConfigBlock: (HttpClientConfig<*>.() -> Unit)? = null
    internal var httpClientBuilder: (() -> HttpClient)? = null
    internal var httpClientLoggingBlock: (Logging.Config.() -> Unit)? = null

    fun tmdbAccountCredentials(block: TmdbCredentials.() -> Unit) {
        tmdbCredentials = TmdbCredentials().apply(block)
    }

    fun logging(block: Logging.Config.() -> Unit) {
        httpClientLoggingBlock = block
    }

    /**
     * Set custom HttpClient configuration for the default HttpClient.
     */
    fun httpClient(block: HttpClientConfig<*>.() -> Unit) {
        this.httpClientConfigBlock = block
    }

    /**
     * Creates an custom [HttpClient] with the specified [HttpClientEngineFactory] and optional [block] configuration.
     * Note that the TMDB config will be added afterwards.
     */
    fun <T : HttpClientEngineConfig> httpClient(
        engineFactory: HttpClientEngineFactory<T>,
        block: HttpClientConfig<T>.() -> Unit = {}
    ) {
        httpClientBuilder = {
            HttpClient(engineFactory, block)
        }
    }

    companion object {

        internal fun buildDefault(
            tmdbApiKey: String,
            tmdbAuthenticationToken: String? = null
        ) = TmdbClientConfig().apply {
            this.tmdbApiKey = tmdbApiKey
            this.tmdbAuthenticationToken = tmdbAuthenticationToken
        }
    }
}

@TmdbDsl
class TmdbCredentials {

    internal var sessionIdProvider: (() -> String?)? = null
    internal var accessTokenProvider: (() -> String?)? = null
    internal var requestTokenProvider: (() -> String?)? = null

    fun sessionId(provider: () -> String?) {
        sessionIdProvider = provider
    }

    fun accessToken(provider: () -> String?) {
        accessTokenProvider = provider
    }

    fun requestToken(provider: () -> String?) {
        requestTokenProvider = provider
    }
}
