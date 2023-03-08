package app.moviebase.tmdb.remote

import app.moviebase.tmdb.TmdbWebConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.decodeURLPart
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import java.io.File

/**
 * See https://ktor.io/docs/http-client-testing.html#usage
 */
fun mockHttpClient(
    version: Int,
    responses: Map<String, String>
) = HttpClient(MockEngine) {
    val jsonFiles = mutableMapOf<String, String>()
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = TmdbWebConfig.TMDB_HOST
            url("$version/")
        }
    }
    responses.entries.forEach {
        jsonFiles["https://api.themoviedb.org/$version/${it.key}"] = it.value
    }
    val headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    install(ContentNegotiation) {
        val json = JsonFactory.buildJson()
        json(json)
    }

    engine {
        addHandler { request ->
            val url = request.url.toString().decodeURLPart()

            val fileName = jsonFiles[url] ?: error("Unhandled url $url")
            val resource = File("./src/jvmTest/resources/tmdb$version/$fileName")

            val content = resource.readText()
            respond(content = content, headers = headers)
        }
    }
}
