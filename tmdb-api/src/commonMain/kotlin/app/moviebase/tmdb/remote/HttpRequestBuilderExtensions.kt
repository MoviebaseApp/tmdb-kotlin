package app.moviebase.tmdb.remote

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.TmdbWebConfig
import io.ktor.client.request.*
import io.ktor.http.*

fun HttpRequestBuilder.json() {
    contentType(ContentType.Application.Json)
}

fun HttpRequestBuilder.endPointV3(vararg paths: String) {
    url {
        takeFrom(TmdbWebConfig.BASE_URL_TMDB)
        path(TmdbWebConfig.VERSION_PATH_V3, *paths)
    }
}

fun HttpRequestBuilder.endPointV4(vararg paths: String) {
    url {
        takeFrom(TmdbWebConfig.BASE_URL_TMDB)
        path(TmdbWebConfig.VERSION_PATH_V4, *paths)
    }
}

fun HttpRequestBuilder.parameters(parameters: Map<String, Any?>) {
    parameters.entries.forEach {
        parameter(it.key, it.value)
    }
}

fun HttpRequestBuilder.parameterLanguage(language: String?) {
    language?.let { parameter("language", it) }
}

fun HttpRequestBuilder.parameterRegion(region: String?) {
    region?.let { parameter("region", it) }
}

fun HttpRequestBuilder.parameterPage(page: Int) {
    require(page > 0) { "invalid page size: $page" }
    parameter("page", page)
}

fun HttpRequestBuilder.parameterFirstAirDateYear(year: Int?) {
    year?.let { parameter("first_air_date_year", it) }
}

fun HttpRequestBuilder.parameterYear(year: Int?) {
    year?.let { parameter("year", it) }
}

fun HttpRequestBuilder.parameterPrimaryReleaseYear(year: Int?) {
    year?.let { parameter("primary_release_year", it) }
}

fun HttpRequestBuilder.parameterAppendResponses(appendResponses: Iterable<AppendResponse>?) {
    appendResponses?.let { parameter("append_to_response", AppendResponse.build(it)) }
}
