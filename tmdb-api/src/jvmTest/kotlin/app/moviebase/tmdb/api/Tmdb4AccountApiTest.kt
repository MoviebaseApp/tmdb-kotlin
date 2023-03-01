package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class Tmdb4AccountApiTest {

    val client = mockHttpClient(
        version = 4,
        responses = mapOf(
            "account/343563/lists?page=1" to "account/account_lists.json"
        )
    )

    val classToTest = Tmdb4AccountApi(client)

    @Test
    fun `it can get account user lists`() = runBlocking {
        val results = classToTest.getLists("343563", 1)

    }
}
