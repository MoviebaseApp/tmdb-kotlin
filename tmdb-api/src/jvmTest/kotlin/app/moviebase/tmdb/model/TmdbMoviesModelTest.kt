package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.image.TmdbImageType
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.LocalDate
import kotlin.test.Test

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
        overview = "overview",
        originalTitle = "originalTitle",
        originalLanguage = "EN",
        voteCount = 23,
        video = false
    )

    @Test
    fun testMovieGetPosterImage() {
        val image = movie.posterImage

        assertThat(image).isEqualTo(TmdbImage("/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg", TmdbImageType.POSTER))
    }

    @Test
    fun testMovieGetBackdropImage() {
        val image = movie.backdropImage

        assertThat(image).isEqualTo(TmdbImage("/3FHrAeYMogXd6K1e5tUzQAiS7GE.jpg", TmdbImageType.BACKDROP))
    }

}
