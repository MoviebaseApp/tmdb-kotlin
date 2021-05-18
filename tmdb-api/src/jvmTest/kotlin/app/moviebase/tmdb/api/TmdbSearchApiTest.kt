package app.moviebase.tmdb.api

import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class TmdbSearchApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "search/tv?query=Lupin&include_adult=false&page=1&region=US&language=en" to "search/search_tv_lupin.json",
            "search/tv?query=S.W.A.T.&include_adult=false&page=1&region=US&language=en" to "search/search_tv_SWAT.json",
            "search/movie?query=Star+Wars&include_adult=false&page=1&region=US&language=en" to "search/search_movie_star_wars.json",
            "search/person?query=ka&include_adult=false&page=1&region=US&language=en" to "search/search_person_ka.json",
            "search/company?query=fox&page=1" to "search/search_company_fox.json",
            "search/collection?query=fast&page=1&language=en" to "search/search_collection_fast.json",
            "search/keyword?query=future&page=1" to "search/search_keyword_future.json",
        )
    )

    val classToTest = TmdbSearchApi(client)

    @Test
    fun `it can search shows by query Lupin`() = runBlocking {
        val pageResult = classToTest.findShows("Lupin", 1, "en", "US",false)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(1)
        assertThat(pageResult.totalResults).isEqualTo(15)
        assertThat(pageResult.results).isNotEmpty()
        val show = pageResult.results.first()
        assertThat(show.id).isEqualTo(96677)
    }

    @Test
    fun `it can search shows by query SWAT`() = runBlocking {
        val pageResult = classToTest.findShows("S.W.A.T.", 1, "en", "US",false)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(1)
        assertThat(pageResult.totalResults).isEqualTo(4)
        assertThat(pageResult.results).isNotEmpty()
        val show = pageResult.results.first()
        assertThat(show.id).isEqualTo(71790)
    }

    @Test
    fun `it can search movies by query Star Wars`() = runBlocking {
        val pageResult = classToTest.findMovies("Star Wars", 1, "en", "US",false)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(7)
        assertThat(pageResult.totalResults).isEqualTo(138)
        assertThat(pageResult.results).isNotEmpty()
        val movie = pageResult.results.first()
        assertThat(movie.id).isEqualTo(11)
        assertThat(movie.title).isEqualTo("Star Wars")
    }

    @Test
    fun `it can search persons by query ka`() = runBlocking {
        val pageResult = classToTest.findPeople("ka", 1, "en", "US",false)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(500)
        assertThat(pageResult.totalResults).isEqualTo(10_000)
        assertThat(pageResult.results).isNotEmpty()
        val person = pageResult.results.first()
        assertThat(person.id).isEqualTo(1194168)
        assertThat(person.name).isEqualTo("Shing Ka")
    }

    @Test
    fun `it can search companies by query fox`() = runBlocking {
        val pageResult = classToTest.findCompanies("fox", 1)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(8)
        assertThat(pageResult.totalResults).isEqualTo(153)
        assertThat(pageResult.results).isNotEmpty()
        val company = pageResult.results.first()
        assertThat(company.id).isEqualTo(5924)
        assertThat(company.name).isEqualTo("Fox")
    }

    @Test
    fun `it can search collections by query fast`() = runBlocking {
        val pageResult = classToTest.findCollections("fast", 1, "en")

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(1)
        assertThat(pageResult.totalResults).isEqualTo(5)
        assertThat(pageResult.results).isNotEmpty()
        val collection = pageResult.results.first()
        assertThat(collection.id).isEqualTo(257378)
        assertThat(collection.name).isEqualTo("Fast Getaway Collection")
        assertThat(collection.backdropPath).isNull()
    }

    @Test
    fun `it can search keyword by query future`() = runBlocking {
        val pageResult = classToTest.findKeywords("future", 1)

        assertThat(pageResult.page).isEqualTo(1)
        assertThat(pageResult.totalPages).isEqualTo(1)
        assertThat(pageResult.totalResults).isEqualTo(4)
        assertThat(pageResult.results).isNotEmpty()
        val company = pageResult.results.first()
        assertThat(company.id).isEqualTo(797559)
        assertThat(company.name).isEqualTo("Future Collection")
    }

}

