package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


class TmdbMoviesApiTest {

    val client = mockHttpClient(
        3,
        "movie/10140?language=en-US&append_to_response=release_dates,watch/providers" to "movie_details.json"
    )

    val classToTest = TmdbMoviesApi(client)


    @Test
    fun `it can fetch movie details`() = runBlocking {
        val movieDetails = classToTest.getDetails(
            movieId = 10140,
            language = "en-US",
            appendResponses = listOf(AppendResponse.RELEASES_DATES, AppendResponse.WATCH_PROVIDERS)
        )

        assertThat(movieDetails.id).isEqualTo(10140)
    }

}
