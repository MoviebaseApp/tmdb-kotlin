package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.image.TmdbImageType
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

// this is an example how the tests looks like
class TmdbMoviesModelTest {

    val movie = TmdbMovie(
        id = 12,
        title = "Jack Reacher: Never Go Back",
        posterPath = "/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg",
        backdropPath = "/3FHrAeYMogXd6K1e5tUzQAiS7GE.jpg",
        genresIds = listOf(TmdbMovieGenreId.ACTION, TmdbMovieGenreId.ADVENTURE),
        voteAverage = 4.5f,
        popularity = 0.8943f,
        releaseDate = LocalDate.parse("2021-03-21"),
    )

    @Test
    fun testMovieGetPosterImage() = runBlocking {
        val image = movie.posterImage

        assertEquals(image, TmdbImage("/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg", TmdbImageType.POSTER))
    }

    @Test
    fun testMovieGetBackdropImage() = runBlocking {
        val image = movie.backdropImage

        assertEquals(image, TmdbImage("/3FHrAeYMogXd6K1e5tUzQAiS7GE.jpg", TmdbImageType.BACKDROP))
    }

}
