package app.moviebase.tmdb.remote

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*

fun mockHttpClient(
    version: Int,
    vararg responses: Pair<String, String>
) = HttpClient(MockEngine) {
    val jsonFiles = mutableMapOf<String, String>()
    responses.forEach {
        jsonFiles["https://api.themoviedb.org/$version/${it.first}"] = it.second
    }
    val headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    install(JsonFeature) {
        serializer = KotlinxSerializer(buildJson())
    }

    engine {
        addHandler { request ->
            val url = request.url.toString().decodeURLPart()

            val fileName = jsonFiles[url] ?: error("Unhandled url $url")
            val resource = Resource("./src/commonTest/resources/$fileName")

            val content = resource.readText()
            respond(content = content, headers = headers)
        }
    }
}
