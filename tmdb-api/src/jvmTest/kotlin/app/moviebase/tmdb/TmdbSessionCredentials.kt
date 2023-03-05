package app.moviebase.tmdb

data class TmdbSessionCredentials(
    val userName: String?,
    val password: String?,
    val approvedRequestToken: String?,
    val sessionId: String?,
    val approvedRequestTokenVersion4: String?,
    val accessTokenVersion4: String?,
    val accountId4: String?,
)
