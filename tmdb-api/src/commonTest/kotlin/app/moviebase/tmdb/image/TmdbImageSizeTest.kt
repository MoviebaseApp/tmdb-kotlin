package app.moviebase.tmdb.image

import kotlin.test.Test
import kotlin.test.assertEquals

class TmdbImageSizeTest {

    @Test
    fun testGetPosterSize() {
        val sizeKey = TmdbImageSize.getPosterSizeKey(160)

        assertEquals(sizeKey, "w185")
    }

    @Test
    fun testGetBackdropSize() {
        val sizeKey = TmdbImageSize.getBackdropSizeKey(300)

        assertEquals(sizeKey, "w300")
    }

    @Test
    fun testGetSizeKeyByPosterType() {
        val sizeKey = TmdbImageSize.getSizeKey(TmdbImageType.POSTER, 160, 0)

        assertEquals(sizeKey, "w185")
    }

    @Test
    fun testGetSizeKeyByBackdropType() {
        val sizeKey = TmdbImageSize.getSizeKey(TmdbImageType.BACKDROP, 300, 0)

        assertEquals(sizeKey, "w300")
    }


}
