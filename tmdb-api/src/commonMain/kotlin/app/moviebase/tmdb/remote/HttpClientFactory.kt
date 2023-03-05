package app.moviebase.tmdb.remote

import app.moviebase.tmdb.TmdbClientConfig
import app.moviebase.tmdb.TmdbVersion
import app.moviebase.tmdb.TmdbWebConfig
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

internal object HttpClientFactory {

    fun buildHttpClient(
        version: TmdbVersion,
        config: TmdbClientConfig
    ): HttpClient {
        val defaultConfig: HttpClientConfig<*>.() -> Unit = {
            val json = JsonFactory.buildJson()

            defaultRequest {
                url {
                    host = TmdbWebConfig.BASE_URL_TMDB
                    path(version.path)
                }
            }

            install(ContentNegotiation) {
                json(json)
            }

            // TODO: Install new Auth plugin  for TMDB account
            // see https://ktor.io/docs/auth.html

            // see https://ktor.io/docs/response-validation.html
            expectSuccess = config.expectSuccess

            // see https://ktor.io/docs/client-caching.html
            if (config.useCache) {
                // TODO: Set persistent cache
                install(HttpCache) {

                }
            }

            if (config.useTimeout) {
                install(HttpTimeout) {
                    requestTimeoutMillis = 60_000
                    connectTimeoutMillis = 60_000
                    socketTimeoutMillis = 60_000
                }
            }

            config.httpClientLoggingBlock?.let {
                Logging(it)
            }

            config.httpClientConfigBlock?.invoke(this)
        }

        return config.httpClientBuilder?.invoke()?.config(defaultConfig) ?: HttpClient(defaultConfig)
    }
}
