package app.moviebase.tmdb.model

import app.moviebase.tmdb.remote.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbRequestToken(
    val success: Boolean,
    @SerialName("expires_at") @Serializable(LocalDateSerializer::class) val expiredAt: LocalDate?,
    @SerialName("request_token") val requestToken: String
)

@Serializable
data class TmdbSession(
    val success: Boolean,
    @SerialName("session_id") val sessionId: String
)
