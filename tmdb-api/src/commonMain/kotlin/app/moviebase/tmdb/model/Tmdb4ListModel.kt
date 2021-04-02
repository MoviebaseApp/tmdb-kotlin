package app.moviebase.tmdb.model

enum class TmdbListSortBy(val value: String) {
    POPULARITY("popularity"),
    RELEASE_DATE(" release_date"),
    REVENUE("revenue"),
    PRIMARY_RELEASE_DATE("primary_release_date"),
    ORIGINAL_TITLE("original_title"),
    VOTE_AVERAGE("vote_average"),
    VOTE_COUNT("vote_count"),
}
