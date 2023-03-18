package app.moviebase.tmdb.core

import app.moviebase.tmdb.TmdbClientConfig
import app.moviebase.tmdb.TmdbVersion
import app.moviebase.tmdb.TmdbWebConfig
import app.moviebase.tmdb.model.TmdbErrorResponse
import io.ktor.client.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.statement.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

internal object HttpClientFactory {

    fun buildHttpClient(
        version: TmdbVersion,
        config: TmdbClientConfig,
        useAuthentication: Boolean = false
    ): HttpClient {
        val defaultConfig: HttpClientConfig<*>.() -> Unit = {
            val json = JsonFactory.buildJson()

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = TmdbWebConfig.TMDB_HOST
                    path(version.path + "/")
                }
            }

            install(ContentNegotiation) {
                json(json)
            }

            // see https://ktor.io/docs/auth.html
            if (useAuthentication) {
                install(Auth) {
                    bearer {
                        // TMDB doesn't have a refresh token
                        loadTokens {
                            config.tmdbAuthCredentials?.accessTokenProvider?.invoke()?.let {
                                BearerTokens(it, "")
                            }
                        }

                        sendWithoutRequest { request ->
                            request.url.host == TmdbWebConfig.TMDB_HOST
                        }
                    }
                }
            }

            // see https://ktor.io/docs/response-validation.html
            expectSuccess = config.expectSuccess
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    val tmdbErrorResponse = json.decodeTmdbErrorResponse(exceptionResponse) ?: return@handleResponseExceptionWithRequest
                    throw TmdbException(tmdbErrorResponse, exception)
                }
            }

            // see https://ktor.io/docs/client-retry.html
            config.maxRetriesOnException?.let {
                install(HttpRequestRetry) {
                    exponentialDelay()
                    retryOnServerErrors(maxRetries = it)

                    retryOnExceptionIf(maxRetries = it) { _, cause ->
                        when {
                            cause.isTimeoutException() -> false
                            cause is CancellationException -> false
                            cause is TmdbException -> false
                            else -> true
                        }
                    }
                }
            }

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

    private suspend fun Json.decodeTmdbErrorResponse(response: HttpResponse): TmdbErrorResponse? {
        if (!response.isTmdbStatusHandled) return null
        val exceptionResponseText = response.bodyAsText()

        return try {
            decodeFromString(TmdbErrorResponse.serializer(), exceptionResponseText)
        } catch (t: Throwable) {
            // if we don't get a TMDB error response, skip the handling
            null
        }
    }

    private val HttpResponse.isTmdbStatusHandled: Boolean
        get() = status == HttpStatusCode.NotFound ||
            status == HttpStatusCode.Unauthorized ||
            status == HttpStatusCode.InternalServerError

    private fun Throwable.isTimeoutException(): Boolean {
        val exception = unwrapCancellationException()
        return exception is HttpRequestTimeoutException ||
            exception is ConnectTimeoutException ||
            exception is SocketTimeoutException
    }
}
