package app.moviebase.tmdb

import app.moviebase.tmdb.api.Tmdb4AccountApi
import app.moviebase.tmdb.api.Tmdb4AuthenticationApi
import app.moviebase.tmdb.api.Tmdb4ListApi
import app.moviebase.tmdb.remote.HttpClientFactory
import app.moviebase.tmdb.remote.TmdbDsl
import app.moviebase.tmdb.remote.interceptRequest
import io.ktor.client.*
import io.ktor.client.request.*

@TmdbDsl
fun Tmdb4(block: TmdbClientConfig.() -> Unit): Tmdb4 {
    val config = TmdbClientConfig().apply(block)
    return Tmdb4(config)
}

class Tmdb4 internal constructor(
    private val config: TmdbClientConfig
) {

    constructor(
        tmdbApiKey: String,
        authenticationToken: String? = null
    ) : this(TmdbClientConfig.buildDefault(tmdbApiKey, authenticationToken))

    init {
        check(!config.tmdbApiKey.isNullOrBlank()) {
            "TMDB API key is unavailable. Set the tmdbApiKey when instantiate the TMDB client."
        }
    }

    private val client by lazy {
        HttpClientFactory.buildHttpClient(TmdbVersion.V4, config).apply {
            interceptRequest {
                it.parameter(TmdbUrlParameter.API_KEY, config.tmdbApiKey)

                config.tmdbCredentials?.accessTokenProvider?.let { token ->
                    it.header("Authorization", "Bearer $token")
                }
            }
        }
    }

    private val authClient by lazy {
        HttpClientFactory.buildHttpClient(TmdbVersion.V4, config).apply {
            interceptRequest {
                it.parameter(TmdbUrlParameter.API_KEY, config.tmdbApiKey)

                config.tmdbAuthenticationToken?.let { token ->
                    it.header("Authorization", "Bearer $token")
                }
            }
        }
    }

    val account by buildApi(::Tmdb4AccountApi)
    val auth by lazy { Tmdb4AuthenticationApi(authClient) }
    val list by buildApi(::Tmdb4ListApi)

    private inline fun <T> buildApi(crossinline builder: (HttpClient) -> T) = lazy { builder(client) }
}
