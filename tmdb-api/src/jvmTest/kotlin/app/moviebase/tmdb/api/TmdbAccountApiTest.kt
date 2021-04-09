package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import io.ktor.client.*

// this is an example how the tests looks like
class TmdbAccountApiTest {

    val client = mockHttpClient(
        3,
        "account?session_id=sessionId" to "movie_details.json"
    )

    val classToTest = TmdbAccountApi(client)

    @Test
    fun `it can get account details`() = runBlocking {

//        val accountDetails = classToTest.getDetails("sessionId")

    }


}

