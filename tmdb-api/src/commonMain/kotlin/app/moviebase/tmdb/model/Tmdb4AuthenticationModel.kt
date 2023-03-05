package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tmdb4RedirectToBodyAuth(
    @SerialName("redirect_to") val redirectTo: String
)

@Serializable
data class Tmdb4RequestTokenBody(
    @SerialName("request_token") val requestToken: String
)

@Serializable
data class Tmdb4DeleteAccessTokenBody(
    @SerialName("access_token") val accessToken: String
)

@Serializable
data class Tmdb4RequestToken(
    @SerialName("status_message") val statusMessage: String,
    @SerialName("request_token") val requestToken: String,
    @SerialName("success") val success: Boolean,
    @SerialName("status_code") val statusCode: Int
)

@Serializable
data class Tmdb4AccessToken(
    @SerialName("status_message") val statusMessage: String,
    @SerialName("access_token") val accessToken: String,
    @SerialName("success") val success: Boolean,
    @SerialName("status_code") val statusCode: Int,
    @SerialName("account_id") val accountId: String,
)
