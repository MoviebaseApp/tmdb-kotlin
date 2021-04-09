package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

// this is an example how the tests looks like
class TmdbAccountApiTest {

    val client = mockHttpClient(
        3,
        "account?session_id=sessionId" to "movie_details.json"
    )

    val classToTest = TmdbAccountApi(client)

    @Test
    fun testGetAccountDetails() = runBlock {
//        val accountDetails = classToTest.getDetails("sessionId")

    }

}

