package app.moviebase.tmdb.image

import app.moviebase.tmdb.model.TmdbVideo
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class TmdbImageUrlBuilderTest {

    @Test
    fun testBuildUrlByImagePath() = runBlocking {
        val imageUrl = TmdbImageUrlBuilder.build("w185", "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")

        assertEquals(imageUrl, "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildUrlByImagePathWithoutSlash() = runBlocking {
        val imageUrl = TmdbImageUrlBuilder.build("w185", "nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")

        assertEquals(imageUrl, "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildUrlByTmdbImage() = runBlocking {
        val tmdbImage = TmdbImage("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", TmdbImageType.POSTER)
        val imageUrl = TmdbImageUrlBuilder.build(tmdbImage, 185, 0)

        assertEquals(imageUrl, "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
    }

    @Test
    fun testBuildYouTubeUrlByImagePath() = runBlocking {
        val imageUrl = TmdbImageUrlBuilder.buildYoutube("sdfjkds", "default")

        assertEquals(imageUrl, "http://img.youtube.com/vi/sdfjkds/default.jpg")
    }

    @Test
    fun testBuildYouTubeUrlByTmdbVideo() = runBlocking {
        val tmdbVideo = TmdbVideo(id = 45, key = "sdfjkds")
        val imageUrl = TmdbImageUrlBuilder.buildYoutube(tmdbVideo, 320)

        assertEquals(imageUrl, "http://img.youtube.com/vi/sdfjkds/mqdefault.jpg")
    }

}
