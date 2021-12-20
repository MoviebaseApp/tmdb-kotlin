package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


class TmdbMoviesApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "movie/10140?language=en-US&append_to_response=external_ids,videos,release_dates,credits,reviews,content_ratings,watch/providers"
                    to "movie/movie_details_10140.json",
            "movie/607?language=en-US&append_to_response=external_ids,videos,release_dates,credits,reviews,content_ratings,watch/providers"
                    to "movie/movie_details_607.json"
        )
    )

    val classToTest = TmdbMoviesApi(client)


    @Test
    fun `it can fetch movie details`() = runBlocking {
        val movieDetails = classToTest.getDetails(
            movieId = 10140,
            language = "en-US",
            appendResponses = listOf(
                AppendResponse.EXTERNAL_IDS,
                AppendResponse.VIDEOS,
                AppendResponse.RELEASES_DATES,
                AppendResponse.CREDITS,
                AppendResponse.REVIEWS,
                AppendResponse.CONTENT_RATING,
                AppendResponse.WATCH_PROVIDERS,
            )
        )

        assertThat(movieDetails.id).isEqualTo(10140)
        assertThat(movieDetails.videos).isNotNull()
        assertThat(movieDetails.popularity).isEqualTo(48.581f)
        assertThat(movieDetails.voteAverage).isEqualTo(6.4f)
        assertThat(movieDetails.voteCount).isEqualTo(4534)
        assertThat(movieDetails.overview).isEqualTo("This time around Edmund and Lucy Pevensie, along with their pesky cousin Eustace Scrubb find themselves swallowed into a painting and on to a fantastic Narnian ship headed for the very edges of the world.")

        val tmdbVideo = movieDetails.videos?.results?.first()
        assertThat(tmdbVideo).isNotNull()
        assertThat(tmdbVideo?.id).isEqualTo("54b36eb9c3a3680939006425")
        assertThat(tmdbVideo?.type).isEqualTo(TmdbVideoType.TRAILER)


    }

    @Test
    fun `it can get right certifications`() = runBlocking {
        val movieDetails = classToTest.getDetails(
            movieId = 607,
            language = "en-US",
            appendResponses = listOf(
                AppendResponse.EXTERNAL_IDS,
                AppendResponse.VIDEOS,
                AppendResponse.RELEASES_DATES,
                AppendResponse.CREDITS,
                AppendResponse.REVIEWS,
                AppendResponse.CONTENT_RATING,
                AppendResponse.WATCH_PROVIDERS,
            )
        )

        val certification = movieDetails.releaseDates?.getCertification("FR")
        val releaseDate = movieDetails.releaseDates?.getReleaseDateBy("FR")
        val releaseDates = movieDetails.releaseDates?.getReleaseDatesBy("FR")

        assertThat(certification).isEqualTo("U")
        assertThat(releaseDates).hasSize(5)
        assertThat(releaseDate?.type).isEqualTo(TmdbReleaseType.THEATRICAL)
    }

}
