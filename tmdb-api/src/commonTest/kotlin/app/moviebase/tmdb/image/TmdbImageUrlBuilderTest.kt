package app.moviebase.tmdb.image

import app.moviebase.tmdb.model.TmdbVideo
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class TmdbImageUrlBuilderTest {

    @Test
    fun testBuildUrlByImagePath() {
        val imageUrl = TmdbImageUrlBuilder.build("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "w185")

        assertEquals(imageUrl, "https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildUrlByImagePathWithoutSlash() {
        val imageUrl = TmdbImageUrlBuilder.build("nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "w185")

        assertEquals(imageUrl, "https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildUrlByTmdbImage() {
        val tmdbImage = TmdbImage("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", TmdbImageType.POSTER)
        val imageUrl = TmdbImageUrlBuilder.build(tmdbImage, 185, 0)

        assertEquals(imageUrl, "https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildYouTubeUrlByImagePath() {
        val imageUrl = TmdbImageUrlBuilder.buildYoutube("sdfjkds", "default")

        assertEquals(imageUrl, "https://img.youtube.com/vi/sdfjkds/default.jpg")
    }

    @Test
    fun testBuildYouTubeUrlByTmdbVideo() {
        val tmdbVideo = TmdbVideo(id = 45, key = "sdfjkds")
        val imageUrl = TmdbImageUrlBuilder.buildYoutube(tmdbVideo, 320)

        assertEquals(imageUrl, "https://img.youtube.com/vi/sdfjkds/mqdefault.jpg")
    }

}
