package app.moviebase.tmdb.remote

import kotlinx.datetime.*

fun currentLocalDate(timeZone: TimeZone = TimeZone.UTC): LocalDate = Clock.System.todayAt(timeZone)

fun LocalDate.plusDays(days: Int) = plus(days, DateTimeUnit.DAY)
fun LocalDate.plusWeeks(weeks: Int) = plus(weeks, DateTimeUnit.WEEK)
fun LocalDate.minusWeeks(weeks: Int) = minus(weeks, DateTimeUnit.WEEK)

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
