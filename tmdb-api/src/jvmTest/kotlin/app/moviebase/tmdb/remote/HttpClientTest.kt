package app.moviebase.tmdb.remote

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.io.File

/**
 * See https://ktor.io/docs/http-client-testing.html#usage
 */
fun mockHttpClient(
    version: Int,
    responses: Map<String, String>
) = HttpClient(MockEngine) {
    val jsonFiles = mutableMapOf<String, String>()
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
