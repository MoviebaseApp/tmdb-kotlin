package app.moviebase.tmdb.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TmdbErrorResponse(
    @SerialName("success")  val success: Boolean = false,
    @SerialName("status_code") val statusCode: Int,
    @SerialName("status_message") val statusMessage: String,
    @SerialName("error_message") val errorMessage: String? = null
)

/**
 * TMDB returns some errors like no resources, invalid API key, no token has been granted.
 * @see [Documentation] (https://developers.themoviedb.org/4/account/get-account-rated-movies)
 */
object TmdbStatusCode {
    const val SUCCESS_ADDED = 1
    const val SUCCESS_UPDATED = 12
    const val SUCCESS_DELETED = 13

    const val INVALID_API_KEY = 7
    const val RESOURCE_NOT_FOUND = 34
    const val TOKEN_NOT_GRANTED = 36
}
