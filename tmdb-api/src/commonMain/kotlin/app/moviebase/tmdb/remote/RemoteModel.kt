package app.moviebase.tmdb.remote

import app.moviebase.tmdb.model.TmdbMediaListItem
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbShow
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal fun buildHttpClient(logLevel: TmdbLogLevel = TmdbLogLevel.NONE, interceptor: RequestInterceptor): HttpClient {
    val json = buildJson()

    val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = logLevel.ktorLogLevel
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

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
