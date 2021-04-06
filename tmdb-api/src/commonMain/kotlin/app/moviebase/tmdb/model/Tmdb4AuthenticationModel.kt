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
