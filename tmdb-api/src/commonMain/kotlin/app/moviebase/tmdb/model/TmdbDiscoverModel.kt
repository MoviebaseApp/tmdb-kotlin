package app.moviebase.tmdb.model

enum class TmdbDiscoverSortBy(val value: String) {
    CREATED_AT("created_at"),
    RELEASE_DATE("release_date"),
    TITLE("title"),
    VOTE_AVERAGE("vote_average");
}
