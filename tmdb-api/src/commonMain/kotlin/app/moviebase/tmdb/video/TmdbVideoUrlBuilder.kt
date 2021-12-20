package app.moviebase.tmdb.video

import app.moviebase.tmdb.model.TmdbVideo
import app.moviebase.tmdb.model.TmdbVideoSite

object TmdbVideoUrlBuilder {

    /**
     * Build the video URL depending on the site the video is from
     */
    fun build(tmdbVideo: TmdbVideo): String = when (tmdbVideo.site) {
        TmdbVideoSite.YOUTUBE -> "https://www.youtube.com/watch?v=${tmdbVideo.key}"
        TmdbVideoSite.VIMEO -> "https://vimeo.com/${tmdbVideo.key}"
    }
}