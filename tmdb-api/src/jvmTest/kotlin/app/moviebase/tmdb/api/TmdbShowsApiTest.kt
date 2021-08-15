package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbVideoType
import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


class TmdbShowsApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "tv/96677?language=en-US&append_to_response=external_ids,videos,credits,aggregate_credits,reviews,content_ratings,watch/providers"
                    to "tv/tv_details_96677.json",
            "tv/96677/aggregate_credits?language=en-US" to "tv/tv_aggregate_credits_96677.json",
        )
    )

    val classToTest = TmdbShowApi(client)

    @Test
    fun `it can fetch show details`() = runBlocking {
        val showDetails = classToTest.getDetails(
            showId = 96677,
            language = "en-US",
            appendResponses = listOf(
                AppendResponse.EXTERNAL_IDS,
                AppendResponse.VIDEOS,
                AppendResponse.CREDITS,
                AppendResponse.AGGREGATE_CREDITS,
                AppendResponse.REVIEWS,
                AppendResponse.CONTENT_RATING,
                AppendResponse.WATCH_PROVIDERS,
            )
        )

        assertThat(showDetails.id).isEqualTo(96677)
        assertThat(showDetails.videos).isNotNull()
        assertThat(showDetails.popularity).isEqualTo(183.04f)
        assertThat(showDetails.voteAverage).isEqualTo(7.9f)
        assertThat(showDetails.voteCount).isEqualTo(742)
        assertThat(showDetails.homepage).isEqualTo("https://www.netflix.com/title/80994082")
        assertThat(showDetails.overview).isEqualTo("Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.")

        val tmdbVideo = showDetails.videos?.results?.first()
        assertThat(tmdbVideo).isNotNull()
        assertThat(tmdbVideo?.id).isEqualTo("5f6dbe399f37b000365d22ad")
        assertThat(tmdbVideo?.type).isEqualTo(TmdbVideoType.TEASER)
    }

    @Test
    fun `it can fetch aggregate credits`() = runBlocking {
        val aggregateCredits = classToTest.getAggregateCredits(
            showId = 96677,
            language = "en-US",
        )

        assertThat(aggregateCredits.cast).isNotEmpty()
        assertThat(aggregateCredits.crew).isNotEmpty()
    }

}
