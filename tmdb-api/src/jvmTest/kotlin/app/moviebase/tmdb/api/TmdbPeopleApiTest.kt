@file:Suppress("ktlint")

package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.core.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class TmdbPeopleApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "person/287?language=en-US&append_to_response=external_ids,images,tagged_images,movie_credits,tv_credits"
                to "person/person_details_287.json",
            "person/19292/movie_credits?language=en-US"
                to "person/person_movie_credits_19292.json",
            "person/19292/tv_credits?language=en-US"
                to "person/person_tv_credits_19292.json"
        )
    )

    val classToTest = TmdbPeopleApi(client)

    @Test
    fun `it can fetch person details`() = runTest {
        val personDetail = classToTest.getDetails(
            personId = 287,
            language = "en-US",
            appendResponses = listOf(
                AppendResponse.EXTERNAL_IDS,
                AppendResponse.IMAGES,
                AppendResponse.TAGGED_IMAGES,
                AppendResponse.MOVIE_CREDITS,
                AppendResponse.TV_CREDITS
            )
        )

        assertThat(personDetail.id).isEqualTo(287)
        assertThat(personDetail.externalIds).isNotNull()
        assertThat(personDetail.movieCredits).isNotNull()
        assertThat(personDetail.tvCredits).isNotNull()
    }

    @Test
    fun `it can fetch show credits`() = runTest {
        val showCredits = classToTest.getShowCredits(
            personId = 19292,
            language = "en-US"
        )

        assertThat(showCredits.cast).isNotNull()
        assertThat(showCredits.crew).isNotNull()
    }

    @Test
    fun `it can fetch movie credits`() = runTest {
        val showCredits = classToTest.getMovieCredits(
            personId = 19292,
            language = "en-US"
        )

        assertThat(showCredits.cast).isNotNull()
        assertThat(showCredits.crew).isNotNull()
    }
}
