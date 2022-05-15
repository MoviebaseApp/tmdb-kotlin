package app.moviebase.tmdb.remote

import app.moviebase.tmdb.model.TmdbMediaListItem
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbShow
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal fun buildHttpClient(logLevel: TmdbLogLevel = TmdbLogLevel.NONE, interceptor: RequestInterceptor): HttpClient {
    val json = buildJson()

    val httpClient = HttpClient {
        Logging {
            logger = Logger.DEFAULT
            level = logLevel.ktorLogLevel
        }

        install(ContentNegotiation) {
            json(json)
        }

        // TODO: handle validation https://ktor.io/docs/response-validation.html

        install(HttpCache)

        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 60_000
        }

    }
    httpClient.interceptRequest(interceptor = interceptor)
    return httpClient
}

private val TmdbLogLevel.ktorLogLevel
    get() = when (this) {
        TmdbLogLevel.ALL -> LogLevel.ALL
        TmdbLogLevel.HEADERS -> LogLevel.HEADERS
        TmdbLogLevel.BODY -> LogLevel.BODY
        TmdbLogLevel.INFO -> LogLevel.INFO
        TmdbLogLevel.NONE -> LogLevel.NONE
    }

internal fun buildJson(): Json = Json {
    encodeDefaults = false
    ignoreUnknownKeys = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    prettyPrint = false

    val module = SerializersModule {
        polymorphic(TmdbMediaListItem::class, TmdbShow::class, TmdbShow.serializer())
        polymorphic(TmdbMediaListItem::class, TmdbMovie::class, TmdbMovie.serializer())
    }
    serializersModule = module
    classDiscriminator = "media_type"
}
