package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


class Tmdb4ListApiTest {

    val client = mockHttpClient(
        4,
        "list/82963?page=1" to "list.json"
    )

    val classToTest = Tmdb4ListApi(client)


    @Test
    fun `it can fetch movie details`() = runBlocking {
        val list = classToTest.getList(
            listId = 82963,
            page = 1
        )

        assertThat(list.id).isEqualTo(82963)
    }

}
