package app.moviebase.tmdb

internal object TmdbWebConfig {
    const val TMDB_HOST = "api.themoviedb.org"

    const val BASE_URL_TMDB = "https://api.themoviedb.org"
    const val BASE_WEBSITE_URL = "https://www.themoviedb.org"

    const val BASE_URL_TMDB_IMAGE = "https://image.tmdb.org/t/p/"
    const val BASE_URL_YOUTUBE_IMAGE = "https://img.youtube.com/vi"

    const val VERSION_PATH_V3 = "3"
    const val VERSION_PATH_V4 = "4"
    const val LOGO_FILTER = "_filter(negate,000,666)"
}

internal object TmdbUrlParameter {
    const val API_KEY = "api_key"
    const val SESSION_ID = "session_id"
    const val GUEST_SESSION_ID = "guest_session_id"
    const val ACCESS_TOKEN = "access_token"
}

internal enum class TmdbVersion(val path: String) {
    V3(TmdbWebConfig.VERSION_PATH_V3),
    V4(TmdbWebConfig.VERSION_PATH_V4)
}
