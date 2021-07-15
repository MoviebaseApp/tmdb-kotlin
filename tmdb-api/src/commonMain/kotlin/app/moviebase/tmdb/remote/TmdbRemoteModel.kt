package app.moviebase.tmdb.remote

fun interface TmdbSessionProvider {
    fun getId(): String?
}

enum class TmdbLogLevel {
    ALL,
    HEADERS,
    BODY,
    INFO,
    NONE,
}
