package app.moviebase.tmdb.api

import app.moviebase.tmdb.core.mockHttpClient
import app.moviebase.tmdb.model.AppendResponse
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class TmdbSeasonApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "tv/63333/season/1?append_to_response=release_dates,images,credits,tv_credits,external_ids&include_image_language="
                to "tv/tv_season_63333_season_1.json",
            "tv/19849/season/1?append_to_response=credits,tv_credits"
                to "tv/tv_season_19849_season_1.json",
        ),
    )
    val classToTest = TmdbShowSeasonsApi(client)

    @Test
    fun `it can fetch season with crew`() = runTest {
        val seasonDetails = classToTest.getDetails(
            19849,
            1,
            null,
            listOf(
                AppendResponse.CREDITS,
                AppendResponse.TV_CREDITS,
            ),
        )

        assertNotNull(seasonDetails.episodes)
    }

    @Test
    fun `it can fetch season details with images`() = runTest {
        val seasonDetails = classToTest.getDetails(
            63333,
            1,
            null,
            listOf(
                AppendResponse.RELEASES_DATES,
                AppendResponse.IMAGES,
                AppendResponse.CREDITS,
                AppendResponse.TV_CREDITS,
                AppendResponse.EXTERNAL_IDS,
            ),
            "",
        )

        assertEquals(68878, seasonDetails.id)
        assertNotNull(seasonDetails.images)

        val imageFile = seasonDetails.images?.posters?.firstOrNull()
        assertEquals("/oQasSKPcBLcEG5rOUg3s1Ozpr4s.jpg", imageFile?.filePath)
    }
}
