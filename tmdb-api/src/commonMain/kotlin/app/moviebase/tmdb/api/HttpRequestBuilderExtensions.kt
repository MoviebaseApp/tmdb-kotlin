package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.TmdbWebConfig
import io.ktor.client.request.*
import io.ktor.http.*

fun HttpRequestBuilder.json() {
    contentType(ContentType.Application.Json)
}

fun HttpRequestBuilder.endPoint(vararg paths: String) {
    url {
        takeFrom(TmdbWebConfig.BASE_URL)
        path(TmdbWebConfig.VERSION_PATH_V3, *paths)
    }
}

fun HttpRequestBuilder.parameters(parameters: Map<String, Any?>) {
    parameters.entries.forEach {
        parameter(it.key, it.value)
    }
}

fun HttpRequestBuilder.parameterLanguage(language: String) {
    parameter("language", language)
}

fun HttpRequestBuilder.parameterAppendResponses(appendResponses: Iterable<AppendResponse>) {
    parameter("append_to_response", AppendResponse.build(appendResponses))
}
