package app.moviebase.tmdb.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class Tmdb4ListTest {


    @Test
    fun `it can a user list`() = runBlocking {
        val tmdb4 = buildTmdb4()
        val tmdb = buildTmdb3()
        val movie = tmdb.movies.getDetails(1406)
//        val list  = tmdb4.list.getList(82963, 1)

//        assertThat(list).isNotNull()
    }

}
