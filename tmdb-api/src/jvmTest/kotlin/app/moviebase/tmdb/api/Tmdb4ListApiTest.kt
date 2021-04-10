package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


class Tmdb4ListApiTest {

    val client = mockHttpClient(
        version = 4,
        responses = mapOf(
            "list/82963?page=1" to "list_82963.json",
            "list/932?page=1" to "list_932.json",
            "list/3321?page=1" to "list_3321.json",
        )
    )

    val classToTest = Tmdb4ListApi(client)


    @Test
    fun `it can fetch list page 1`() = runBlocking {
        val list = classToTest.getList(listId = 82963, page = 1)

        assertThat(list.id).isEqualTo(82963)
        assertThat(list.page).isEqualTo(1)
    }

    @Test
    fun `it can fetch list with empty backdrop`() = runBlocking {
        val list = classToTest.getList(listId = 932, page = 1)

        assertThat(list.id).isEqualTo(932)
        assertThat(list.backdropPath).isNull()
    }

    @Test
    fun `it can fetch list with available content`() = runBlocking {
        val list = classToTest.getList(listId = 3321, page = 1)

        assertThat(list.id).isEqualTo(3321)
        assertThat(list.page).isEqualTo(1)
        assertThat(list.totalPages).isEqualTo(4)
        assertThat(list.public).isTrue()
        assertThat(list.results).isNotEmpty()
        assertThat(list.posterPath).isEqualTo("/sSislgDL6SrfkE0upQTqJ6hUcVd.jpg")
        assertThat(list.name).isEqualTo("Anime Movies")
        assertThat(list.createdBy.name).isEqualTo("Star")
    }

}
