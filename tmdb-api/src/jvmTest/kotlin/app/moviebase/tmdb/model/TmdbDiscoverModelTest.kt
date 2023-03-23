package app.moviebase.tmdb.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TmdbDiscoverModelTest {

    @Nested
    inner class `building TMDb movie discover class` {

        @Test
        fun `it has watch regions`() {
            val discover = TmdbDiscover.Movie(watchRegion = "US")

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.WATCH_REGION]).isEqualTo("US")
        }

        @Test
        fun `it has watch providers separated by or`() {
            val discover = TmdbDiscover.Movie(
                withWatchProviders = TmdbDiscoverFilter(TmdbDiscoverSeparator.OR, listOf(8, 9, 350)),
            )

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.WITH_WATCH_PROVIDERS]).isEqualTo("8|9|350")
        }

        @Test
        fun `it has watch providers separated by and`() {
            val discover = TmdbDiscover.Movie(
                withWatchProviders = TmdbDiscoverFilter(TmdbDiscoverSeparator.AND, listOf(8, 9, 350)),
            )

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.WITH_WATCH_PROVIDERS]).isEqualTo("8,9,350")
        }

        @Test
        fun `it has status separated by or`() {
            val discover = TmdbDiscover.Movie(
                withReleaseTypes = TmdbDiscoverFilter(
                    TmdbDiscoverSeparator.OR,
                    setOf(TmdbReleaseType.DIGITAL, TmdbReleaseType.PHYSICAL),
                ),

            )

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.Movie.WITH_RELEASE_TYPE]).isEqualTo("4|5")
        }
    }

    @Nested
    inner class `building TMDb show discover class` {
        @Test
        fun `it can add watch regions`() {
            val discover = TmdbDiscover.Show(watchRegion = "DE")

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.WATCH_REGION]).isEqualTo("DE")
        }

        @Test
        fun `it has status separated by or`() {
            val discover = TmdbDiscover.Show(
                withStatus = TmdbDiscoverFilter(
                    TmdbDiscoverSeparator.OR,
                    setOf(TmdbShowStatus.RETURNING_SERIES, TmdbShowStatus.PLANNED),
                ),
            )

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.Show.WITH_STATUS]).isEqualTo("0|1")
        }
    }
}
