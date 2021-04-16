package app.moviebase.tmdb.model

import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.image.TmdbImageType
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class TmdbMoviesModelTest {

    val movie = TestTmdbMovie()

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
