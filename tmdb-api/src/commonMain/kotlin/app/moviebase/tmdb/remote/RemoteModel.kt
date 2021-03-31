package app.moviebase.tmdb.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json

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
}
