package app.moviebase.tmdb.core

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

internal fun currentLocalDate(timeZone: TimeZone = TimeZone.UTC): LocalDate = Clock.System.todayIn(timeZone)

internal fun LocalDate.plusDays(days: Int) = plus(days, DateTimeUnit.DAY)
internal fun LocalDate.plusWeeks(weeks: Int) = plus(weeks, DateTimeUnit.WEEK)
internal fun LocalDate.minusWeeks(weeks: Int) = minus(weeks, DateTimeUnit.WEEK)

internal fun String.tryLocalDate(): LocalDate? = try {
    if (isBlank()) null else toLocalDate()
} catch (t: Throwable) {
    null
}

internal fun String.tryLocalDateTime(): LocalDateTime? = try {
    toInstant()
    if (isBlank()) null else toLocalDateTime()
} catch (t: Throwable) {
    null
}
