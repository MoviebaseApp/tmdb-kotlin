// ktlint-disable filename

package app.moviebase.tmdb.model

enum class TmdbListSortBy(val value: String) {
    ORIGINAL_TITLE("original_title"),
    VOTE_AVERAGE("vote_average"),
    PRIMARY_RELEASE_DATE("primary_release_date"),
    TITLE("title"),
    POPULARITY("popularity"),
    RELEASE_DATE(" release_date"),
    REVENUE("revenue"),
    VOTE_COUNT("vote_count")
}
