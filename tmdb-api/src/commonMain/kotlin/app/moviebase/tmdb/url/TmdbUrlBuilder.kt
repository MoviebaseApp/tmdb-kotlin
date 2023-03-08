package app.moviebase.tmdb.url

import app.moviebase.tmdb.TmdbWebConfig
import app.moviebase.tmdb.model.TmdbVideo
import app.moviebase.tmdb.model.TmdbVideoSite

object TmdbUrlBuilder {

    /**
     * Example: https://www.themoviedb.org/u/msbreviews
     */
    fun buildUserPage(userId: String) = "${TmdbWebConfig.BASE_WEBSITE_URL}/u/$userId"

    /**
     * Example: https://www.themoviedb.org/review/63501e9ed363e5007a664110
     */
    fun buildReviewPage(reviewId: Int) = "${TmdbWebConfig.BASE_WEBSITE_URL}/review/$reviewId"

    /**
     * Build the video URL depending on the site the video is from
     */
    fun buildVideo(tmdbVideo: TmdbVideo): String? = when (tmdbVideo.site) {
        TmdbVideoSite.YOUTUBE -> "https://www.youtube.com/watch?v=${tmdbVideo.key}"
        TmdbVideoSite.VIMEO -> "https://vimeo.com/${tmdbVideo.key}"
        else -> null
    }
}
