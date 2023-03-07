package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbMediaType
import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class TmdbAccountApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "account" to "account/account_details.json",
            "account/18029486/favorite" to "account/mark_favorite_76600.json",
            "account/18029486/favorite/movies" to "account/favorite_movies.json",
        )
    )

    val classToTest = TmdbAccountApi(client)

    @Test
    fun `it should return request token`() = runTest {
        val accountDetails = classToTest.getDetails()

        assertThat(accountDetails.userName).isEqualTo("someuser")
        assertThat(accountDetails.name).isEqualTo("name23")
        assertThat(accountDetails.id).isEqualTo(18029486)
        assertThat(accountDetails.language).isEqualTo("en")
        assertThat(accountDetails.region).isEqualTo("DE")
    }

    @Test
    fun `it should get favorite movies`() = runTest {
        val pageResult = classToTest.getFavorites(18029486, TmdbMediaType.MOVIE)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalResults).isEqualTo(2)
        assertThat(pageResult.results).isNotEmpty()
    }

    @Test
    fun `when adding movie to favorites it returns a success result`() = runTest {
        val result = classToTest.markFavorite(18029486, TmdbMediaType.MOVIE, 76600, true)

        assertThat(result.success).isTrue()
        assertThat(result.statusCode).isEqualTo(1)
    }
}
