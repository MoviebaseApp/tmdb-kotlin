package app.moviebase.tmdb.remote

import kotlinx.datetime.*

fun String.tryLocalDate(): LocalDate? = try {
    if (isBlank()) null else toLocalDate()
} catch (t: Throwable) {
    null
}

fun String.tryLocalDateTime(): LocalDateTime? = try {
    if (isBlank()) null else toInstant().toLocalDateTime(TimeZone.UTC)
} catch (t: Throwable) {
    null
}
