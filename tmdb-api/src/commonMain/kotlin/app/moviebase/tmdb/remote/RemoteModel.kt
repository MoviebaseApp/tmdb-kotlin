package app.moviebase.tmdb.remote

import app.moviebase.tmdb.model.TmdbMediaListItem
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbShow
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

fun buildHttpClient(interceptor: RequestInterceptor): HttpClient {
    val json = buildJson()

    val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }
    httpClient.interceptRequest(interceptor = interceptor)
    return httpClient
}

fun buildJson(): Json = Json {
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
