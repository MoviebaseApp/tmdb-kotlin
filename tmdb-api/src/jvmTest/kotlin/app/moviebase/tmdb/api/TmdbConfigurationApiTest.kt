package app.moviebase.tmdb.api

import app.moviebase.tmdb.core.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class TmdbConfigurationApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "configuration" to "configuration/configuration.json",
            "configuration/countries" to "configuration/countries.json"
        )
    )

    val api = TmdbConfigurationApi(client)

    @Test
    fun `it can fetch api configuration`() = runTest {
        val config = api.getApiConfiguration()

        assertThat(config.changeKeys).isNotEmpty()

        assertThat(config.images.baseUrl).isNotEmpty()
        assertThat(config.images.secureBaseUrl).isNotEmpty()
        assertThat(config.images.backdropSizes).isNotEmpty()
        assertThat(config.images.posterSizes).isNotEmpty()
        assertThat(config.images.logoSizes).isNotEmpty()
        assertThat(config.images.profileSizes).isNotEmpty()
        assertThat(config.images.stillSizes).isNotEmpty()
    }

    @Test
    fun `it can fetch configuration countries`() = runTest {
        assertThat(api.getCountries()).isNotEmpty()
    }

}
