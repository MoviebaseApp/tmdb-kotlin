package app.moviebase.tmdb.image

import app.moviebase.tmdb.TmdbWebConfig
import app.moviebase.tmdb.model.TmdbBackdropItem
import app.moviebase.tmdb.model.TmdbPosterItem
import app.moviebase.tmdb.model.TmdbVideo

object TmdbImageUrlBuilder {

    /**
     * Building the URL has three parts:
     * The base URL will look like: http://image.tmdb.org/t/p/.
     * Then you will need a ‘size’, which will be one of the following:
     * "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
     * And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
     *
     *
     * Example poster: https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
     * Example logo: https://image.tmdb.org/t/p/h60/ifhbNuuVnlwYy5oXA5VIb2YR8AZ.png
     *
     * Note: Also build for person profile path. Sizes are "w45", "w185", "h632", "original"
     */
    fun build(imagePath: String, sizeKey: String): String {
        var url = TmdbWebConfig.BASE_URL_TMDB_IMAGE + sizeKey
        if (!imagePath.startsWith("/")) {
            url += "/"
        }

        return url + imagePath
    }

    fun build(imagePath: String, type: TmdbImageType, width: Int, height: Int): String {
        val sizeKey = TmdbImageSize.getSizeKey(type, width, height)
        return build(imagePath, sizeKey)
    }

    fun build(image: TmdbImage, sizeKey: String): String {
        return build(image.path, sizeKey)
    }

    fun build(image: TmdbImage, width: Int, height: Int): String {
        return build(image.path, image.type, width, height)
    }

    /**
     * Builds fallback URLs with the largest sizes, which should everytime available on TMDB.
     */
    fun buildAlternativeUrls(image: TmdbImage, width: Int): List<String> {
        val sizeKey = TmdbImageSize.getLargestSizeKey(image.type, width)
        val largestUrl = build(image, sizeKey)
        val originalUrl = build(image, TmdbImageSize.ORIGINAL)

        return listOf(largestUrl, originalUrl)
    }

    fun buildPoster(image: TmdbPosterItem, width: Int): String? {
        val posterPath = image.posterPath ?: return null
        val sizeKey = TmdbImageSize.getPosterSizeKey(width)
        return build(posterPath, sizeKey)
    }

    fun buildBackdrop(image: TmdbBackdropItem, width: Int): String? {
        val posterPath = image.backdropPath ?: return null
        val sizeKey = TmdbImageSize.getBackdropSizeKey(width)
        return build(posterPath, sizeKey)
    }

    /**
     * default 120x90
     * mqdefault 320x180
     * hqdefault 480x360
     * Example http://img.youtube.com/vi/<insert-youtube-video-id-here>/default.jpg</insert-youtube-video-id-here>
     */
    fun buildYoutube(youtubeKey: String, imageFileSize: String): String {
        return TmdbWebConfig.BASE_URL_YOUTUBE_IMAGE + "/" + youtubeKey + "/" + imageFileSize + ".jpg"
    }

    fun buildYoutube(youtubeKey: String, width: Int): String {
        val imageFileSize = YoutubeImageSize.getYoutubeSizeKey(width)
        return buildYoutube(youtubeKey, imageFileSize)
    }

    fun buildYoutube(video: TmdbVideo, width: Int): String? {
        val key = video.key ?: return null
        return buildYoutube(key, width)
    }

    fun buildYoutubeAlternativeUrls(youtubeKey: String): List<String> {
        val url = buildYoutube(youtubeKey, YoutubeImageSize.SIZE_HQDEFAULT)
        return listOf(url)
    }
}
