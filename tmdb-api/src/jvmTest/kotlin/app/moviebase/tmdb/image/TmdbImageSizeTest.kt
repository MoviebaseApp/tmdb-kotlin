package app.moviebase.tmdb.image

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class TmdbImageSizeTest {

    @Test
    fun testGetPosterSize() {
        val sizeKey = TmdbImageSize.getPosterSizeKey(160)

        assertThat(sizeKey).isEqualTo("w185")
    }

    @Test
    fun testGetBackdropSize() {
        val sizeKey = TmdbImageSize.getBackdropSizeKey(300)

        assertThat(sizeKey).isEqualTo("w300")
    }

    @Test
    fun testGetSizeKeyByPosterType() {
        val sizeKey = TmdbImageSize.getSizeKey(TmdbImageType.POSTER, 160, 0)

        assertThat(sizeKey).isEqualTo("w185")
    }

    @Test
    fun testGetSizeKeyByBackdropType() {
        val sizeKey = TmdbImageSize.getSizeKey(TmdbImageType.BACKDROP, 300, 0)

        assertThat(sizeKey).isEqualTo("w300")
    }
}
