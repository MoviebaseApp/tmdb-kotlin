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
            val discover = TmdbDiscover.Movie(withWatchProviders = listOf(8, 9, 350))

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.WITH_WATCH_PROVIDERS]).isEqualTo("8|9|350")
        }

        @Test
        fun `it has watch providers separated by and`() {
            val discover = TmdbDiscover.Movie(withWatchProviders = listOf(8, 9, 350), withWatchProvidersType = TmdbDiscoverSeparator.AND)

            val parameters = discover.buildParameters()

            assertThat(parameters[DiscoverParam.WITH_WATCH_PROVIDERS]).isEqualTo("8,9,350")
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
    }
}
