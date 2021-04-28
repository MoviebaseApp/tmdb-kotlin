package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

// this is an example how the tests looks like
class TmdbAccountApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "account?session_id=sessionId" to "tmdb3/movie/movie_details_10140.json"
        )
    )

    val classToTest = TmdbAccountApi(client)

    @Test
    fun `it can get account details`() = runBlocking {
//        val accountDetails = classToTest.getDetails("sessionId")

    }


}

