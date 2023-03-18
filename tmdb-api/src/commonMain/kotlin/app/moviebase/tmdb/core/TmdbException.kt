package app.moviebase.tmdb.core

import app.moviebase.tmdb.model.TmdbErrorResponse

class TmdbException(
    val tmdbResponse: TmdbErrorResponse,
    caused: Throwable? = null
) : IllegalStateException("Status code: ${tmdbResponse.statusCode}. Message: \"${tmdbResponse.statusMessage}\"", caused) {

    val statusCode get() = tmdbResponse.statusCode
}
