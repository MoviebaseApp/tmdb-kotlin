package app.moviebase.tmdb.image

import app.moviebase.tmdb.model.TmdbVideo
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


class TmdbImageUrlBuilderTest {

    @Test
    fun testBuildUrlByImagePath() {
        val imageUrl = TmdbImageUrlBuilder.build("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "w185")

        assertThat(imageUrl).isEqualTo("https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildUrlByImagePathWithoutSlash() {
        val imageUrl = TmdbImageUrlBuilder.build("nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "w185")

        assertThat(imageUrl).isEqualTo("https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildUrlByTmdbImage() {
        val tmdbImage = TmdbImage("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", TmdbImageType.POSTER)
        val imageUrl = TmdbImageUrlBuilder.build(tmdbImage, 185, 0)

        assertThat(imageUrl).isEqualTo("https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildYouTubeUrlByImagePath() {
        val imageUrl = TmdbImageUrlBuilder.buildYoutube("sdfjkds", "default")

        assertThat(imageUrl).isEqualTo("https://img.youtube.com/vi/sdfjkds/default.jpg")
    }

    @Test
    fun testBuildYouTubeUrlByTmdbVideo() {
        val tmdbVideo = TmdbVideo(id = 45, key = "sdfjkds")
        val imageUrl = TmdbImageUrlBuilder.buildYoutube(tmdbVideo, 320)

        assertThat(imageUrl).isEqualTo("https://img.youtube.com/vi/sdfjkds/mqdefault.jpg")
    }

}
